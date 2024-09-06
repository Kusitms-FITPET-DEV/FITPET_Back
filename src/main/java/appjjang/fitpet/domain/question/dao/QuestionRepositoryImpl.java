package appjjang.fitpet.domain.question.dao;

import appjjang.fitpet.domain.question.api.Type;
import appjjang.fitpet.domain.question.domain.Question;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static appjjang.fitpet.domain.question.domain.QQuestion.question;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Question> findQuestionByType(Type keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        if (keyword != null) {
            builder.and(question.type.eq(keyword));
        }
        return queryFactory.selectFrom(question)
                .where(builder)
                .fetch();
    }
}
