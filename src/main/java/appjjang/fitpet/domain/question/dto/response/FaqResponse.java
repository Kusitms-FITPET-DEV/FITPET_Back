package appjjang.fitpet.domain.question.dto.response;

import appjjang.fitpet.domain.question.api.Type;
import appjjang.fitpet.domain.question.domain.Question;
import lombok.Getter;

@Getter
public class FaqResponse {
    private Type type;
    private String question;
    private String answer;

    public FaqResponse(Question question) {
        this.type = question.getType();
        this.question = question.getTitle();
        this.answer = question.getAnswer();
    }
}
