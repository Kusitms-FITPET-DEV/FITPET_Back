package appjjang.fitpet.domain.home.application;

import appjjang.fitpet.domain.home.dto.response.HomeResponse;
import appjjang.fitpet.domain.journal.application.JournalService;
import appjjang.fitpet.domain.question.application.QuestionService;
import appjjang.fitpet.domain.quiz.application.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeService {
    private final JournalService journalService;
    private final QuizService quizService;
    private final QuestionService questionService;

    @Transactional(readOnly = true)
    public HomeResponse getHomeDate() {
        return new HomeResponse(
                journalService.getJournalListDto(),
                quizService.getRandomQuiz(),
                questionService.getQuestionTitleList()
                );
    }
}
