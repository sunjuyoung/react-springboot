package com.example.airbnbApi.reservation;

import com.example.airbnbApi.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> ,ReservationRepositoryExtension{




}
