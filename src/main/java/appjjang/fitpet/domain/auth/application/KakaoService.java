package appjjang.fitpet.domain.auth.application;

import appjjang.fitpet.domain.auth.dto.request.KakaoTokenLoginRequest;
import appjjang.fitpet.domain.auth.dto.response.KakaoTokenLoginResponse;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import appjjang.fitpet.infra.config.oauth.KakaoProperties;
import appjjang.fitpet.infra.feign.KakaoLoginClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static appjjang.fitpet.global.common.constants.SecurityConstants.LOGIN_CONTENT_TYPE_VALUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {
    private final KakaoLoginClient kakaoLoginClient;
    private final KakaoProperties properties;

    public KakaoTokenLoginResponse getIdToken(String code) {
        try {
            return kakaoLoginClient.getToken(
                    KakaoTokenLoginRequest.newInstance(properties, code).toString(),
                    LOGIN_CONTENT_TYPE_VALUE
                    );
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(ErrorCode.BAD_KAKAO_LOGIN_REQUEST);
        }
    }
}
