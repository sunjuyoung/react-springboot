package com.example.airbnbApi.listing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class ListingRepositoryExtensionImplTest {

    @Autowired
    ListingRepository listingRepository;

    @Test
    @DisplayName("")
    public void test1(){

    }

}