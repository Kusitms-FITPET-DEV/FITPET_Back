package appjjang.fitpet.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCodeLoginRequest {
    @Schema(description = "카카오 로그인을 통한 인증코드")
    private String code;
}
