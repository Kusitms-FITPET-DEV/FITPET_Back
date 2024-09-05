package appjjang.fitpet.domain.compensationhistory.dao;

import appjjang.fitpet.domain.compensationhistory.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}
