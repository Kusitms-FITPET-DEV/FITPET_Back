package appjjang.fitpet.domain.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HistoryUpdateRequest {
    @Schema(example = "담당자")
    private String chargePerson;

    @Schema(example = "150000")
    private int insuranceFee;
}
