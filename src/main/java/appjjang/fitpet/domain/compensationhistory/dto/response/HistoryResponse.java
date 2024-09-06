package appjjang.fitpet.domain.compensationhistory.dto.response;

import appjjang.fitpet.domain.compensationhistory.domain.History;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class HistoryResponse {
    @Schema(description = "보상 내역 기록 id", example = "1")
    private Long historyId;

    @Schema(description = "보상 내역 기록 변경 상태", example = "접수")
    private String progress;

    @Schema(description = "보상 내역 기록 변경 시간", example = "2024-09-06 01:07:33.334519")
    private LocalDateTime time;

    @Schema(description = "보상 내역 기록 확인 여부", example = "false")
    private boolean confirmed;

    public HistoryResponse(History history) {
        this.historyId = history.getId();
        this.progress = history.getNextProgress().getValue();
        this.time = history.getChangeDateTime();
        this.confirmed = history.isConfirmed();
    }
}
