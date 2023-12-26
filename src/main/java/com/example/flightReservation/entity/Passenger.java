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
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String passengerId;

    private String passengerName;

    private Integer age;

    private String gender;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private String seatId;

    private String seatType;

    private Date bookedDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "bookingId")
    private Allocation booking;

}
