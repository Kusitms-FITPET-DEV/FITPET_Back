package appjjang.fitpet.domain.insurance.application;

import appjjang.fitpet.domain.insurance.dto.ContractDto;
import appjjang.fitpet.domain.insurance.dto.CoverageDto;
import appjjang.fitpet.domain.insurance.dto.response.InsuranceDetailResponse;
import appjjang.fitpet.domain.member.domain.Member;
import appjjang.fitpet.domain.pet.dao.PetRepository;
import appjjang.fitpet.domain.pet.domain.Pet;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import appjjang.fitpet.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static appjjang.fitpet.global.common.constants.InsuranceConstants.*;

@Service
@RequiredArgsConstructor
@Transactional
public class InsuranceService {
    private final PetRepository petRepository;
    private final MemberUtil memberUtil;

    @Transactional(readOnly = true)
    public InsuranceDetailResponse getInsuranceDetail(Long petId) {
        final Member currentMember = memberUtil.getCurrentMember();
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new CustomException(ErrorCode.PET_NOT_FOUND));
        validatePetOwner(currentMember, pet);
        validateHasInsurance(pet);

        String startDate = changeDateFormat(pet.getInsurance().getStartDate().toString());
        String endDate = changeDateFormat(pet.getInsurance().getEndDate().toString());
        String bankAccount = maskFirstFourCharacters(pet.getInsurance().getBankAccount());

        return new InsuranceDetailResponse(
                new ContractDto(pet.getInsurance(), startDate, endDate, bankAccount),
                new CoverageDto(pet.getInsurance().getCoverage())
        );
    }

    private String changeDateFormat(String date) {
        return date.replace(DASH, DOT);
    }

    private String maskFirstFourCharacters(String account) {
        return MASK + account.substring(MASK_COUNT);
    }

    private void validateHasInsurance(Pet pet) {
        if (!pet.isInsurance()) {
            throw new CustomException(ErrorCode.INSURANCE_NOT_FOUND);
        }
    }

    private void validatePetOwner(Member member, Pet pet) {
        if (!member.getPets().contains(pet)) {
            throw new CustomException(ErrorCode.NOT_PET_OWNER);
        }
    }
}
