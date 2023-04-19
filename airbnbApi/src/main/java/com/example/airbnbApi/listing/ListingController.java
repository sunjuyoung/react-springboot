package com.example.airbnbApi.listing;

import com.example.airbnbApi.listing.dto.RegisterListingDTO;
import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<?> getAllListings(){
        List<ResponseListingListDTO> result = listingService.getAllListings();
        return ResponseEntity.ok().body(result);
    }

}
