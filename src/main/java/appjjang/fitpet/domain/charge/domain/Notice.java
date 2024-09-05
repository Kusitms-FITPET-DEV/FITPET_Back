package appjjang.fitpet.domain.charge.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Notice {
    private String message;
    private String email;
    private String phone;

    @Builder
    public Notice (String message, String email, String phone){
        this.message = message;
        this.email = email;
        this.phone = phone;
    }
}

