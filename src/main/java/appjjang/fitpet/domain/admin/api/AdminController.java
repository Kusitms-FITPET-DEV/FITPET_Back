package appjjang.fitpet.domain.admin.api;

import appjjang.fitpet.domain.admin.application.AdminService;
import appjjang.fitpet.domain.admin.dto.request.AdminInsuranceRequest;
import appjjang.fitpet.domain.question.application.QuestionService;
import appjjang.fitpet.domain.question.dto.request.FaqCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "admin API", description = "관리자 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminservice;
    private final QuestionService questionService;

    @Operation(summary = "admin으로 체결된 보험 등록", description = "MY펫등록을 진행합니다.")
    @PostMapping("/insurance")
    public ResponseEntity<Void> registerInsurance(@RequestBody AdminInsuranceRequest request){
        adminservice.joinInsurance(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "FAQ 생성", description = "관리자가 FQA를 생성합니다.")
    @PostMapping("/questions")
    public ResponseEntity<Void> addQuestion(@RequestBody FaqCreateRequest request) {
        questionService.addQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
