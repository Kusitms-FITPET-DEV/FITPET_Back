package appjjang.fitpet.domain.insurance.api;

import appjjang.fitpet.domain.insurance.application.InsuranceService;
import appjjang.fitpet.domain.insurance.dto.response.InsuranceDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "보험 API", description = "보험 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/insurances")
public class InsuranceController {
    private final InsuranceService insuranceService;

    @Operation(summary = "보험 자세하게 조회", description = "보험을 자세하게 조회합니다.")
    @GetMapping("/{petId}")
    public InsuranceDetailResponse getInsuranceDetail(@PathVariable Long petId) {
        return insuranceService.getInsuranceDetail(petId);
    }
}
