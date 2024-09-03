package appjjang.fitpet.domain.member.dto.response;

import appjjang.fitpet.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MemberInfoResponse {
    @Schema(description = "카카오에서의 닉네임", example = "김이름")
    private String nickname;

    @Schema(description = "카카오와 연결된 이메일", example = "appjjang@gmail.com")
    private String email;

    @Schema(description = "카카오에서의 프로필 이미지", example = "https://img1.kakaocdn.net/thumb/R110x110.q70/?fname=https://t1.kakaocdn.net/account_images/default_profile.jpeg")
    private String profileUrl;

    public MemberInfoResponse(Member member) {
        this.nickname = member.getOauthInfo().getNickname();
        this.email = member.getOauthInfo().getEmail();
        this.profileUrl = member.getOauthInfo().getProfileUrl();
    }
}
