package com.example.airbnbApi.listing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing,Integer> {
}
