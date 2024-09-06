package appjjang.fitpet.domain.question.dao;

import appjjang.fitpet.domain.question.api.Type;
import appjjang.fitpet.domain.question.domain.Question;

import java.util.List;

public interface QuestionRepositoryCustom {
    List<Question> findQuestionByType(Type keyword);
}
