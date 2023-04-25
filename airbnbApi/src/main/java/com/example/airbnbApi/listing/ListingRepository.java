package com.example.airbnbApi.listing;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListingRepository extends JpaRepository<Listing,Integer>,ListingRepositoryExtension{


    @EntityGraph(attributePaths = {"images","categories","host"})
    Optional<Listing> findById(Integer listing_id);

}
