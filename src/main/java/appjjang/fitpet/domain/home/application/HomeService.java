package appjjang.fitpet.domain.home.application;

import appjjang.fitpet.domain.banner.application.BannerService;
import appjjang.fitpet.domain.home.api.HomeController;
import appjjang.fitpet.domain.home.dto.BannerListDto;
import appjjang.fitpet.domain.home.dto.JournalListDto;
import appjjang.fitpet.domain.home.dto.response.HomeResponse;
import appjjang.fitpet.domain.journal.application.JournalService;
import appjjang.fitpet.domain.question.application.QuestionService;
import appjjang.fitpet.domain.quiz.application.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeService {
    private final BannerService bannerService;
    private final JournalService journalService;
    private final QuizService quizService;
    private final QuestionService questionService;

    @Transactional(readOnly = true)
    public HomeResponse getHomeDate() {
        return new HomeResponse(
                new BannerListDto(bannerService.getBannerUrlList()),
                journalService.getJournalListDto(),
                quizService.getRandomQuiz(),
                questionService.getQuestionTitleList()
                );
    }
}
