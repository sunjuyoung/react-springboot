package com.example.airbnbApi.reservation;

import com.example.airbnbApi.reservation.dto.ReservationDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;


    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO){
        reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok().body("success");
    }

}
