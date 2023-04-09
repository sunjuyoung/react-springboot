package com.example.airbnbApi;


import com.example.airbnbApi.auth.provider.OAuthUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaAuditing
@RestController
@Log4j2
public class AirbnbApiApplication {



	public static void main(String[] args) {
		SpringApplication.run(AirbnbApiApplication.class, args);
	}






}
