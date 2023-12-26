package com.example.flightReservation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllocationDto {

    private Date journeyDate;

    private String mobileNumber;

    private String emailId;

    private String flightId;

    private Long journeyId;

    private String userId;

    private List<PassengerDto> passengerDtoList;


}
