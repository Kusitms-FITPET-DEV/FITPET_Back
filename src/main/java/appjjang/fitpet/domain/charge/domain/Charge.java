package appjjang.fitpet.domain.charge.domain;

import appjjang.fitpet.domain.insurance.domain.Insurance;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charge_id")
    private Long id;

    private String type;
    private LocalTime visitTime;
    @Embedded private Authentication authentication;
    private String accountHolder;
    private String bank;
    private String account;
    @Embedded private Notice notice;
    @Embedded private Agree agree;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
}
