package com.example.flightReservation.repository;

import com.example.flightReservation.entity.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {
    Optional<List<Flight>> findByJourneyListBoardingPointAndJourneyListDepartingPoint(String from, String to);
}
