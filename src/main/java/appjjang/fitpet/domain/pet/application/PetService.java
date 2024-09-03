package appjjang.fitpet.domain.pet.application;

import appjjang.fitpet.domain.catprice.domain.CatPrice;
import appjjang.fitpet.domain.dogprice.domain.DogPrice;
import appjjang.fitpet.domain.member.domain.Member;
import appjjang.fitpet.domain.pet.dao.PetRepository;
import appjjang.fitpet.domain.pet.domain.Pet;
import appjjang.fitpet.domain.pet.dto.SingleEstimateDto;
import appjjang.fitpet.domain.pet.dto.request.PetRegisterRequest;
import appjjang.fitpet.domain.pet.dto.request.PetUpdateRequest;
import appjjang.fitpet.domain.pet.dto.PetInfoDto;
import appjjang.fitpet.domain.pet.dto.response.OwnPetListResponse;
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

        int age = LocalDate.now().getYear() - pet.getBirthYear() + 1;

        List<SingleEstimateDto> estimateList = getEstimateList(pet, priceRate, age).stream()
                .peek(this::applyDiscountRate)
                .collect(Collectors.toList());

        int maxInsuranceFee = estimateList.stream()
                .mapToInt(SingleEstimateDto::getInsuranceFee)
                .max()
                .orElse(NO_MIN_OR_MAX_INSURANCE_FEE);
        int minInsuranceFee = estimateList.stream()
                .mapToInt(SingleEstimateDto::getInsuranceFee)
                .min()
                .orElse(NO_MIN_OR_MAX_INSURANCE_FEE);

        return new SinglePetQueryResponse(pet.getName(), pet.getSpecies(), age, pet.getBreed(),
                maxInsuranceFee == minInsuranceFee,
                minInsuranceFee, maxInsuranceFee, estimateList);
    }


    public OwnPetListResponse getPetsInfo () {
        final Member currentMember = memberUtil.getCurrentMember();
        List<PetInfoDto> petList = currentMember.getPets().stream()
                .map(PetInfoDto::new)
                .collect(Collectors.toList());
        return new OwnPetListResponse(currentMember.getPets().size(), petList);

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

    private void applyDiscountRate(SingleEstimateDto dto) {
        switch (dto.getInsuranceCompany().toLowerCase()) {
            case PETPERMINT -> dto.setInsuranceFee((int) Math.round(dto.getInsuranceFee() * PETPERMINT_DISCOUNT_RATE));
            case SAMSUNG -> dto.setInsuranceFee((int) Math.round(dto.getInsuranceFee() * SAMSUNG_DISCOUNT_RATE));
            case DB -> dto.setInsuranceFee((int) Math.round(dto.getInsuranceFee() * DB_DISCOUNT_RATE));
            case HYUNDAI -> dto.setInsuranceFee((int) Math.round(dto.getInsuranceFee() * HYUNDAI_DISCOUNT_RATE));
            case KB -> dto.setInsuranceFee((int) Math.round(dto.getInsuranceFee() * KB_DISCOUNT_RATE));
            default -> throw new CustomException(ErrorCode.INSURANCE_COMPANY_NOT_EXIST);
        }
    }


    private void validatePriceRate(String priceRate) {
        if(!VALID_RATES.contains(priceRate)) {
            throw new CustomException(ErrorCode.PRICE_RATE_NOT_EXIST);
        }
    }
}
