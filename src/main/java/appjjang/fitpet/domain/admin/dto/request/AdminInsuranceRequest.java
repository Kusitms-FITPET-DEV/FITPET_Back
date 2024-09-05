package appjjang.fitpet.domain.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class AdminInsuranceRequest {
    @Schema(description = "등록할 펫 id", example = "")
    private Long petId;  // pet ID
    private String contractor;
    private String insurant;
    private LocalDate startDate;
    private LocalDate endDate;
    private String updateCycle;
    private int insuranceFee;
    private String priceRate;
    private String payWay;
    private String bank;
    private String bankAccount;
    private String payCycle;
    private String insuranceCompany;
}
