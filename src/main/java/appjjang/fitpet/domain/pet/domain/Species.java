package appjjang.fitpet.domain.pet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Species {
    DOG("강아지"),
    CAT("고양이")
    ;

    private String value;
}
