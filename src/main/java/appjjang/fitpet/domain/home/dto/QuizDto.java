package appjjang.fitpet.domain.home.dto;

import appjjang.fitpet.domain.quiz.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class QuizDto {
    private String question;
    private boolean answer;
    private String description;

    public QuizDto(Quiz quiz) {
        this.question = quiz.getQuestion();
        this.answer =  quiz.isAnswer();
        this.description = quiz.getDescription();
    }
}
