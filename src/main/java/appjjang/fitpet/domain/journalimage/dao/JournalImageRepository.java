package appjjang.fitpet.domain.journalimage.dao;

import appjjang.fitpet.domain.journalimage.domain.JournalImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalImageRepository extends JpaRepository<JournalImage, Long> {
}
