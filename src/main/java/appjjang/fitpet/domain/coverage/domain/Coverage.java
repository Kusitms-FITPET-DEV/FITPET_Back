package appjjang.fitpet.domain.coverage.domain;

import appjjang.fitpet.domain.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coverage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coverage_id")
    private Long id;

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

    @OneToMany(mappedBy = "coverage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Positive> positiveList = new ArrayList<>();

    @OneToMany(mappedBy = "coverage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discount> discountList = new ArrayList<>();

    @Builder
    private Coverage(String dailyTreatLimit, String dailySurgeryCostLimit, String yearReward, String hipJointLimit, String skinDisease, String skinEtc, String oralCoverage, String dentalCoverage, String inspectionCoverage, String foreignObjectRemoval, String selfBurden, String compensationLiability, String funeralAid, String insuranceCompany, List<Positive> positiveList, List<Discount> discountList) {
        this.dailyTreatLimit = dailyTreatLimit;
        this.dailySurgeryCostLimit = dailySurgeryCostLimit;
        this.yearReward = yearReward;
        this.hipJointLimit = hipJointLimit;
        this.skinDisease = skinDisease;
        this.skinEtc = skinEtc;
        this.oralCoverage = oralCoverage;
        this.dentalCoverage = dentalCoverage;
        this.inspectionCoverage = inspectionCoverage;
        this.foreignObjectRemoval = foreignObjectRemoval;
        this.selfBurden = selfBurden;
        this.compensationLiability = compensationLiability;
        this.funeralAid = funeralAid;
        this.insuranceCompany = insuranceCompany;
        this.positiveList = positiveList;
        this.discountList = discountList;
    }

    public static Coverage createCoverage(String dailyTreatLimit, String dailySurgeryCostLimit, String yearReward, String hipJointLimit, String skinDisease, String skinEtc, String oralCoverage, String dentalCoverage, String inspectionCoverage, String foreignObjectRemoval, String selfBurden, String compensationLiability, String funeralAid, String insuranceCompany, List<String> positiveList, List<String> discountList) {
        Coverage coverage = Coverage.builder()
                .dailyTreatLimit(dailyTreatLimit)
                .dailySurgeryCostLimit(dailySurgeryCostLimit)
                .yearReward(yearReward)
                .hipJointLimit(hipJointLimit)
                .skinDisease(skinDisease)
                .skinEtc(skinEtc)
                .oralCoverage(oralCoverage)
                .dentalCoverage(dentalCoverage)
                .inspectionCoverage(inspectionCoverage)
                .foreignObjectRemoval(foreignObjectRemoval)
                .selfBurden(selfBurden)
                .compensationLiability(compensationLiability)
                .funeralAid(funeralAid)
                .insuranceCompany(insuranceCompany)
                .positiveList(new ArrayList<>())
                .discountList(new ArrayList<>())
                .build();

        coverage.positiveList = positiveList.stream()
                .map(value -> Positive.createPositive(value, coverage))
                .collect(Collectors.toList());
        coverage.discountList = discountList.stream()
                .map(value -> Discount.createDiscount(value, coverage))
                .collect(Collectors.toList());

        return coverage;
    }
}
