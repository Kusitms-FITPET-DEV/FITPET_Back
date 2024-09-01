package appjjang.fitpet.domain.dogprice.domain;

import appjjang.fitpet.domain.doglist.domain.DogList;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DogPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_price_id")
    private Long id;

    private int age;
    private String updateCycle;
    private String grade;
    private String selfBurden;
    private String priceRate;
    private String oneDayReward;
    private String code;
    private int insuranceFee;

    private String insuranceCompany;

    @ManyToOne
    @JoinColumn(name = "dog_list_id")
    private DogList dogList;
}
