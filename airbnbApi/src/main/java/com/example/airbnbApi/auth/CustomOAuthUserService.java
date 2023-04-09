package com.example.airbnbApi.auth;


import com.example.airbnbApi.auth.provider.OAuth2UserInfo;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.Role;
import com.example.airbnbApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        log.info(oAuth2User);

        log.info(userRequest.getClientRegistration().getRegistrationId());
        log.info("name??  ===== {}" ,userRequest.getClientRegistration().getClientName());
        log.info("name??  ===== {}" ,userRequest.getClientRegistration().getClientId());
        log.info("name??  ===== {}" ,userRequest.getClientRegistration().getRegistrationId());

        String clientName = userRequest.getClientRegistration().getClientName();


        switch (clientName){
            case "GitHub": log.info("github login");
                break;
            case "Google": log.info("google login");
                break;
        }



        return oAuth2User;
    }
}
