package com.example.airbnbApi.reservation;

import com.example.airbnbApi.common.BaseTime;
import com.example.airbnbApi.listing.Listing;
import com.example.airbnbApi.user.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation extends BaseTime {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Integer id;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private Boolean paymentStatus;

//    @Column(nullable = false)
//    private String cancellationPolicy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;

    // Getter and Setter methods

//    public boolean isAvailable() {
//        List<Reservation> bookings = property.getBookings();
//        for (Reservation booking : bookings) {
//            if (booking.checkInDate.isBefore(checkOutDate) && booking.checkOutDate.isAfter(checkInDate)) {
//                return false;
//            }
//        }
//        return true;
//    }
}
