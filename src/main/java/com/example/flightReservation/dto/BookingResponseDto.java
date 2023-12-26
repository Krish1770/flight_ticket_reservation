package com.example.flightReservation.dto;

import com.example.flightReservation.entity.Allocation;
import com.example.flightReservation.entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingResponseDto {

    private Allocation allocation;

    private List<Passenger> passengerList;
}
