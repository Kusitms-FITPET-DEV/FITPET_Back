package appjjang.fitpet.domain.compensation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Progress {
    REGISTER("접수"),
    ASSIGN("배정"),
    JUDGE("심사"),
    COMPLETE("완료")
    ;

    private String value;
}
