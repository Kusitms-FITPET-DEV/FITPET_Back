package appjjang.fitpet.domain.dogsave.domain;

import appjjang.fitpet.domain.common.model.BaseTimeEntity;
import appjjang.fitpet.domain.dogprice.domain.DogPrice;
import appjjang.fitpet.domain.pet.domain.Pet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DogSave extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_save_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "dog_price_id")
    private DogPrice dogPrice;
}
