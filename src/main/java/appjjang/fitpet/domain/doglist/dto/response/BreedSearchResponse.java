package appjjang.fitpet.domain.doglist.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BreedSearchResponse {
    @Schema(description = "검색 결과 견종 리스트 수")
    private int searchCount;

    @Schema(description = "검색 결과 견종 리스트")
    private List<String> searchedBreeds;
}
