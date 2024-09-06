package appjjang.fitpet.domain.admin.api;

import appjjang.fitpet.domain.admin.application.AdminService;
import appjjang.fitpet.domain.admin.dto.request.AdminInsuranceRequest;
import appjjang.fitpet.domain.journal.application.JournalService;
import appjjang.fitpet.domain.journal.dto.request.JournalCreateRequest;
import appjjang.fitpet.domain.question.application.QuestionService;
import appjjang.fitpet.domain.question.dto.request.FaqCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "admin API", description = "관리자 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminservice;
    private final QuestionService questionService;
    private final JournalService journalService;

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
    @Operation(summary = "병원일지 생성", description = "관리자가 병원일지를 생성합니다.")
    @PostMapping(value = "/journals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addJournal(
            @RequestPart(value = "request") JournalCreateRequest request,
            @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles) {
        journalService.addJournal(request, imageFiles);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}