package com.example.airbnbApi.listing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseListingListDTO {

    private String email;

    private String title;

    private String location;

    private int price;

    private String description;

    private int roomCount;

    private int bathroomCount;

    private int guestCount;

    private String category;

    private String imgPath;


    @QueryProjection
    public ResponseListingListDTO(String title, String location, int price, String description) {
        this.title = title;
        this.location = location;
        this.price = price;
        this.description = description;
    }
}
