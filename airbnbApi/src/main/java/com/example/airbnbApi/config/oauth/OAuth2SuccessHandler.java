package com.example.airbnbApi.config.oauth;

import com.example.airbnbApi.auth.AuthRequest;
import com.example.airbnbApi.auth.AuthService;
import com.example.airbnbApi.config.JwtService;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.User;
import com.example.airbnbApi.user.UserRepository;
import com.example.airbnbApi.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * 스프링시큐리티 기본로직에서 별도의 authenticationSuccessHandler를 지정하지 않으면
     * 로그인 성공이후 SimpleUrlAuthenticationSuccessHandler 를 사용합니다
     * 토큰과 과련된 작업만 추가하기위해 SimpleUrlAuthenticationSuccessHandler 상속받은뒤에
     * onAuthenticationSuccess()메서드를 오버라이드
     */

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);
    public static final String REDIRECT_PATH = "/api/v1/listing";


    private final UserRepository userRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;

    private final JwtService jwtService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Account account = userRepository.findByEmail((String) oAuth2User.getAttributes().get("email")).get();


        String accessToken = jwtService.generateToken(new User(account));
        String targetUrl = getTargetUrl(accessToken);
        addRefreshTokenToCookie(request, response, accessToken);

        clearAuthenticationAttributes(request, response);

        new DefaultRedirectStrategy().sendRedirect(request,response,targetUrl);
      getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }

    private void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();

        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, ACCESS_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)

                .build()
                .toUriString();
    }

}
