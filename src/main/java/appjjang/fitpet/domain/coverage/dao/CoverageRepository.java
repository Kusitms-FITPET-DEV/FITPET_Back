package appjjang.fitpet.domain.coverage.dao;

import appjjang.fitpet.domain.coverage.domain.Coverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverageRepository extends JpaRepository<Coverage, Long> {
}
