package appjjang.fitpet.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoLoginRequest {
    @Schema(description = "카카오 로그인 인증 코드")
    private String code;
}
