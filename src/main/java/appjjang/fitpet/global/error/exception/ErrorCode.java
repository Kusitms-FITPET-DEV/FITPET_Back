package appjjang.fitpet.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SAMPLE_ERROR(HttpStatus.BAD_REQUEST, "AJ4000", "Sample Error Message"),
    BAD_KAKAO_WITHDRAWAL_REQUEST(HttpStatus.BAD_REQUEST, "AJ4001", "카카오 회원탈퇴 과정에서 오류가 발생했습니다."),
    PRICE_RATE_NOT_EXIST(HttpStatus.BAD_REQUEST, "AJ4002", "잘못된 보상 비율의 요청입니다."),
    INSURANCE_COMPANY_NOT_EXIST(HttpStatus.BAD_REQUEST, "AJ4003", "보험사가 존재하지 않습니다."),
    NOT_PET_OWNER(HttpStatus.BAD_REQUEST, "AJ4004", "해당 펫의 소유자가 아닙니다."),
    CAN_NOT_CHANGE_PROGRESS(HttpStatus.BAD_REQUEST, "AJ4005", "과정 변경이 불가능합니다."),

    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "AJ4010", "만료된 JWT 토큰입니다."),
    AUTH_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AJ4011", "시큐리티 인증 정보를 찾을 수 없습니다."),
    MISSING_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "AJ4012", "토큰 정보가 존재하지 않습니다."),
    AUDIENCE_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, "AJ4013", "ID 토큰의 Audience 검증에 실패했습니다."),
    ISSUER_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, "AJ4014", "ID 토큰의 Issuer 검증에 실패했습니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "AJ4040", "해당 회원을 찾을 수 없습니다."),
    PET_NOT_FOUND(HttpStatus.NOT_FOUND, "AJ4041", "해당 펫을 찾을 수 없습니다."),
    ESTIMTE_NOT_FOUND(HttpStatus.NOT_FOUND, "AJ4042", "해당 견적을 찾을 수 없습니다."),
    COMPENSATION_NOT_FOUND(HttpStatus.NOT_FOUND, "AJ4043", "해당 보상 정보를 찾을 수 없습니다."),
    HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "AJ4044", "보상 내역을 찾을 수 없습니다."),
    INSURANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "AJ4043", "해당 펫의 보험을 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
