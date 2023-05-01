package com.example.airbnbApi.listing.dto;

import com.example.airbnbApi.listing.Listing;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseListingListDTO {


    private Integer listing_id;

    private Integer user_id;

    private String title;

    private String location;

    private int price;

    private String description;

    private int roomCount;

    private int bathroomCount;

    private int guestCount;

    private String category ;

    private String image_src ;

//
//    @QueryProjection
//    public ResponseListingListDTO(String title, String location, int price, String description) {
//        this.title = title;
//        this.location = location;
//        this.price = price;
//        this.description = description;
//    }


    @QueryProjection
    public ResponseListingListDTO(Integer listing_id, Integer user_id, String title,
                                  String location, int price, String image_src,String category) {
        this.listing_id = listing_id;
        this.user_id = user_id;
        this.title = title;
        this.location = location;
        this.price = price;
        this.image_src = image_src;
        this.category = category;
    }
}
