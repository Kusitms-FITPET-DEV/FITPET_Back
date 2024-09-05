package appjjang.fitpet.domain.compensation.api;

import appjjang.fitpet.domain.compensation.application.CompensationService;
import appjjang.fitpet.domain.compensation.dto.response.CompensationListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "보상 API", description = "보상 내역 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/compensations")
public class CompensationController {
    private final CompensationService compensationService;

    @Operation(summary = "보상 내역 조회", description = "보상 내역을 조회합니다.")
    @GetMapping("/{petId}")
    public CompensationListResponse getCompensationList(@PathVariable Long petId) {
        return compensationService.getCompensationList(petId);
    }
}
