package appjjang.fitpet.infra.feign;

import appjjang.fitpet.domain.auth.dto.response.KakaoTokenLoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static appjjang.fitpet.global.common.constants.SecurityConstants.*;

@FeignClient(name = "kakaoLoginClient", url = KAKAO_LOGIN_URL)
public interface KakaoLoginClient {
    @PostMapping(value = KAKAO_LOGIN_ENDPOINT)
    KakaoTokenLoginResponse getToken(
            @RequestBody String KakaoTokenRequest,
            @RequestHeader(CONTENT_TYPE_KEY) String contentType
    );
}
