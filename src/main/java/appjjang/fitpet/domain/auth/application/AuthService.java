package appjjang.fitpet.domain.auth.application;

import appjjang.fitpet.domain.auth.dto.AccessTokenDto;
import appjjang.fitpet.domain.auth.dto.RefreshTokenDto;
import appjjang.fitpet.domain.auth.dto.request.TokenRefreshRequest;
import appjjang.fitpet.domain.auth.dto.response.KakaoTokenLoginResponse;
import appjjang.fitpet.domain.auth.dto.response.TokenPairResponse;
import appjjang.fitpet.domain.member.dao.MemberRepository;
import appjjang.fitpet.domain.member.domain.Member;
import appjjang.fitpet.domain.member.domain.OauthInfo;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final KakaoService kakaoService;
    private final IdTokenVerifier idTokenVerifier;
    private final JwtTokenService jwtTokenService;
    private final MemberRepository memberRepository;

    public TokenPairResponse socialLogin(String code) {
        KakaoTokenLoginResponse response = kakaoService.getIdToken(code);
        OidcUser oidcUser = idTokenVerifier.getOidcUser(response.getId_token());
        OauthInfo oauthInfo = OauthInfo.from(oidcUser, response.getRefresh_token());

        Member member = getMemberByOidcInfo(oauthInfo);
        jwtTokenService.setAuthenticationToken(member.getId(), member.getRole());
        return getLoginResponse(member);
    }

    public TokenPairResponse tokenRefresh(TokenRefreshRequest request) {
        RefreshTokenDto oldRefreshTokenDto =
                jwtTokenService.validateRefreshToken(request.getRefreshToken());

        if (oldRefreshTokenDto == null) {
            throw new CustomException(ErrorCode.EXPIRED_JWT_TOKEN);
        }
        RefreshTokenDto newRefreshTokenDto =
                jwtTokenService.refreshRefreshToken(oldRefreshTokenDto);
        AccessTokenDto accessTokenDto =
                jwtTokenService.refreshAccessToken(getMember(newRefreshTokenDto));
        return new TokenPairResponse(accessTokenDto.getToken(), newRefreshTokenDto.getToken());
    }

    private Member getMember(RefreshTokenDto refreshTokenDto) {
        return memberRepository
                .findById(refreshTokenDto.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private TokenPairResponse getLoginResponse(Member member) {
        String accessToken = jwtTokenService.createAccessToken(member.getId(), member.getRole());
        String refreshToken = jwtTokenService.createRefreshToken(member.getId());
        return new TokenPairResponse(accessToken, refreshToken);
    }

    private Member getMemberByOidcInfo(OauthInfo oauthInfo) {
        return memberRepository
                .findByOauthInfo(oauthInfo)
                .orElseGet(() -> saveMember(oauthInfo));
    }

    private Member saveMember(OauthInfo oauthInfo) {
        return memberRepository.save(Member.createMember(oauthInfo));
    }
}
