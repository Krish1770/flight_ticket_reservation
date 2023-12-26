package com.example.flightReservation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightSearchDto {
    private String  boardingPoint;
    private String departingPoint;
    private Date journeyDate;
}
