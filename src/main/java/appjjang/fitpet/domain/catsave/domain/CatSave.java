package appjjang.fitpet.domain.catsave.domain;

import appjjang.fitpet.domain.catprice.domain.CatPrice;
import appjjang.fitpet.domain.common.model.BaseTimeEntity;
import appjjang.fitpet.domain.pet.domain.Pet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class CatSave extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_save_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "cat_price_id")
    private CatPrice catPrice;
}
