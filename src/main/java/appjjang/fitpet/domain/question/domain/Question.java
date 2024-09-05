package appjjang.fitpet.domain.question.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private String type;
    private String title;

    @Column(length = 2000)
    private String answer;

    @Builder
    private Question(String type, String title, String answer) {
        this.type = type;
        this.title = title;
        this.answer = answer;
    }

    public static Question createQuestion(String type, String title, String answer) {
        return Question.builder()
                .type(type)
                .title(title)
                .answer(answer)
                .build();
    }
}
