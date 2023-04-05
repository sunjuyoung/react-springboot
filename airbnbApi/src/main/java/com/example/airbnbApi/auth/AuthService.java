package com.example.airbnbApi.auth;

import com.example.airbnbApi.config.JwtService;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.Role;
import com.example.airbnbApi.user.User;
import com.example.airbnbApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService   {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = Account.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.MEMBER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(new User(user));
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
