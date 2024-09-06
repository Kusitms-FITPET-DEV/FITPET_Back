package appjjang.fitpet.domain.insurance.dto;

import appjjang.fitpet.domain.insurance.domain.Insurance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ContractDto {
    @Schema(description = "계약자", example = "김이름")
    private String contractor;

    @Schema(description = "피보험자", example = "보리")
    private String insurant;

    @Schema(description = "보험 시작 날짜", example = "2024.09.05")
    private String startDate;

    @Schema(description = "보험 종료 날짜", example = "2024.09.05")
    private String endDate;

    @Schema(description = "보험 갱신 주기", example = "3년")
    private String updateCycle;

    @Schema(description = "보험료", example = "10000")
    private int insuranceFee;

    @Schema(description = "보상비율", example = "70%")
    private String priceRate;

    @Schema(description = "납입 방법", example = "자동이체(25일)")
    private String payWay;

    @Schema(description = "납입 계좌 은행", example = "카카오뱅크")
    private String bank;

    @Schema(description = "납입 계좌", example = "****12341234")
    private String bankAccount;

    @Schema(description = "납입 주기", example = "월납")
    private String payCycle;

    public ContractDto(Insurance insurance, String startDate, String endDate, String bankAccount) {
        this.contractor = insurance.getContractor();
        this.insurant = insurance.getInsurant();
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateCycle = insurance.getUpdateCycle();
        this.insuranceFee = insurance.getInsuranceFee();
        this.priceRate = insurance.getPriceRate();
        this.payWay = insurance.getPayWay();
        this.bank = insurance.getBank();
        this.bankAccount = bankAccount;
        this.payCycle = insurance.getPayCycle();
    }
}
