package appjjang.fitpet.domain.pet.application;

import appjjang.fitpet.domain.member.domain.Member;
import appjjang.fitpet.domain.pet.dao.PetRepository;
import appjjang.fitpet.domain.pet.domain.Pet;
import appjjang.fitpet.domain.pet.dto.response.PetRegisterRequest;
import appjjang.fitpet.domain.pet.dto.response.PetUpdateRequest;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import appjjang.fitpet.global.util.MemberUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                request.getEtc(),
                request.getPhone(),
                currentMember
        ));
    }

    public void updatePet(PetUpdateRequest request) {
        Pet pet = petRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.PET_NOT_FOUND));
        pet.updatePet(request.getPetName(), request.getSpecies(), request.getBreed(), request.getBirthYear());
    }
}
