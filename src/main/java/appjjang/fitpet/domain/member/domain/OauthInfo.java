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
    private String kakaoRefreshToken;

    @Builder
    public OauthInfo(String oauthId, OauthProvider provider, String kakaoRefreshToken) {
        this.oauthId = oauthId;
        this.provider = provider;
        this.kakaoRefreshToken = kakaoRefreshToken;
    }

    public static OauthInfo from(OidcUser user, String kakaoRefreshToken) {
        return OauthInfo.builder()
                .oauthId(user.getSubject())
                .kakaoRefreshToken(kakaoRefreshToken)
                .provider(KAKAO)
                .build();
    }
}

