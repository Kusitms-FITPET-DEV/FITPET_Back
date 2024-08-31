package appjjang.fitpet.domain.doglist.domain;

import appjjang.fitpet.domain.dogprice.domain.DogPrice;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DogList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_list_id")
    private Long id;

    private String important;
    private String breed;

    private String petpermint;

    private String samsung;

    private String db;

    private String hyundai;

    private String kb;
    private String danger;

    private String reparationLimit;

    @OneToMany(mappedBy = "dogList")
    private List<DogPrice> dogPrices;
}
