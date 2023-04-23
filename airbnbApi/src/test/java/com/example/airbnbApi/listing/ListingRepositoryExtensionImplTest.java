package com.example.airbnbApi.listing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListingRepositoryExtensionImplTest {

    @Autowired
    ListingRepository listingRepository;

    @Test
    @Rollback(value = false)
    public void test1(){
        listingRepository.allListings();
    }

}