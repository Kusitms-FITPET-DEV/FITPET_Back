package appjjang.fitpet.domain.insurance.dto.response;

import appjjang.fitpet.domain.insurance.dto.ContractDto;
import appjjang.fitpet.domain.insurance.dto.CoverageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InsuranceDetailResponse {
    @Schema(description = "보험 계약")
    private ContractDto contract;

    @Schema(description = "보험 보장")
    private CoverageDto coverage;
}
