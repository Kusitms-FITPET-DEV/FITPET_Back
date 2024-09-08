package appjjang.fitpet.domain.pet.dto;

import appjjang.fitpet.domain.pet.domain.Pet;
import appjjang.fitpet.domain.pet.domain.Species;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PetInfoDto {
    @Schema(description = "조회한 펫의 id", example = "1")
    private Long petId;

    @Schema(description = "조회한 펫의 이름", example = "보리")
    private String name;

    @Schema(description = "조회한 펫의 출생년도", example = "2023")
    private int birthYear;

    @Schema(description = "조회한 펫의 나이", example = "2")
    private int age;

    @Schema(description = "조회 펫의 종", example = "DOG")
    private Species species;

    @Schema(description = "조회한 펫의 견종 (고양이라면 null)", example = "말티즈")
    private String breed;

    public PetInfoDto(Pet pet) {
        this.petId = pet.getId();
        this.name = pet.getName();
        this.birthYear = pet.getBirthYear();
        this.age = LocalDate.now().getYear() - pet.getBirthYear();
        this.species = pet.getSpecies();
        this.breed = pet.getBreed();
    }
}
