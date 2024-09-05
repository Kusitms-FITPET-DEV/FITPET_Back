package appjjang.fitpet.domain.question.dao;

import appjjang.fitpet.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> , QuestionRepositoryCustom{
}
