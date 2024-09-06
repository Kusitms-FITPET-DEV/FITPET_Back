package appjjang.fitpet.domain.question.api;

import appjjang.fitpet.domain.question.application.QuestionService;
import appjjang.fitpet.domain.question.domain.Type;
import appjjang.fitpet.domain.question.dto.response.FaqResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "FAQ API", description = "FAQ 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Operation(summary = "FAQ 조회", description = "모든 FAQ를 조회합니다.")
    @GetMapping
    public List<FaqResponse> addQuestion(@RequestParam(required = false) Type keyword) {
        return questionService.getQuestionList(keyword);
    }
}
