package appjjang.fitpet.domain.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuizCreateRequest {
    private String question;
    private boolean answer;
    private String description;
}
