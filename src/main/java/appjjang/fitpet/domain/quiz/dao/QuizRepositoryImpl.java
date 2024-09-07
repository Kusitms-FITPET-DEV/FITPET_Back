package appjjang.fitpet.domain.quiz.dao;

import appjjang.fitpet.domain.quiz.domain.Quiz;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static appjjang.fitpet.domain.quiz.domain.QQuiz.quiz;

@Repository
@RequiredArgsConstructor
public class QuizRepositoryImpl implements QuizRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Quiz findRandomQuiz() {
        return queryFactory
                .selectFrom(quiz)
                .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
                .fetchFirst();
    }
}
