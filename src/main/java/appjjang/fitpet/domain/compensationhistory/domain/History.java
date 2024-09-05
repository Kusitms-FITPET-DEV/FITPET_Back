package appjjang.fitpet.domain.compensationhistory.domain;

import appjjang.fitpet.domain.compensation.domain.Compensation;
import appjjang.fitpet.domain.compensation.domain.Progress;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Progress previousProgress;

    @Enumerated(EnumType.STRING)
    private Progress nextProgress;

    private LocalDateTime changeDateTime;
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "compensation_id")
    private Compensation compensation;

    @Builder
    private History(Progress previousProgress, Progress nextProgress, LocalDateTime changeDate, boolean confirmed, Compensation compensation) {
        this.previousProgress = previousProgress;
        this.nextProgress = nextProgress;
        this.changeDateTime = changeDate;
        this.confirmed = confirmed;
        this.compensation = compensation;
    }

    public static History createHistory(Progress previousProgress, Progress nextProgress, Compensation compensation) {
        return History.builder()
                .previousProgress(previousProgress)
                .nextProgress(nextProgress)
                .changeDate(LocalDateTime.now())
                .confirmed(false)
                .compensation(compensation)
                .build();
    }

    public void updateConfirmed() {
        this.confirmed = true;
    }
}
