package com.example.airbnbApi.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> ,ReservationRepositoryExtension{


}
