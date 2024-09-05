package appjjang.fitpet.domain.charge.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Authentication {

    private String receiptUrl;
    private String medicalExpensesUrl;
    private String etcUrl;

    @Builder
    public Authentication(String receiptUrl, String medicalExpensesUrl, String etcUrl){
        this.receiptUrl = receiptUrl;
        this.medicalExpensesUrl = medicalExpensesUrl;
        this.etcUrl = etcUrl;
    }
}
