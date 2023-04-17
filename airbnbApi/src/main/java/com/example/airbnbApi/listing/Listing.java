package com.example.airbnbApi.listing;

import com.example.airbnbApi.category.Category;
import com.example.airbnbApi.category.CategoryListing;
import com.example.airbnbApi.common.BaseTime;
import com.example.airbnbApi.common.Photo;
import com.example.airbnbApi.listing.dto.RegisterListingDTO;
import com.example.airbnbApi.review.Review;
import com.example.airbnbApi.user.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Listing extends BaseTime {

    @Id
    @GeneratedValue
    @Column(name = "listing_id")
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String locationValue;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Integer guestCount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int roomCount;

    @Column(nullable = false)
    private int bathroomCount;



    @OneToMany(mappedBy = "listing")
    private List<Review> reviews;

    @ManyToMany(mappedBy = "listings")
    private Set<Category> categories = new HashSet<>();



    @OneToMany
    private List<Photo> photos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account host;



    /**
     * 생성메서드
     */
    public static Listing createListing(Account account, RegisterListingDTO dto, Category category){
        Listing listing = Listing.builder()
                .description(dto.getDescription())
                .title(dto.getTitle())
                .price(dto.getPrice())
                .bathroomCount(dto.getBathroomCount())
                .guestCount(dto.getGuestCount())
                .roomCount(dto.getRoomCount())
                .host(account)
                .categories(Set.of(category))
                .build();
        return listing;
    }



}
