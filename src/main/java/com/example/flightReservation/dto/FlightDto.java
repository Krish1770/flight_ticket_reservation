package com.example.flightReservation.dto;


import com.example.flightReservation.entity.Flight;
import com.example.flightReservation.entity.LayOut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightDto {

    private Flight flight;

    private LayOut layout;
}
