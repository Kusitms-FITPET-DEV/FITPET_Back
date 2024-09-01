package appjjang.fitpet.domain.charge.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Agree {
    private Boolean essentialAgree;
    private Boolean optionalAgree;

    private Agree(boolean essentialAgree, boolean optionalAgree){
        this.essentialAgree = essentialAgree;
        this.optionalAgree = optionalAgree;
    }
}
