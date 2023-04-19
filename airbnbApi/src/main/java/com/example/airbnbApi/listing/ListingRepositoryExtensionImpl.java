package com.example.airbnbApi.listing;

import com.example.airbnbApi.listing.dto.QResponseListingListDTO;
import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.airbnbApi.listing.QListing.*;

@Transactional(readOnly = true)
public class ListingRepositoryExtensionImpl extends QuerydslRepositorySupport implements ListingRepositoryExtension {


    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public ListingRepositoryExtensionImpl() {
        super(Listing.class);
    }


    @Override
    public List<ResponseListingListDTO> allListings() {

        JPQLQuery<ResponseListingListDTO> result =

        from(listing)
                .select(new QResponseListingListDTO(
                        listing.title,
                        listing.location,
                        listing.price,
                        listing.description
                ))
                ;

        return result.fetch();
    }
}
