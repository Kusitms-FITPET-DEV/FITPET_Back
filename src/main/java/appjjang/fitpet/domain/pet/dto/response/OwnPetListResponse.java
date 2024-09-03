package appjjang.fitpet.domain.pet.dto.response;

import appjjang.fitpet.domain.pet.dto.PetInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OwnPetListResponse {
    @Schema(description = "조회한 펫의 리스트 사이즈", example = "2")
    private int petCount;

    @Schema(description = "조회한 펫 정보 리스트")
    private List<PetInfoDto> petList;
}
