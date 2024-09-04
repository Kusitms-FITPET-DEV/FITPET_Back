package appjjang.fitpet.domain.pet.dto.response;

import appjjang.fitpet.domain.coverage.domain.Coverage;
import appjjang.fitpet.domain.coverage.domain.Discount;
import appjjang.fitpet.domain.coverage.domain.Positive;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PetEstimateDetailResponse {
    @Schema(description = "해당 견적의 보험사", example = "petpermint")
    private String insuranceCompany;

    @Schema(description = "해당 견적의 보험료 할인 사항", example = "[\"동물등록우대 할인 2%\", \"다펫 할인 5%\"]")
    private List<String> discountList;

    @Schema(description = "해당 견적의 특징 및 장점", example = "[\"보험금 현장 접수\", \"치과치료(스케일링 등) 보장\", \"아포퀠/사이토포인트 보장\"]")
    private List<String> positiveList;

    @Schema(description = "해당 견적 할인 전 금액", example = "53847")
    private int beforeDiscountFee;

    @Schema(description = "해당 견적의 할인 후 금액", example = "52770")
    private int afterDiscountFee;

    @Schema(description = "치료비 1일 보상한도", example = "15만")
    private String dailyTreatLimit;

    @Schema(description = "수술비 보상한도", example = "250만원 (1일당)")
    private String dailySurgeryCostLimit;

    @Schema(description = "연간 보상한도", example = "2000만원")
    private String yearReward;

    @Schema(description = "슬고관절 수술비", example = "O")
    private String hipJointLimit;

    @Schema(description = "피부병", example = "O")
    private String skinDisease;

    @Schema(description = "아포웰/사이토포인트", example = "O")
    private String skinEtc;

    @Schema(description = "구강질환 보장", example = "O")
    private String oralCoverage;

    @Schema(description = "치과질환 보장", example = "O")
    private String dentalCoverage;

    @Schema(description = "MRI/CT검사 보장", example = "1일 보상한도 보장")
    private String inspectionCoverage;

    @Schema(description = "이물제거 보상", example = "1일 보상한도 보장")
    private String foreignObjectRemoval;

    @Schema(description = "자기부담금", example = "1만원")
    private String selfBurden;

    @Schema(description = "반려견 배상책임", example = "1000 만원")
    private String compensationLiability;

    @Schema(description = "장례비지원금", example = "X")
    private String funeralAid;

    public PetEstimateDetailResponse(String insuranceCompany, int beforeDiscountFee, int afterDiscountFee, Coverage coverage) {
        this.insuranceCompany = insuranceCompany;
        this.beforeDiscountFee = beforeDiscountFee;
        this.afterDiscountFee = afterDiscountFee;
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
        this.positiveList = coverage.getPositiveList().stream()
                .map(Positive::getValue)
                .collect(Collectors.toList());
        this.discountList = coverage.getDiscountList().stream()
                .map(Discount::getValue)
                .collect(Collectors.toList());
    }
}
