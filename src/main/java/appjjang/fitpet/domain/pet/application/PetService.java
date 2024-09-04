package appjjang.fitpet.domain.pet.application;

import appjjang.fitpet.domain.catprice.dao.CatPriceRepository;
import appjjang.fitpet.domain.catprice.domain.CatPrice;
import appjjang.fitpet.domain.coverage.dao.CoverageRepository;
import appjjang.fitpet.domain.coverage.domain.Coverage;
import appjjang.fitpet.domain.dogprice.dao.DogPriceRepository;
import appjjang.fitpet.domain.dogprice.domain.DogPrice;
import appjjang.fitpet.domain.member.domain.Member;
import appjjang.fitpet.domain.pet.dao.PetRepository;
import appjjang.fitpet.domain.pet.domain.Pet;
import appjjang.fitpet.domain.pet.dto.SingleEstimateDto;
import appjjang.fitpet.domain.pet.dto.request.PetRegisterRequest;
import appjjang.fitpet.domain.pet.dto.request.PetUpdateRequest;
import appjjang.fitpet.domain.pet.dto.PetInfoDto;
import appjjang.fitpet.domain.pet.dto.response.OwnPetListResponse;
import appjjang.fitpet.domain.pet.dto.response.PetEstimateDetailResponse;
import appjjang.fitpet.domain.pet.dto.response.SinglePetQueryResponse;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import appjjang.fitpet.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static appjjang.fitpet.domain.pet.domain.Species.DOG;
import static appjjang.fitpet.global.common.constants.PetConstants.*;
import static appjjang.fitpet.global.common.values.PetValues.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PetService {
    private final PetRepository petRepository;
    private final MemberUtil memberUtil;
    private final DogPriceRepository dogPriceRepository;
    private final CatPriceRepository catPriceRepository;
    private final CoverageRepository coverageRepository;

    public void savePet(PetRegisterRequest request) {
        final Member currentMember =  memberUtil.getCurrentMember();
        petRepository.save(Pet.createPet(
                request.getPetName(),
                request.getSpecies(),
                request.getBreed(),
                request.getBirthYear(),
                request.getPhone(),
                currentMember
        ));
    }

    public void updatePet(Long petId, PetUpdateRequest request) {
        final Member currentMember = memberUtil.getCurrentMember();
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new CustomException(ErrorCode.PET_NOT_FOUND));
        validatePetOwner(pet, currentMember);
        pet.updatePet(request.getPetName(), request.getSpecies(), request.getBreed(), request.getBirthYear());
    }

    @Transactional(readOnly = true)
    public SinglePetQueryResponse getPet(Long petId, String priceRate) {
        final Member currentMember = memberUtil.getCurrentMember();
        validatePriceRate(priceRate);

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new CustomException(ErrorCode.PET_NOT_FOUND));

        validatePetOwner(pet, currentMember);

        int age = LocalDate.now().getYear() - pet.getBirthYear();

        List<SingleEstimateDto> estimateList = getEstimateList(pet, priceRate, age).stream()
                .peek(dto -> dto.setInsuranceFee(applyDiscountRate(dto.getInsuranceFee(), dto.getInsuranceCompany())))
                .map(this::updateDto)
                .collect(Collectors.toList());

        int maxInsuranceFee = estimateList.stream()
                .mapToInt(SingleEstimateDto::getInsuranceFee)
                .max()
                .orElse(NO_MIN_OR_MAX_INSURANCE_FEE);
        int minInsuranceFee = estimateList.stream()
                .mapToInt(SingleEstimateDto::getInsuranceFee)
                .min()
                .orElse(NO_MIN_OR_MAX_INSURANCE_FEE);

        return new SinglePetQueryResponse(pet.getName(), pet.getSpecies(), age, pet.getBreed(), minInsuranceFee, maxInsuranceFee, estimateList.size(), estimateList);
    }

    @Transactional(readOnly = true)
    public OwnPetListResponse getPetsInfo () {
        final Member currentMember = memberUtil.getCurrentMember();
        List<PetInfoDto> petList = currentMember.getPets().stream()
                .map(PetInfoDto::new)
                .collect(Collectors.toList());
        return new OwnPetListResponse(currentMember.getPets().size(), petList);
    }

    @Transactional(readOnly = true)
    public PetEstimateDetailResponse getEstimateDetail(Long petId, Long priceId) {
        Member currentMember = memberUtil.getCurrentMember();
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new CustomException(ErrorCode.PET_NOT_FOUND));
        validatePetOwner(pet, currentMember);

        SingleEstimateDto dto = pet.getSpecies().equals(DOG) ?
                new SingleEstimateDto(dogPriceRepository.findById(priceId).orElseThrow(() -> new CustomException(ErrorCode.ESTIMTE_NOT_FOUND))) :
                new SingleEstimateDto(catPriceRepository.findById(priceId).orElseThrow(() -> new CustomException(ErrorCode.ESTIMTE_NOT_FOUND)));

        Coverage coverage = coverageRepository.findLatestByInsuranceCompany(dto.getInsuranceCompany());
        return new PetEstimateDetailResponse(
                dto.getInsuranceFee(),
                applyDiscountRate(dto.getInsuranceFee(), dto.getInsuranceCompany()),
                updateDto(dto).getInsuranceCompany(),
                coverage
        );
    }

    public void deletePet(Long petId) {
        final Member currentMember = memberUtil.getCurrentMember();
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new CustomException(ErrorCode.PET_NOT_FOUND));
        validatePetOwner(pet, currentMember);
        petRepository.delete(pet.delete());
    }

    private void validatePetOwner(Pet pet, Member member) {
        if (!member.getPets().contains(pet)) {
            throw new CustomException(ErrorCode.NOT_PET_OWNER);
        }
    }

    private List<SingleEstimateDto> getEstimateList(Pet pet, String priceRate, int age) {
        if (pet.getSpecies().equals(DOG)) {
            List<DogPrice> dogPriceList = petRepository.getDogPriceList(age, pet.getBreed(), UPDATE_CYCLE, SELF_BURDEN, priceRate, ONE_DAY_REWARD);
            return dogPriceList.stream()
                    .map(SingleEstimateDto::new)
                    .collect(Collectors.toList());
        }

        List<CatPrice> catPriceList = petRepository.getCatPriceList(age, UPDATE_CYCLE, SELF_BURDEN, priceRate, ONE_DAY_REWARD);
        return catPriceList.stream()
                .map(SingleEstimateDto::new)
                .collect(Collectors.toList());
    }

    private int applyDiscountRate(int insuranceFee, String insuranceCompany) {
        return switch (insuranceCompany.toLowerCase()) {
            case PETPERMINT -> (int) Math.round(insuranceFee * PETPERMINT_DISCOUNT_RATE);
            case SAMSUNG -> (int) Math.round(insuranceFee * SAMSUNG_DISCOUNT_RATE);
            case DB -> (int) Math.round(insuranceFee * DB_DISCOUNT_RATE);
            case HYUNDAI -> (int) Math.round(insuranceFee * HYUNDAI_DISCOUNT_RATE);
            case KB -> (int) Math.round(insuranceFee * KB_DISCOUNT_RATE);
            default -> throw new CustomException(ErrorCode.INSURANCE_COMPANY_NOT_EXIST);
        };
    }

    private SingleEstimateDto updateDto(SingleEstimateDto dto) {
        return switch (dto.getInsuranceCompany().toLowerCase()) {
            case PETPERMINT -> dto.update(PETPERMINT_COMPANY, PETPERINT_INSURANCE);
            case SAMSUNG -> dto.update(SAMSUNG_COMPANY, SAMSUNG_INSURANCE);
            case DB -> dto.update(DB_COMPANY, DB_INSURANCE);
            case HYUNDAI -> dto.update(HYUNDAI_COMPANY, HYUNDAI_INSURANCE);
            case KB -> dto.update(KB_COMPANY, KB_INSURANCE);
            default -> throw new CustomException(ErrorCode.MISSING_JWT_TOKEN);
        };
    }

    private void validatePriceRate(String priceRate) {
        if(!VALID_RATES.contains(priceRate)) {
            throw new CustomException(ErrorCode.PRICE_RATE_NOT_EXIST);
        }
    }
}
