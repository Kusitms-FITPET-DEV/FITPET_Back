package appjjang.fitpet.domain.question.dao;

import appjjang.fitpet.domain.question.domain.Question;

import java.util.List;

public interface QuestionRepositoryCustom {
    List<String> findDistinctTypes();
    List<Question> findQuestionByType(String keyword);
}
