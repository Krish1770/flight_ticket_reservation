package com.example.flightReservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckFlightResultDto {

    private SeatAllocationDto seatAllocationDto;

    private List<String> previouslyUsedNames;
}
