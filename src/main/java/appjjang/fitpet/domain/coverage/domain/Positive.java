package appjjang.fitpet.domain.coverage.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Positive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "positive_id")
    private Long id;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coverage_id")
    private Coverage coverage;

    @Builder
    private Positive(String value, Coverage coverage) {
        this.value = value;
        this.coverage = coverage;
    }

    public static Positive createPositive(String value, Coverage coverage) {
        return Positive.builder()
                .value(value)
                .coverage(coverage
                ).build();
    }
}
