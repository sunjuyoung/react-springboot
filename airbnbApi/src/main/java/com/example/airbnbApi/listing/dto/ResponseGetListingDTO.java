package com.example.airbnbApi.listing.dto;

import com.example.airbnbApi.category.Category;
import com.example.airbnbApi.common.Photo;
import com.example.airbnbApi.listing.Listing;
import com.example.airbnbApi.listing.vo.ReviewVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private Set<String> category;
   //private String category;

    private String latlng;

    private Set<String> imageSrc = new HashSet<>();

    private LocalDate startDate;
    private LocalDate endDate;

    private List<ReviewVO> reviews;


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
        //this.category = listing.getCategories().stream().findFirst().get().getName();
        this.category = listing.getCategories().stream().map(Category::getName).collect(Collectors.toSet());
        for(String image:  listing.getImages()){
            this.imageSrc.add(image);
        }
        this.reviews = listing.getReviews().stream()
                .map(review -> new ReviewVO(review))
                .collect(Collectors.toList());

        this.startDate = listing.getStartDate();
        this.endDate = listing.getEndDate();

    }
}
