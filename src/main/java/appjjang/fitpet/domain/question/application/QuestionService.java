package appjjang.fitpet.domain.question.application;

import appjjang.fitpet.domain.home.dto.QuestionDto;
import appjjang.fitpet.domain.question.dto.response.FaqResponse;
import appjjang.fitpet.domain.question.domain.Type;
import appjjang.fitpet.domain.question.dao.QuestionRepository;
import appjjang.fitpet.domain.question.domain.Question;
import appjjang.fitpet.domain.question.dto.request.FaqCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void addQuestion(FaqCreateRequest request) {
        questionRepository.save(Question.createQuestion(request.getType(), request.getTitle(), request.getAnswer()));
    }

    @Transactional(readOnly = true)
    public List<FaqResponse> getQuestionList(Type keyword) {
        return questionRepository.findQuestionByType(keyword).stream()
                .map(FaqResponse::new)
                .collect(Collectors.toList());
    }

    public QuestionDto getQuestionTitleList() {
        return new QuestionDto(questionRepository.findTop4ByOrderByIdDesc().stream()
                .map(Question::getTitle)
                .collect(Collectors.toList()));
    }
}
