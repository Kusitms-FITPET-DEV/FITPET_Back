package appjjang.fitpet.domain.home.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BannerListDto {
    @Schema(description = "배너 url 리스트")
    private List<String> bannerUrlList;
}
