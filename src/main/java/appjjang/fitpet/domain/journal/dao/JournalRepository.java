package appjjang.fitpet.domain.journal.dao;

import appjjang.fitpet.domain.journal.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}
