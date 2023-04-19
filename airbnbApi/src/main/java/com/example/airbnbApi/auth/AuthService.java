package com.example.airbnbApi.auth;

import com.example.airbnbApi.config.JwtService;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.Role;
import com.example.airbnbApi.user.User;
import com.example.airbnbApi.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService   {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {
        var user = Account.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.MEMBER)
                .build();
        userRepository.save(user);

    }



    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Account account = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(new User(account));
        Map<String,String> currentUser = new HashMap<>();
        currentUser.put("id",account.getId().toString());
        currentUser.put("name",account.getName());
        currentUser.put("email",account.getEmail());


        log.info(jwtToken);
        return AuthResponse.builder()
                .token(jwtToken)
                .user(currentUser)
                .build();
    }
}
