package appjjang.fitpet.domain.auth.api;

import appjjang.fitpet.domain.auth.application.AuthService;
import appjjang.fitpet.domain.auth.dto.response.TokenPairResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static appjjang.fitpet.global.common.constants.SecurityConstants.REDIRECT_LOGIN_CODE;

@Tag(name = "인증 API", description = "인증 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "소셜 로그인 및 회원가입", description = "카카오 소셜 로그인을 이용하여 회원가입 및 로그인을 진행합니다.")
    @GetMapping("/login")
    public TokenPairResponse memberOauthLogin(@RequestParam(REDIRECT_LOGIN_CODE) String code) {
        return authService.socialLogin(code);
    }
}
