package appjjang.fitpet.domain.charge.dao;

import appjjang.fitpet.domain.charge.domain.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends JpaRepository<Charge,Long> {
}
