package appjjang.fitpet.domain.question.dto.request;

import appjjang.fitpet.domain.question.domain.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FaqCreateRequest {
    private Type type;
    private String title;
    private String answer;
}
