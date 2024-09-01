package appjjang.fitpet.domain.doglist.application;

import appjjang.fitpet.domain.doglist.dao.DogListRepository;
import appjjang.fitpet.domain.doglist.dto.response.BreedSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static appjjang.fitpet.global.common.constants.PetConstants.NO_MATCHING_RESULT_ALTERNATIVE;
import static appjjang.fitpet.global.common.constants.PetConstants.NO_MATCHING_RESULT_COUNT;

@Service
@RequiredArgsConstructor
@Transactional
public class DogListService {
    private final DogListRepository dogListRepository;

    public BreedSearchResponse searchBreed(String keyword) {
        List<String> breedList = dogListRepository.searchBreedByKeyword(keyword);
        if (breedList.isEmpty()) {
            breedList.add(NO_MATCHING_RESULT_ALTERNATIVE);
            return new BreedSearchResponse(NO_MATCHING_RESULT_COUNT, breedList);
        }
        return new BreedSearchResponse(breedList.size(), breedList);
    }
}
