package appjjang.fitpet.domain.compensation.application;

import appjjang.fitpet.domain.compensation.dao.CompensationRepository;
import appjjang.fitpet.domain.compensation.dto.CompensationDto;
import appjjang.fitpet.domain.compensation.dto.response.CompensationListResponse;
import appjjang.fitpet.domain.member.domain.Member;
import appjjang.fitpet.domain.pet.dao.PetRepository;
import appjjang.fitpet.domain.pet.domain.Pet;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import appjjang.fitpet.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompensationService {
    private final PetRepository petRepository;
    private final CompensationRepository compensationRepository;
    private final MemberUtil memberUtil;

    @Transactional(readOnly = true)
    public CompensationListResponse getCompensationList(Long petId) {
        Member currentMember = memberUtil.getCurrentMember();
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new CustomException(ErrorCode.PET_NOT_FOUND));
        validatePetOwner(pet, currentMember);

        List<CompensationDto> dtoList = compensationRepository.findCompensationsByPetId(petId).stream()
                .map(CompensationDto::new)
                .collect(Collectors.toList());
        return new CompensationListResponse(pet.getName(), pet.getInsurance().getStartDate(), pet.getInsurance().getEndDate(), dtoList);
    }

    private void validatePetOwner(Pet pet, Member member) {
        if (!member.getPets().contains(pet)) {
            throw new CustomException(ErrorCode.NOT_PET_OWNER);
        }
    }
}
