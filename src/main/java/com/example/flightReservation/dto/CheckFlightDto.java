package com.example.flightReservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckFlightDto {
private String userId;

    private String flightId;

    private Date date;

    private String boardingPoint;

    private String DepartingPoint;
}
