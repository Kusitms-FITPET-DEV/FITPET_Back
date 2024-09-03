package appjjang.fitpet.domain.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import static appjjang.fitpet.domain.member.domain.OauthProvider.KAKAO;
@Embeddable
@Getter
@NoArgsConstructor
public class OauthInfo {
    private String oauthId;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;

    private String email;

    @Builder
    public OauthInfo(String oauthId, OauthProvider provider) {
        this.oauthId = oauthId;
        this.provider = provider;
    }

    public static OauthInfo from(OidcUser user) {
        return OauthInfo.builder()
                .oauthId(user.getSubject())
                .provider(KAKAO)
                .build();
    }
}

