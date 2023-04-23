package com.example.airbnbApi.listing;

import com.example.airbnbApi.category.QCategory;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.airbnbApi.category.QCategory.category;
import static com.example.airbnbApi.listing.QListing.*;
import static com.querydsl.jpa.JPAExpressions.select;

@Repository
public class ListingTestRepository extends QuerydslRepositorySupport {

    public ListingTestRepository() {
        super(Listing.class);
    }

    public List<Listing> test1(){

        QListing listingSub = new QListing("listingSub");
        select(listing.id,
                JPAExpressions
                        .select(Expressions.stringTemplate("group_concat({0})",category.name).as("categories"))
                        .from(category)
                        .innerJoin(category.listings,listingSub)
                        .groupBy(listingSub.id))

                .from(listing);

        return null;

    }
}
