package com.example.flightReservation.entity;

import com.example.flightReservation.constants.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PassengerBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String passengerBookingId;

    @JoinColumn(referencedColumnName = "passengerId",name = "passenger")
    @ManyToOne(targetEntity = Passenger.class,cascade = CascadeType.ALL)
    private Passenger passenger;


    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private String seatId;

    private String seatType;

    private Date bookedDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "bookingId")
    private Allocation booking;



}
