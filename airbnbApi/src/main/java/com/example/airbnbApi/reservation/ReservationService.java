package com.example.airbnbApi.reservation;

import com.example.airbnbApi.listing.Listing;
import com.example.airbnbApi.listing.ListingRepository;
import com.example.airbnbApi.reservation.dto.ReservationDTO;
import com.example.airbnbApi.reservation.dto.ReservationResponseDTO;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;

    public void createReservation(ReservationDTO reservationDTO) {
        Listing listing = listingRepository.findOnlyId(reservationDTO.getListing_id().intValue());
        Account account = userRepository.findOnlyId(reservationDTO.getUser_id().intValue());
        Reservation reservation = new Reservation(reservationDTO,account,listing);
        reservationRepository.save(reservation);
    }

    public List<ReservationResponseDTO> getReservationByUser(Integer userId) {
        List<ReservationResponseDTO> result = reservationRepository.getReservationsByUser(userId);
        return result;
    }

    public void deleteReservation(Integer reservationId,Integer userId) {

    }
}
