package appjjang.fitpet.domain.auth.dto;

import appjjang.fitpet.domain.member.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccessTokenDto {
    private Long memberId;
    private MemberRole memberRole;
    private String token;
}
