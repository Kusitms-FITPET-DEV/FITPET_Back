package appjjang.fitpet.domain.charge.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Agree {
    private Boolean essentialAgree;
    private Boolean optionalAgree;

    @Builder
    private Agree(boolean essentialAgree, boolean optionalAgree){
        this.essentialAgree = essentialAgree;
        this.optionalAgree = optionalAgree;
    }
}
