package appjjang.fitpet.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SAMPLE_ERROR(HttpStatus.BAD_REQUEST, "AJ4000", "Sample Error Message"),
    BAD_KAKAO_LOGIN_REQUEST(HttpStatus.BAD_REQUEST, "AJ4001", "카카오 로그인 과정에서 오류가 발생했습니다."),

    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "HS4010", "만료된 JWT 토큰입니다."),
    ID_TOKEN_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, "HS4011", "ID 토큰 검증에 실패했습니다."),
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
