package appjjang.fitpet.domain.insurance.dto;

import appjjang.fitpet.domain.coverage.domain.Coverage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CoverageDto {
    @Schema(description = "치료비 1일 보상한도", example = "15만원")
    private String dailyTreatLimit;

    @Schema(description = "수술비 1일 보상한도", example = "300만원 (연2회)")
    private String dailySurgeryCostLimit;

    @Schema(description = "연간 보상한도", example = "1500만원")
    private String yearReward;

    @Schema(description = "슬고관절 수술비", example = "O")
    private String hipJointLimit;

    @Schema(description = "피부병", example = "O")
    private String skinDisease;

    @Schema(description = "아포퀠/사이토포인트", example = "O")
    private String skinEtc;

    @Schema(description = "구강질환 보장", example = "O")
    private String oralCoverage;

    @Schema(description = "치과질환 보장", example = "X")
    private String dentalCoverage;

    @Schema(description = "치료비 1일 보상한도", example = "1일 보상한도 보장")
    private String inspectionCoverage;

    @Schema(description = "이물제거 보장", example = "1일 보상한도 보장")
    private String foreignObjectRemoval;

    @Schema(description = "자기부담금", example = "1만원")
    private String selfBurden;

    @Schema(description = "반려견 배상책임", example = "3000 만원")
    private String compensationLiability;

    @Schema(description = "장례지원금", example = "15만원 (만8세이상 장례비 제외)")
    private String funeralAid;

    public CoverageDto(Coverage coverage) {
        this.dailyTreatLimit = coverage.getDailyTreatLimit();
        this.dailySurgeryCostLimit = coverage.getDailySurgeryCostLimit();
        this.yearReward = coverage.getYearReward();
        this.hipJointLimit = coverage.getHipJointLimit();
        this.skinDisease = coverage.getSkinDisease();
        this.skinEtc = coverage.getSkinEtc();
        this.oralCoverage = coverage.getOralCoverage();
        this.dentalCoverage = coverage.getDentalCoverage();
        this.inspectionCoverage = coverage.getInspectionCoverage();
        this.foreignObjectRemoval = coverage.getForeignObjectRemoval();
        this.selfBurden = coverage.getSelfBurden();
        this.compensationLiability = coverage.getCompensationLiability();
        this.funeralAid = coverage.getFuneralAid();
    }
}
