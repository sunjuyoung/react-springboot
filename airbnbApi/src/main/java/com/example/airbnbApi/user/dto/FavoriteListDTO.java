package com.example.airbnbApi.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FavoriteListDTO {

    private Integer listing_id;

    private Integer user_id;

    private String location;

    private int price;

    private String categories;

    private String image_src;

    private Integer  favorites;

    @QueryProjection
    public FavoriteListDTO(Integer listing_id, Integer user_id, String location,
                           int price, String categories, String image_src,Integer favorites) {
        this.listing_id = listing_id;
        this.user_id = user_id;
        this.location = location;
        this.price = price;
        this.categories = categories;
        this.image_src = image_src;
        this.favorites = favorites;
    }
}
