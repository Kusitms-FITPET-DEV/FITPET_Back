package appjjang.fitpet.domain.knowledge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KnowledgeType {
    DOG("강아지"),
    CAT("고양이")
    ;

    private String value;
}
