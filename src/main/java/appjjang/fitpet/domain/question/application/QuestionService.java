package appjjang.fitpet.domain.question.application;

import appjjang.fitpet.domain.question.FaqDto;
import appjjang.fitpet.domain.question.dao.QuestionRepository;
import appjjang.fitpet.domain.question.domain.Question;
import appjjang.fitpet.domain.question.dto.request.FaqCreateRequest;
import appjjang.fitpet.domain.question.dto.response.FaqQueryResponse;
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

    public FaqQueryResponse getQuestionList(String keyword) {
        List<String> types = questionRepository.findDistinctTypes();
        List<FaqDto> dtoList = questionRepository.findQuestionByType(keyword).stream()
                .map(FaqDto::new)
                .collect(Collectors.toList());
        return new FaqQueryResponse(types.size(), types, dtoList.size(), dtoList);
    }
}
