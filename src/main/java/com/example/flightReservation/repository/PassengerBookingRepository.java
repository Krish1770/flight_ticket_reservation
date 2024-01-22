package com.example.flightReservation.repository;

import com.example.flightReservation.entity.Allocation;
import com.example.flightReservation.entity.Passenger;
import com.example.flightReservation.entity.PassengerBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PassengerBookingRepository  extends JpaRepository<PassengerBooking,String> {
    Optional<List<PassengerBooking>> findByBooking(Allocation allocation);

    PassengerBooking findByPassenger(Passenger passenger);
}
