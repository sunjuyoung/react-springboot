package com.example.airbnbApi.auth.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSocialOAuthResponse {

    private String jwtToken;
    private int user_num;
    private String accessToken;
    private String tokenType;
}
