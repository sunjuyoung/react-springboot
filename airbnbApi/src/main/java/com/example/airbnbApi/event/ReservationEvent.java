package com.example.airbnbApi.event;

import com.example.airbnbApi.listing.Listing;
import com.example.airbnbApi.reservation.Reservation;
import lombok.Getter;

@Getter
public class ReservationEvent {


    private Listing listing;


    public ReservationEvent(Listing listing) {
        this.listing = listing;
    }
}
