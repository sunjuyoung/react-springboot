package com.example.airbnbApi.auth;

import com.example.airbnbApi.auth.provider.OAuthUser;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class OAuthAccountDTO implements OAuth2User {

    private OAuth2User attributes;

    public OAuthAccountDTO( OAuth2User attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.MEMBER.name()));
        return authorities;
    }

    @Override
    public String getName() {
        return attributes.getName();
    }
}
