package appjjang.fitpet.domain.charge.dto.request;

import appjjang.fitpet.domain.charge.domain.Agree;
import appjjang.fitpet.domain.charge.domain.Authentication;
import appjjang.fitpet.domain.charge.domain.Notice;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class InsuranceChargeRequest {
    @Schema(description = "보험 청구 사유", example = "통원")
    private String type;

    @Schema(description = "병원방문일", example = "2024-08-07")
    private LocalDate visitTime;

    @Schema(description = "영수증 URL", example = "http://example.com/receipt")
    private String receiptUrl;

    @Schema(description = "의료비 내역서 URL", example = "http://example.com/medical-expenses")
    private String medicalExpensesUrl;

    @Schema(description = "기타 서류 URL", example = "http://example.com/etc")
    private String etcUrl;

    @Schema(description = "예금주", example = "김앱짱")
    private String accountHolder;

    @Schema(description = "은행", example = "국민은행")
    private String bank;

    @Schema(description = "계좌번호", example = "209801-294-12492")
    private String account;

    @Schema(description = "안내받을 수단", example = "카카오톡")
    private String message;
    @Schema(description = "안내받을 수단", example = "이메일")
    private String email;
    @Schema(description = "안내받을 수단", example = "전화번호")
    private String phone;

    @Schema(description = "동의 항목", example = "필수 동의")
    private Boolean essentialAgree;
    @Schema(description = "동의 항목", example = "선택 동의")
    private Boolean optionalAgree;

    public Authentication toAuthentication() {
        return new Authentication(receiptUrl, medicalExpensesUrl, etcUrl);

    }
}