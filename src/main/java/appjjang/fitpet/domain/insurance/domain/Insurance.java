package appjjang.fitpet.domain.insurance.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

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
}
