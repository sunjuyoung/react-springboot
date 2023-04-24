package com.example.airbnbApi.listing.mapper;

import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import com.example.airbnbApi.listing.vo.ListingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListingMapper {

    List<ListingVO> getAllListings();

    String getTest();
}
