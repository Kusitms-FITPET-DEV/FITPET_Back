package appjjang.fitpet.domain.question.application;

import appjjang.fitpet.domain.question.dto.response.FaqResponse;
import appjjang.fitpet.domain.question.domain.Type;
import appjjang.fitpet.domain.question.dao.QuestionRepository;
import appjjang.fitpet.domain.question.domain.Question;
import appjjang.fitpet.domain.question.dto.request.FaqCreateRequest;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
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

    public List<String> getQuestionTitleList() {
        return questionRepository.findTop4ByOrderByIdDesc().stream()
                .map(Question::getTitle)
                .collect(Collectors.toList());
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.delete(questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(ErrorCode.SAMPLE_ERROR)));
    }
}
