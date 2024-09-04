package appjjang.fitpet.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoLoginRequest {
    @Schema(description = "카카오 엑세스 토큰")
    private String accessToken;

    @Schema(description = "카카오 엑세스 토큰 만료 시간")
    private String accessTokenExpiresAt;

    @Schema(description = "카카오 리프레시 토큰")
    private String refreshToken;

    @Schema(description = "카카오 리프레시 토큰 만료 시간")
    private String refreshTokenExpiresAt;

    @Schema(description = "카카오 id 토큰")
    private String idToken;
}
