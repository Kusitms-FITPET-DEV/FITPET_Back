package appjjang.fitpet.domain.auth.api;

import appjjang.fitpet.domain.auth.application.AuthService;
import appjjang.fitpet.domain.auth.dto.request.AuthCodeLoginRequest;
import appjjang.fitpet.domain.auth.dto.response.TokenPairResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증 API", description = "인증 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "회원가입 및 로그인", description = "회원가입 및 로그인을 진행합니다.")
    @PostMapping("/login")
    public TokenPairResponse memberOauthLogin(@RequestBody AuthCodeLoginRequest request) {
        return authService.socialLogin(request);
    }
}
