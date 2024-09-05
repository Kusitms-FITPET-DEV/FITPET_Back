package appjjang.fitpet.domain.question;

import appjjang.fitpet.domain.question.domain.Question;
import lombok.Getter;

@Getter
public class FaqDto {
    private String type;
    private String question;
    private String answer;

    public FaqDto(Question question) {
        this.type = question.getType();
        this.question = question.getTitle();
        this.answer = question.getAnswer();
    }
}
