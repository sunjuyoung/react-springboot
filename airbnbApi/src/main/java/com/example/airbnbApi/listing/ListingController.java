package com.example.airbnbApi.listing;

import com.example.airbnbApi.listing.dto.RegisterListingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/listing")
public class ListingController {

    private final ListingService listingService;

    @PostMapping
    public ResponseEntity<?> addListing(@RequestBody RegisterListingDTO dto){
        listingService.createListing(dto);
        return ResponseEntity.ok().body("success");
    }

}
