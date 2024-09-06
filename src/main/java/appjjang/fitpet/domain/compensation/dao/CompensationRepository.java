package appjjang.fitpet.domain.compensation.dao;

import appjjang.fitpet.domain.compensation.domain.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Long>, CompensationRepositoryCustom {
}
