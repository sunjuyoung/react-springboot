package com.example.airbnbApi.listing;

import com.example.airbnbApi.category.Category;
import com.example.airbnbApi.category.CategoryRepository;
import com.example.airbnbApi.common.Photo;
import com.example.airbnbApi.common.PhotoRepository;
import com.example.airbnbApi.listing.dto.RegisterListingDTO;
import com.example.airbnbApi.listing.dto.ResponseGetListingDTO;
import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import com.example.airbnbApi.listing.vo.ListingVO;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PhotoRepository photoRepository;

    public void createListing(RegisterListingDTO registerListingDTO){
        Account account = userRepository.findByEmail(registerListingDTO.getEmail()).get();
        Category category = categoryRepository.findByName(registerListingDTO.getCategory());
        Listing listing = Listing.createListing(account,registerListingDTO,category);
        Listing newListing = listingRepository.save(listing);
        if(registerListingDTO.getImgPath() != null && !registerListingDTO.getImgPath().equals("")){
            Photo photo = new Photo(registerListingDTO.getUuid(), registerListingDTO.getImgPath(), null,newListing);
            photoRepository.save(photo);
        }
    }

    public List<ResponseListingListDTO> getAllListings(){
        return  listingRepository.allListings();
    }

    public ResponseGetListingDTO getListingById(Integer listing_id) {
        Listing listing = listingRepository.findById(listing_id)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));

        ResponseGetListingDTO getListingDTO = new ResponseGetListingDTO(listing);
        return getListingDTO;

    }
}
