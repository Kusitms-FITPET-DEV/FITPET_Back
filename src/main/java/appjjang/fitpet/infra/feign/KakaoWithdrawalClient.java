package appjjang.fitpet.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static appjjang.fitpet.global.common.constants.SecurityConstants.*;

@FeignClient(name = "kakaoWithdrawalClient", url = KAKAO_WITHDRAWAL_URL)
public interface KakaoWithdrawalClient {
    @PostMapping(value = KAKAO_WITHDRAWAL_ENDPOINT, consumes = KAKAO_WITHDRAWAL_URL_ENCODING)
    String withdrawal(
            @RequestHeader(AUTHORIZATION) String authorization,
            @RequestParam(name = TARGET_ID_TYPE) String targetIdType,
            @RequestParam(name = TARGET_ID) Long targetId);
}
