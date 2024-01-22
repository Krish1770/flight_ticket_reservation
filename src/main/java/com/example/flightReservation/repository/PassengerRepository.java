package com.example.flightReservation.repository;


import com.example.flightReservation.entity.Allocation;
import com.example.flightReservation.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,String> {


    Optional<Passenger> findByAadharId(String aadharId);
}
