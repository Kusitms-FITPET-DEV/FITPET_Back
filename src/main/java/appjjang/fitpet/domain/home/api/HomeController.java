package appjjang.fitpet.domain.home.api;

import appjjang.fitpet.domain.home.application.HomeService;
import appjjang.fitpet.domain.home.dto.response.HomeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "홈 API", description = "홈 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

    @Operation(summary = "반려생활 페이지", description = "반려생활 페이지용")
    @GetMapping
    public HomeResponse getHomeData() {
        return homeService.getHomeDate();
    }
}
