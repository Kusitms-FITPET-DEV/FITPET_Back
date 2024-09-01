package appjjang.fitpet.domain.converage.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Converage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "converage_id")
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
    private String furneralAid;



}
