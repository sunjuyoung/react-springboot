package com.example.airbnbApi.listing.dto;

import com.example.airbnbApi.category.Category;
import com.example.airbnbApi.common.Photo;
import com.example.airbnbApi.listing.Listing;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ResponseGetListingDTO {

    private Integer id;

    private String email;

    private String name;


    private String title;

    private String location;

    private int price;

    private String description;

    private int roomCount;

    private int bathroomCount;

    private int guestCount;

    private String category;

    private String latlng;

    private Set<String> imageSrc = new HashSet<>();

    public ResponseGetListingDTO(Listing listing) {
        this.id = listing.getId();
        this.name = listing.getHost().getName();
        this.email = listing.getHost().getEmail();
        this.title = listing.getTitle();
        this.location = listing.getMap().getLocation();
        this.latlng = listing.getMap().getLatlng().replace("[", "").replace("]", "");;
        this.price = listing.getPrice();
        this.description = listing.getDescription();
        this.roomCount = listing.getRoomCount();
        this.bathroomCount = listing.getBathroomCount();
        this.guestCount = listing.getGuestCount();
        this.category =  listing.getCategories().stream().findFirst().get().getName();
        for(Photo p :  listing.getImages()){
            this.imageSrc.add(p.getFileName());
        }
    }
}
