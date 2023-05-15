package com.example.airbnbApi.listing;

import com.example.airbnbApi.auth.AuthRequest;
import com.example.airbnbApi.auth.AuthService;
import com.example.airbnbApi.listing.dto.RegisterListingDTO;
import com.example.airbnbApi.user.Account;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListingServiceTest {

    @Autowired
    private ListingService listingService;

    @Autowired
    private AuthService authService;



    @Test
    public void testDummy(){


        for(int i=0; i<2; i++){
            RegisterListingDTO dto = new RegisterListingDTO();
            dto.setCategory(new String[]{"Beach","Lux"});
            dto.setDescription("test description..."+i);
            dto.setTitle("test title..."+i);
            dto.setEmail("test@test.com");
            dto.setEndDate(LocalDate.now());
            dto.setStartDate(LocalDate.now());
            dto.setLatlng("[21,33]");
            dto.setPrice(1000);
            dto.setImages(Set.of("89c8077b-5c85-494c-9c41-fedbcfe0e202_test3.jpg"));
            dto.setBathroomCount(1);
            dto.setRoomCount(1);
            dto.setGuestCount(1);
            dto.setUuid("89c8077b-5c85-494c-9c41-fedbcfe0e202");
            dto.setImgPath("89c8077b-5c85-494c-9c41-fedbcfe0e202_test3.jpg");


            listingService.createListing(dto);


        }

    }

}