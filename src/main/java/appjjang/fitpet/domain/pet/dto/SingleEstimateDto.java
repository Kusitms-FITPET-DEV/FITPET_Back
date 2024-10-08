package appjjang.fitpet.domain.pet.dto;

import appjjang.fitpet.domain.catprice.domain.CatPrice;
import appjjang.fitpet.domain.dogprice.domain.DogPrice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SingleEstimateDto {
    @Schema(description = "견적 id")
    private Long priceId;

    @Schema(description = "보험사")
    private String insuranceCompany;

    @Schema(description = "보험이름")
    private String insuranceName;

    @Schema(description = "보험료")
    private int insuranceFee;

    public SingleEstimateDto(DogPrice dogPrice) {
        this.priceId = dogPrice.getId();
        this.insuranceCompany = dogPrice.getInsuranceCompany();
        this.insuranceFee = dogPrice.getInsuranceFee();
    }

    public SingleEstimateDto(CatPrice catPrice) {
        this.priceId = catPrice.getId();
        this.insuranceCompany = catPrice.getInsuranceCompany();
        this.insuranceFee = catPrice.getInsuranceFee();
    }

    public SingleEstimateDto update(String insuranceCompany, String insuranceName) {
        this.insuranceCompany = insuranceCompany;
        this.insuranceName = insuranceName;
        return this;
    }
}
