package appjjang.fitpet.domain.question.dao;

import appjjang.fitpet.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> , QuestionRepositoryCustom{
    @Query(value = "SELECT * FROM question ORDER BY question_id DESC LIMIT 4", nativeQuery = true)
    List<Question> findTop4ByOrderByIdDesc();
}
