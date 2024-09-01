package appjjang.fitpet.domain.compensation.domain;

import appjjang.fitpet.domain.insurance.domain.Insurance;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compensation_id")
    private Long id;

    private String type;
    private LocalTime requestDate;
    private String chargePerson;
    private int receiveFee;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
}
