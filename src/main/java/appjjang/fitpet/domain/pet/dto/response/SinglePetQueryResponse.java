package appjjang.fitpet.domain.pet.dto.response;

import appjjang.fitpet.domain.pet.domain.Species;
import appjjang.fitpet.domain.pet.dto.SingleEstimateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SinglePetQueryResponse {
    @Schema(description = "펫에 대한 보험이 체결되었는지의 여부")
    private boolean isInsurance;

    @Schema(description = "조회한 펫의 이름")
    private String name;

    @Schema(description = "조회한 펫의 종")
    private Species species;

    @Schema(description = "조회한 펫의 나이")
    private int age;

    @Schema(description = "조회한 펫의 견종 (고양이라면 비워집니다.)")
    private String breed;

    @Schema(description = "조회되는 견적 중 제일 최대 가격")
    private int maxInsuranceFee;

    @Schema(description = "조회되는 견적 중 제일 최소 가격")
    private int minInsuranceFee;

    @Schema(description = "조회 견적의 개수")
    private int resultCount;

    @Schema(description = "조회 견적 리스트")
    private List<SingleEstimateDto> estimateList;
}
