package appjjang.fitpet.domain.doglist.dao;

import appjjang.fitpet.domain.doglist.domain.DogList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogListRepository extends JpaRepository<DogList, Long>, DogListRepositoryCustom {
}
