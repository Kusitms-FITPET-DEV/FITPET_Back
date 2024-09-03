package appjjang.fitpet.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SAMPLE_ERROR(HttpStatus.BAD_REQUEST, "AJ4000", "Sample Error Message"),
    BAD_KAKAO_LOGIN_REQUEST(HttpStatus.BAD_REQUEST, "AJ4001", "카카오 로그인 과정에서 오류가 발생했습니다."),
    PRICE_RATE_NOT_EXIST(HttpStatus.BAD_REQUEST, "AJ4002", "잘못된 보상 비율의 요청입니다."),
    INSURANCE_COMPANY_NOT_EXIST(HttpStatus.BAD_REQUEST, "AJ4003", "보험사가 존재하지 않습니다."),
    NOT_PET_OWNER(HttpStatus.BAD_REQUEST, "AJ4004", "해당 펫의 소유자가 아닙니다."),

    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "AJ4010", "만료된 JWT 토큰입니다."),
    ID_TOKEN_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, "AJ4011", "ID 토큰 검증에 실패했습니다."),
    AUTH_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AJ4012", "시큐리티 인증 정보를 찾을 수 없습니다."),
    MISSING_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "AJ4013", "토큰 정보가 존재하지 않습니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "AJ4040", "해당 회원을 찾을 수 없습니다."),
    PET_NOT_FOUND(HttpStatus.NOT_FOUND, "AJ4041", "해당 펫을 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
