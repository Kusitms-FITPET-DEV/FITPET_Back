package appjjang.fitpet.domain.compensationhistory.api;

import appjjang.fitpet.domain.compensationhistory.application.HistoryService;
import appjjang.fitpet.domain.compensationhistory.dto.response.HistoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "알림 API", description = "알림 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    @Operation(summary = "보상 변경 내역 조회", description = "알림에 사용되는 보상 변경 내용을 조회합니다.")
    @GetMapping
    public List<HistoryResponse> getCompensationList() {
        return historyService.getHistoryList();
    }
}
