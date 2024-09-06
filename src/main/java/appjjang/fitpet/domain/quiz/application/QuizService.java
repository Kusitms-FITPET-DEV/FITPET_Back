package appjjang.fitpet.domain.quiz.application;

import appjjang.fitpet.domain.home.dto.QuizDto;
import appjjang.fitpet.domain.quiz.dao.QuizRepository;
import appjjang.fitpet.domain.quiz.domain.Quiz;
import appjjang.fitpet.domain.quiz.dto.QuizCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizService {
    private final QuizRepository quizRepository;

    public void saveQuiz(QuizCreateRequest request) {
        quizRepository.save(Quiz.createQuiz(request.getQuestion(), request.isAnswer(), request.getDescription()));
    }

    public QuizDto getRandomQuiz() {
        return new QuizDto(quizRepository.findRandomQuiz());
    }
}
