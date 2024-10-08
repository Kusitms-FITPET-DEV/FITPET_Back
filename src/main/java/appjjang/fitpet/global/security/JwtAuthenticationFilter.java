package appjjang.fitpet.global.security;

import appjjang.fitpet.domain.auth.application.JwtTokenService;
import appjjang.fitpet.domain.auth.dto.AccessTokenDto;
import appjjang.fitpet.global.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessTokenHeaderValue = jwtUtil.extractAccessTokenFromHeader(request);

        if (accessTokenHeaderValue != null) {
            AccessTokenDto accessTokenDto =
                    jwtTokenService.retrieveAccessToken(accessTokenHeaderValue);
            if (accessTokenDto != null) {
                jwtTokenService.setAuthenticationToken(
                        accessTokenDto.getMemberId(), accessTokenDto.getMemberRole());
            }
        }

        filterChain.doFilter(request, response);
    }
}
