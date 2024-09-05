package appjjang.fitpet.domain.charge.api;

import appjjang.fitpet.domain.charge.application.ChargeService;
import appjjang.fitpet.domain.charge.dto.request.InsuranceChargeRequest;
import appjjang.fitpet.domain.insurance.dao.InsuranceRepository;
import appjjang.fitpet.domain.insurance.domain.Insurance;
import appjjang.fitpet.domain.pet.dao.PetRepository;
import appjjang.fitpet.domain.pet.domain.Pet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static appjjang.fitpet.domain.insurance.domain.QInsurance.insurance;

@Tag(name = "보험 청구 API", description = "보험 청구 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/charge")
public class ChargeController {
    private final ChargeService chargeService;
    private final InsuranceRepository insuranceRepository;
    private final PetRepository petRepository;

    @Operation(summary = "청구 신청", description = "가입된 보험사에 보험금 신청합니다.")
    @PostMapping("/{petId}")
    public ResponseEntity<Void> registerCharge(@PathVariable Long petId, @RequestBody InsuranceChargeRequest request) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found")); // 값이 없을 경우 예외 던지기

        Insurance insurance = pet.getInsurance();
        chargeService.saveCharge(request, insurance);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "이미지 업로드", description = "보험 청구에 필요한 이미지를 업로드합니다.")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> uploadDocuments(
            @RequestPart("receipt") MultipartFile receipt,
            @RequestPart("medicalExpenses") MultipartFile medicalExpenses,
            @RequestPart("etc") MultipartFile etc) {

        Map<String, String> urls = chargeService.uploadDocuments(receipt, medicalExpenses, etc);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(urls);
    }


}
