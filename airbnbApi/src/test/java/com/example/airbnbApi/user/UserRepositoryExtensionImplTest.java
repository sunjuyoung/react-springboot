package com.example.airbnbApi.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class UserRepositoryExtensionImplTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("")
    public void test1(){

    }

}