package appjjang.fitpet.domain.question.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FaqCreateRequest {
    private String type;
    private String title;
    private String answer;
}
