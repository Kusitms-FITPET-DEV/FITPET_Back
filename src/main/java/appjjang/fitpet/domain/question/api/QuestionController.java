package appjjang.fitpet.domain.question.api;

import appjjang.fitpet.domain.question.application.QuestionService;
import appjjang.fitpet.domain.question.dto.request.FaqCreateRequest;
import appjjang.fitpet.domain.question.dto.response.FaqQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "FAQ API", description = "FAQ 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Operation(summary = "FAQ 생성", description = "관리자가 FQA를 생성합니다.")
    @PostMapping("/questions")
    public ResponseEntity<Void> addQuestion(@RequestBody FaqCreateRequest request) {
        questionService.addQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "FAQ 조회", description = "모든 FAQ를 조회합니다.")
    @GetMapping
    public FaqQueryResponse addQuestion(@RequestParam(required = false) String keyword) {
        return questionService.getQuestionList(keyword);
    }
}
