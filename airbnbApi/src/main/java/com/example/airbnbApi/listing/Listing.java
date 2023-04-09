package com.example.airbnbApi.listing;

import com.example.airbnbApi.category.Category;
import com.example.airbnbApi.common.BaseTime;
import com.example.airbnbApi.common.Photo;
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
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Integer maxGuests;

    @Column(nullable = false)
    private String description;



    private int roomCount;
    private int bathroomCount;
    private int guestCount;
    private String locationValue;

    @OneToMany(mappedBy = "listing")
    private List<Review> reviews;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "listing_category",
            joinColumns = @JoinColumn(name = "listing_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();



    @OneToMany
    private List<Photo> photos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account host;

}
