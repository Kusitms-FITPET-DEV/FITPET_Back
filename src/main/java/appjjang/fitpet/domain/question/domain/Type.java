package appjjang.fitpet.domain.question.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    REWARD("보상"),
    INSURANCE_FEE("보험료"),
    COVERAGE_RANGE("보장범위"),
    COVERAGE_PERIOD("보장기간"),
    SUBSCRIPTION_TERM("가입조건"),
    OTHER_INFO("기타정보")
    ;

    private String value;
}
