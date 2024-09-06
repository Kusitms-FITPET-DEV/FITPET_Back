package appjjang.fitpet.domain.quiz.dao;

import appjjang.fitpet.domain.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> , QuizRepositoryCustom{
}
