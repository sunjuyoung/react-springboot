package com.example.airbnbApi.listing;

import com.example.airbnbApi.category.Category;
import com.example.airbnbApi.listing.dto.ListingSearchCondition;
import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ListingRepositoryExtension  {

     List<ResponseListingListDTO> getListingsByUserId(Integer userId);

     List<ResponseListingListDTO> listingListBySearch(ListingSearchCondition condition, Category category);
}
