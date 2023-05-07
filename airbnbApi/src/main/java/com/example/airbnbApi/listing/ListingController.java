package com.example.airbnbApi.listing;

import com.example.airbnbApi.listing.dto.ListingSearchCondition;
import com.example.airbnbApi.listing.dto.RegisterListingDTO;
import com.example.airbnbApi.listing.dto.ResponseGetListingDTO;
import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import com.example.airbnbApi.listing.mapper.ListingMapper;
import com.example.airbnbApi.listing.vo.ListingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/listing")
public class ListingController {

    private final ListingService listingService;
    private final ListingMapper mapper;

    @PostMapping
    public ResponseEntity<?> addListing(@RequestBody RegisterListingDTO dto){
       // log.info(dto.getCategory());
        listingService.createListing(dto);
        return ResponseEntity.ok().body("success");
    }


    @GetMapping
    public ResponseEntity<?> getAllListings(@RequestParam(value = "locationValue",required = false)String locationValue
                                            ,@RequestParam(value = "category",required = false)String category
                                            ,@RequestParam(value = "keyword",required = false)String keyword){
//         ,@RequestParam(value = "startDate",required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate startDate
//          @RequestParam(value = "endDate",required = false) LocalDate endDate

        ListingSearchCondition condition = new ListingSearchCondition(locationValue,category,keyword);
       // List<ResponseListingListDTO> result = listingService.getAllListings(condition);
        List<ListingVO> allListings = mapper.getAllListings(condition);
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
