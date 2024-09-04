package appjjang.fitpet.domain.dogprice.dao;

import appjjang.fitpet.domain.dogprice.domain.DogPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogPriceRepository extends JpaRepository<DogPrice, Long> {
}
