package com.example.airbnbApi.listing;

import com.example.airbnbApi.category.Category;
import com.example.airbnbApi.category.CategoryRepository;
import com.example.airbnbApi.listing.dto.RegisterListingDTO;
import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public void createListing(RegisterListingDTO registerListingDTO){
        Account account = userRepository.findByEmail(registerListingDTO.getEmail()).get();
        Category category = categoryRepository.findByName(registerListingDTO.getCategory());
        Listing listing = Listing.createListing(account,registerListingDTO,category);
        listingRepository.save(listing);
    }

    public List<ResponseListingListDTO> getAllListings(){
        return  listingRepository.allListings();
    }
}
