package appjjang.fitpet.domain.auth.application;

import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import appjjang.fitpet.infra.config.oauth.KakaoProperties;
import appjjang.fitpet.infra.feign.KakaoWithdrawalClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static appjjang.fitpet.global.common.constants.SecurityConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {
    private final KakaoWithdrawalClient kakaoWithdrawalClient;
    private final KakaoProperties properties;

    public void withdrawal(Long oauthId) {
        try {
            kakaoWithdrawalClient.withdrawal(
                    KAKAO_WITHDRAWAL_HEADER_PREFIX + properties.getAdmin(),
                    KAKAO_WITHDRAWAL_TYPE,
                    oauthId);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(ErrorCode.BAD_KAKAO_WITHDRAWAL_REQUEST);
        }
    }
}
