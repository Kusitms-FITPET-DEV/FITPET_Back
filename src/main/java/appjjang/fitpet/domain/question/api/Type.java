package appjjang.fitpet.domain.question.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    INSURANCE_APPLICATION("보험 신청"),
    GET_ESTIMATE("견적 받기"),
    INSURANCE_CLAIM("보험 청구"),
    REWARD("보상"),
    INSURANCE_FEE("보험료"),
    COVERAGE_RANGE("보장범위"),
    COVERAGE_PERIOD("보장기간"),
    SUBSCRIPTION_PERIOD("가입기간"),
    SUBSCRIPTION_TERM("가입조건"),
    OTHER_COLLATERAL("기타담보")
    ;

    private String value;
}
