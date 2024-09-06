package appjjang.fitpet.domain.quiz.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private String question;
    private boolean answer;
    private String description;

    @Builder
    private Quiz(String question, boolean answer, String description) {
        this.question = question;
        this.answer = answer;
        this.description = description;
    }

    public static Quiz createQuiz(String question, boolean answer, String description) {
        return Quiz.builder()
                .question(question)
                .answer(answer)
                .description(description)
                .build();
    }
}
