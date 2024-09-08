package appjjang.fitpet.domain.compensation.dto;

import appjjang.fitpet.domain.compensation.domain.Compensation;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CompensationDto {
    private Long compensationId;
    private String compensationType;
    private String progress;
    private LocalDate applyDate;
    private String chargePerson;
    private int receiveMoney;

    public CompensationDto(Compensation compensation) {
        this.compensationId = compensation.getId();
        this.compensationType = compensation.getCharge().getType();
        this.progress = compensation.getProgress().getValue();
        this.applyDate = compensation.getRequestDate();
        this.chargePerson = compensation.getChargePerson();
        this.receiveMoney = compensation.getReceiveFee();
    }
}
