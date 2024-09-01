package appjjang.fitpet.domain.doglist.api;

import appjjang.fitpet.domain.doglist.application.DogListService;
import appjjang.fitpet.domain.doglist.dto.response.BreedSearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "펫 API", description = "펫 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class DogListController {
    private final DogListService dogListService;

    @Operation(summary = "견종 검색", description = "펫 등록을 위한 견종 검색을 진행합니다.")
    @GetMapping("/breeds/search")
    public BreedSearchResponse searchDogBreeds(@RequestParam String keyword) {
        return dogListService.searchBreed(keyword);
    }
}
