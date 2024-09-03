package appjjang.fitpet.domain.coverage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CoverageCreateRequest {
    private String dailyTreatLimit;
    private String dailySurgeryCostLimit;
    private String yearReward;
    private String hipJointLimit;
    private String skinDisease;
    private String skinEtc;
    private String oralCoverage;
    private String dentalCoverage;
    private String inspectionCoverage;
    private String foreignObjectRemoval;
    private String selfBurden;
    private String compensationLiability;
    private String funeralAid;

    private String insuranceCompany;

    private List<String> positiveList;
    private List<String> discountList;
}
