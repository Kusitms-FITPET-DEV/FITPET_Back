package appjjang.fitpet.domain.catprice.dao;

import appjjang.fitpet.domain.catprice.domain.CatPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatPriceRepository extends JpaRepository<CatPrice, Long> {
}
