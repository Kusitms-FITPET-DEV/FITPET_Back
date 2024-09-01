package appjjang.fitpet.domain.coverage.domain;

import appjjang.fitpet.domain.compensation.domain.Compensation;
import appjjang.fitpet.domain.insurance.domain.Insurance;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coverage_id")
    private Long id;

    private String dailyTreatLimit;
    private String dailySurgeryCostLimit;
    private String yearReward;
    private String hipJointLimit;
    private String skinDisease;
    private String oralCoverage;
    private String dentalCoverage;
    private String inspectionCoverage;
    private String foreignObjectRemoval;
    private String selfBurden;
    private String compensationLiability;
    private String funeralAid;




}
