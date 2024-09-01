package appjjang.fitpet.domain.insurance.domain;

import appjjang.fitpet.domain.charge.domain.Charge;
import appjjang.fitpet.domain.compensation.domain.Compensation;
import appjjang.fitpet.domain.coverage.domain.Coverage;
import appjjang.fitpet.domain.like.domain.Like;
import appjjang.fitpet.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
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
    private LocalTime startDate;
    private LocalTime endDate;
    private String updateCycle;
    private int insuranceFee;
    private String priceRate;
    private String payWay;
    private String bank;
    private String bankAccount;
    private String payCycle;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Charge> charges = new ArrayList<>();

    @OneToMany(mappedBy = "insurance",cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Compensation> compensations = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="coverage_id")
    private Coverage coverage;

}
