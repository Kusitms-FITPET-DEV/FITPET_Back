package appjjang.fitpet.domain.catprice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_price_id")
    private Long id;

    private int age;
    private String updateCycle;
    private String selfBurden;
    private String priceRate;
    private String oneDayReward;
    private String code;
    private int insuranceFee;
    private String insuranceCompany;
}
