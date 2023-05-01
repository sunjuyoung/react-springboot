package com.example.airbnbApi.listing;

import com.example.airbnbApi.listing.dto.RegisterListingDTO;
import com.example.airbnbApi.listing.dto.ResponseGetListingDTO;
import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import com.example.airbnbApi.listing.mapper.ListingMapper;
import com.example.airbnbApi.listing.vo.ListingVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/listing")
public class ListingController {

    private final ListingService listingService;
    private final ListingMapper mapper;

    @PostMapping
    public ResponseEntity<?> addListing(@RequestBody RegisterListingDTO dto){
        listingService.createListing(dto);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping
    public ResponseEntity<?> getAllListings(){
        //List<ResponseListingListDTO> result = listingService.getAllListings();
        List<ListingVO> allListings = mapper.getAllListings();
        return ResponseEntity.ok().body(allListings);
    }

    @GetMapping("/{listing_id}")
    public ResponseEntity<?> getListingById(@PathVariable Integer listing_id){
        ResponseGetListingDTO getListing =  listingService.getListingById(listing_id);
        return ResponseEntity.ok().body(getListing);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getListingsByUserId(@PathVariable Integer userId){
        List<ResponseListingListDTO> result = listingService.getListingsByUserId(userId);
        return ResponseEntity.ok().body(result);
    }


}
