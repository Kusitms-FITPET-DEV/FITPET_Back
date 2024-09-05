package appjjang.fitpet.domain.question.application;

import appjjang.fitpet.domain.question.dao.QuestionRepository;
import appjjang.fitpet.domain.question.domain.Question;
import appjjang.fitpet.domain.question.dto.request.FaqCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void addQuestion(FaqCreateRequest request) {
        questionRepository.save(Question.createQuestion(request.getType(), request.getTitle(), request.getAnswer()));
    }
}
