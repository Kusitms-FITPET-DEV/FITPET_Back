package appjjang.fitpet.domain.insurance.dao;

import appjjang.fitpet.domain.insurance.domain.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
