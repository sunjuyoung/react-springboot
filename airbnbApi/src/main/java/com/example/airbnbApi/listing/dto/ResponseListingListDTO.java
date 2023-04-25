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


    private Integer id;

    private String email;

    private String title;

    private String location;

    private int price;

    private String description;

    private int roomCount;

    private int bathroomCount;

    private int guestCount;


    private String category ;

    private String imageSrc ;

//
//    @QueryProjection
//    public ResponseListingListDTO(String title, String location, int price, String description) {
//        this.title = title;
//        this.location = location;
//        this.price = price;
//        this.description = description;
//    }


    @QueryProjection
    public ResponseListingListDTO(Integer id, String title, String location, int price, String imageSrc,String category) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.price = price;
        this.imageSrc = imageSrc;
        this.category = category;
    }
}
