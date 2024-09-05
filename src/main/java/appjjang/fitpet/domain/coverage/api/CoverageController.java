package appjjang.fitpet.domain.coverage.api;

import appjjang.fitpet.domain.coverage.application.CoverageService;
import appjjang.fitpet.domain.coverage.dto.request.CoverageCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "admin API", description = "관리자 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CoverageController {
    private final CoverageService coverageService;

    @Operation(description = "데이터 삽입을 위하여 커버리지 생성")
    @PostMapping("/coverage")
    public ResponseEntity<Void> saveCoverage(@RequestBody CoverageCreateRequest request) {
        coverageService.saveCoverage(request);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
