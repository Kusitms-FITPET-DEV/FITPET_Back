package appjjang.fitpet.domain.pet.dto.response;

import appjjang.fitpet.domain.pet.domain.Species;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PetRegisterRequest {
    @Schema(description = "등록할 펫 이름", example = "보리")
    private String petName;

    @Schema(description = "강아지 혹은 고양이", example = "DOG")
    private Species species;

    @Schema(description = "견종 (고양이라면 비워주세요)", example = "말티즈")
    private String breed;

    @Schema(description = "펫 탄생년도", example = "2001")
    private int birthYear;

    @Schema(description = "비고", example = "병력이 있어요")
    private String etc;

    @Schema(description = "연락처", example = "01012341234")
    private String phone;
}
