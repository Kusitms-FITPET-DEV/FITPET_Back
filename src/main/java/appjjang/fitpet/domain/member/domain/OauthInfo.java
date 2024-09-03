package appjjang.fitpet.domain.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import static appjjang.fitpet.domain.member.domain.OauthProvider.KAKAO;
import static appjjang.fitpet.global.common.constants.SecurityConstants.NICKNAME;
import static appjjang.fitpet.global.common.constants.SecurityConstants.PICTURE;

@Embeddable
@Getter
@NoArgsConstructor
public class OauthInfo {
    private String oauthId;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;

    private String profileUrl;
    private String nickname;
    private String email;

    @Builder
    public OauthInfo(String oauthId, OauthProvider provider, String profileUrl, String nickname, String email) {
        this.oauthId = oauthId;
        this.provider = provider;
        this.profileUrl = profileUrl;
        this.nickname = nickname;
        this.email = email;
    }

    public static OauthInfo from(OidcUser user) {
        return OauthInfo.builder()
                .oauthId(user.getSubject())
                .provider(KAKAO)
                .profileUrl(user.getAttributes().get(PICTURE).toString())
                .nickname(user.getClaim(NICKNAME))
                .email(user.getEmail())
                .build();
    }
}

