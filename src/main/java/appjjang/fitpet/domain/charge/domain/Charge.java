package appjjang.fitpet.domain.charge.domain;

import appjjang.fitpet.domain.insurance.domain.Insurance;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private LocalDate visitTime;
    @Embedded private Authentication authentication;
    private String accountHolder;
    private String bank;
    private String account;
    @Embedded private Notice notice;
    @Embedded private Agree agree;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;

    @Builder
    private Charge(String type, LocalDate visitTime, Authentication authentication,
                   String accountHolder, String bank, String account, Notice notice, Agree agree){
        this.type = type;
        this.visitTime = visitTime;
        this.authentication = authentication;
        this.accountHolder = accountHolder;
        this.bank = bank;
        this.account = account;
        this.notice = notice;
        this.agree = agree;
    }

    public static Charge createCharge(String type, LocalDate visitTime, Authentication authentication,
                                      String accountHolder, String bank, String account, Notice notice, Agree agree){
        Charge charge = Charge.builder()
                .type(type)
                .visitTime(visitTime)
                .authentication(authentication)
                .accountHolder(accountHolder)
                .bank(bank)
                .account(account)
                .notice(notice)
                .agree(agree)
                .build();
        return charge;
    }
}
