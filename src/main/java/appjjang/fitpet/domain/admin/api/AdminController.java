package appjjang.fitpet.domain.admin.api;

import appjjang.fitpet.domain.admin.application.AdminService;
import appjjang.fitpet.domain.admin.dto.request.AdminInsuranceRequest;

import appjjang.fitpet.domain.banner.application.BannerService;
import appjjang.fitpet.domain.journal.application.JournalService;
import appjjang.fitpet.domain.journal.dto.request.JournalCreateRequest;

import appjjang.fitpet.domain.admin.dto.request.HistoryUpdateRequest;
import appjjang.fitpet.domain.compensationhistory.application.HistoryService;

import appjjang.fitpet.domain.question.application.QuestionService;
import appjjang.fitpet.domain.question.dto.request.FaqCreateRequest;
import appjjang.fitpet.domain.quiz.application.QuizService;
import appjjang.fitpet.domain.quiz.dto.QuizCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
    private final BannerService bannerService;
    private final JournalService journalService;
    private final HistoryService historyService;
    private final QuizService quizService;

    @Operation(summary = "admin으로 체결된 보험 등록", description = "MY펫등록을 진행합니다.")
    @PostMapping("/insurance")
    public ResponseEntity<Void> registerInsurance(@RequestBody AdminInsuranceRequest request) {
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

    @Operation(summary = "보상내역 업데이트", description = "관리자가 보상내역을 업데이트합니다.")
    @PostMapping("/history/{compensationId}")
    public ResponseEntity<Void> updateCompensation(@PathVariable Long compensationId,
                                                   @RequestBody HistoryUpdateRequest request) {
        historyService.updateCompensation(compensationId, request);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "배너 이미지 업로드", description = "배너 이미지를 업로드합니다.")
    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCompensation(@RequestPart MultipartFile file) {
        bannerService.saveBannerImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "퀴즈 등록", description = "퀴즈를 등록합니다.")
    @PostMapping("/quiz")
    public ResponseEntity<Void> createQuiz(@RequestBody QuizCreateRequest request) {
        quizService.saveQuiz(request);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "퀴즈 삭제")
    @DeleteMapping("/quiz/{quizId}")
    public void deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
    }

    @Operation(summary = "FAQ 삭제")
    @DeleteMapping("/question/{quizId}")
    public void deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
    }

    @Operation(summary = "배너 삭제")
    @DeleteMapping("/banner/{bannerId}")
    public void deleteBanner(@PathVariable Long bannerId) {
        bannerService.deleteBanner(bannerId);
    }
}
