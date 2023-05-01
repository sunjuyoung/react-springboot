package com.example.airbnbApi.listing.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ListingSearchCondition {

    private String locationValue;

    private LocalDate startDate;
    private LocalDate endDate;
}
