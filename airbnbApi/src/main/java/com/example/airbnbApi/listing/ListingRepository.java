package com.example.airbnbApi.listing;

import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.Optional;

public interface ListingRepository extends JpaRepository<Listing,Integer>,ListingRepositoryExtension{


    @EntityGraph(attributePaths = {"images","categories","host"})
    Optional<Listing> findById(Integer listing_id);

    @Query("select l  from Listing l where l.id = :listing_id")
    Listing findOnlyId(@Param("listing_id") int listing_id);

}
