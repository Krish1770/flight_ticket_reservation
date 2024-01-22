package com.example.flightReservation.entity;


import com.example.flightReservation.constants.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String passengerId;

    private String passengerName;

    private Integer age;

    private String gender;

    private String aadharId;
}
