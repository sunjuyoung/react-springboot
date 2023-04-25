package com.example.airbnbApi.listing;

import com.example.airbnbApi.category.QCategory;
import com.example.airbnbApi.listing.dto.QResponseListingListDTO;
import com.example.airbnbApi.listing.dto.ResponseListingListDTO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.expression.common.ExpressionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.airbnbApi.category.QCategory.*;
import static com.example.airbnbApi.listing.QListing.*;
import static com.querydsl.jpa.JPAExpressions.select;

public class ListingRepositoryExtensionImpl extends QuerydslRepositorySupport implements ListingRepositoryExtension {


    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public ListingRepositoryExtensionImpl() {
        super(Listing.class);
    }


    @Override
    public List<ResponseListingListDTO> allListings() {

        QListing listingSub = new QListing("listingSub");

        JPQLQuery<ResponseListingListDTO> categories =
                select(new QResponseListingListDTO(listing.id
                , listing.title
                , listing.location
                , listing.price
                , listing.imageSrc,

                select( Expressions.stringTemplate("string_agg({0}, ',')", category.name, ",").as("category"))
                        .from(category)
                        .innerJoin(category.listings, listingSub)
                        .groupBy(listingSub.id)))

                .from(listing);

        return null;

    }
}
