package com.example.airbnbApi.listing;

import com.example.airbnbApi.category.Category;
import com.example.airbnbApi.category.CategoryRepository;
import com.example.airbnbApi.listing.dto.RegisterListingDTO;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
}
