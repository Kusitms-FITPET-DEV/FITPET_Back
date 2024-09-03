package appjjang.fitpet.domain.coverage.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long id;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coverage_id")
    private Coverage coverage;

    @Builder
    private Discount(String value, Coverage coverage) {
        this.value = value;
        this.coverage = coverage;
    }

    public static Discount createDiscount(String value, Coverage coverage) {
        return Discount.builder()
                .value(value)
                .coverage(coverage)
                .build();
    }
}
