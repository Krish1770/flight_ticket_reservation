package com.example.flightReservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalTime;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "flightDetails")
public class Flight {


    @Id
    private String id;

    private String flightName;

    private String companyName;

    private  Integer totalSeats;

    private List<Seat> seat;

    private LocalTime CheckInTimeGap;

    private Discount discountList;


    private List<Journey> journeyList;

}
