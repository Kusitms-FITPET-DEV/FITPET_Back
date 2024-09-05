package appjjang.fitpet.domain.compensation.domain;

import appjjang.fitpet.domain.charge.domain.Charge;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static appjjang.fitpet.domain.compensation.domain.Progress.REGISTER;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compensation_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Progress progress;
    private LocalDate requestDate;
    private String chargePerson;
    private int receiveFee;

    @OneToOne
    @JoinColumn(name = "charge_id")
    private Charge charge;

    @Builder
    private Compensation(Progress progress, LocalDate requestDate, String chargePerson, int receiveFee, Charge charge) {
        this.progress = progress;
        this.requestDate = requestDate;
        this.chargePerson = chargePerson;
        this.receiveFee = receiveFee;
        this.charge = charge;
    }

    public static Compensation createCompensation(Charge charge) {
        return Compensation.builder()
                .progress(REGISTER)
                .requestDate(LocalDate.now())
                .charge(charge)
                .build();
    }

    public Progress updateProgress(Progress progress, String chargePerson, int insuranceFee) {
        switch (progress) {
            case REGISTER:
                this.progress = Progress.ASSIGN;
                this.chargePerson = chargePerson;
                this.receiveFee = insuranceFee;
                break;
            case ASSIGN:
                this.progress = Progress.JUDGE;
                break;
            case JUDGE:
                this.progress = Progress.COMPLETE;
                break;
            default:
                throw new CustomException(ErrorCode.CAN_NOT_CHANGE_PROGRESS);
        }
        return this.progress;
    }
}
