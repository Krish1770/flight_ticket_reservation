package com.example.flightReservation.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seat {
        private String seatType;

        private String seatRange;

        private Long price;

        private Long cabinBag;

        private Long checkInBag;

        private Long totalSeats;

        private Long bookingLimit; //threshold value for overbooking

}
