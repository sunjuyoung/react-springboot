package com.example.airbnbApi.auth.oauth;

import com.example.airbnbApi.auth.AuthRequest;
import com.example.airbnbApi.auth.AuthResponse;
import com.example.airbnbApi.auth.AuthService;
import com.example.airbnbApi.auth.RegisterRequest;
import com.example.airbnbApi.config.JwtService;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.Role;
import com.example.airbnbApi.user.User;
import com.example.airbnbApi.user.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class OauthService {

    private final HttpServletResponse response;
    private final GoogleOauth googleOauth;

    private final UserRepository userRepository;
//    private final JwtService jwtService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public void request() throws IOException {
        String oauthRedirectURL = googleOauth.getOauthRedirectURL();
         response.sendRedirect(oauthRedirectURL);
    }

    public AuthResponse oAuthLogin(String code) throws IOException {


        //구글로 일회성 코드를 보내 액세스 토큰이 담긴 응답객체를 받아옴
        ResponseEntity<String> accessTokenResponse = googleOauth.requestAccessToken(code);
        //응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담을 것이다.
        GoogleOAuthToken oAuthToken = googleOauth.getAccessToken(accessTokenResponse);

        //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
        ResponseEntity<String> userInfoResponse = googleOauth.requestUserInfo(oAuthToken);
        //다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화한다.
        GoogleUser googleUser = googleOauth.getUserInfo(userInfoResponse);
        String userEmail = googleUser.getEmail();

        // db와 대조하여 해당 user가 존재하는 지 확인한다.
        Optional<Account> account = userRepository.findAccountByEmail(userEmail);
        if (account.isPresent()) {
            if(account.get().isSocial()){
                AuthRequest authRequest = new AuthRequest();
                authRequest.setEmail(googleUser.getEmail());
                authRequest.setPassword("1234");
                return  authService.authenticate(authRequest);

            }else{
                throw new RuntimeException("이미 가입된 이메일입니다");
            }

        }else {
            authService.register(new RegisterRequest(
                            googleUser.getEmail()
                            ,googleUser.getId()
                            ,passwordEncoder.encode("1234")
                            ,googleUser.getPicture())
                            ,true);

            AuthRequest authRequest = new AuthRequest();
            authRequest.setEmail(googleUser.getEmail());
            authRequest.setPassword(googleUser.getId());
           return  authService.authenticate(authRequest);
        }

    }





}
