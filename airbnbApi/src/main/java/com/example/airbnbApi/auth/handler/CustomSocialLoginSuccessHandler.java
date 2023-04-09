package com.example.airbnbApi.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("============");
        log.info(authentication.getPrincipal());

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        log.info(oAuth2User.getAttributes().get("email"));
        log.info(oAuth2User.getAttributes().get("given_name"));

        response.sendRedirect("/");


    }
}
