package com.example.airbnbApi.listing;

import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ListingRepositoryExtension  {

     List<ResponseListingListDTO> allListings();
}
