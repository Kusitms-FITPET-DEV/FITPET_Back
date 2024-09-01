package appjjang.fitpet.domain.doglist.dao;

import java.util.List;

public interface DogListRepositoryCustom {
    List<String> searchBreedByKeyword (String keyword);
}
