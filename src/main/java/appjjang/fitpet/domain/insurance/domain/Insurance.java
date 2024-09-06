package appjjang.fitpet.domain.insurance.domain;

import appjjang.fitpet.domain.charge.domain.Charge;
import appjjang.fitpet.domain.coverage.domain.Coverage;
import appjjang.fitpet.domain.pet.domain.Pet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Long id;

    private String contractor;
    private String insurant;
    private LocalDate startDate;
    private LocalDate endDate;
    private String updateCycle;
    private int insuranceFee;
    private String priceRate;
    private String payWay;
    private String bank;
    private String bankAccount;
    private String payCycle;

    @ManyToOne
    @JoinColumn(name="coverage_id")
    private Coverage coverage;


    @Builder
    private Insurance(String contractor, String insurant, LocalDate startDate, LocalDate endDate,
                      String updateCycle, int insuranceFee, String priceRate, String payWay, String bank, String bankAccount, String payCycle, Coverage coverage, Pet pet){
        this.contractor = contractor;
        this.insurant = insurant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateCycle = updateCycle;
        this.insuranceFee = insuranceFee;
        this.priceRate = priceRate;
        this.payWay = payWay;
        this.bank = bank;
        this.bankAccount = bankAccount;
        this.payCycle = payCycle;
        this.coverage = coverage;
        this.pet = pet;
    }

    public static Insurance createInsurance(String contractor, String insurant,LocalDate startDate,LocalDate endDate,
                                            String updateCycle,int insuranceFee, String priceRate, String payWay, String bank,String bankAccount, String payCycle,Coverage coverage, Pet pet){
        Insurance insurance = Insurance.builder()
                .contractor(contractor)
                .insurant(insurant)
                .startDate(startDate)
                .endDate(endDate)
                .updateCycle(updateCycle)
                .insuranceFee(insuranceFee)
                .priceRate(priceRate)
                .payWay(payWay)
                .bank(bank)
                .bankAccount(bankAccount)
                .payCycle(payCycle)
                .coverage(coverage)
                .pet(pet)
                .build();
        return insurance;
    }

    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Charge> charges = new ArrayList<>();
}
