package com.example.flightReservation.repository;


import com.example.flightReservation.entity.Allocation;
import com.example.flightReservation.entity.Journey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation,String> {
    Optional<List<Allocation>> findAllByFlightIdAndJourney(String flightId, Journey journey1);

    Optional<List<Allocation>> findAllByFlightIdAndJourneyAndJourneyDate(String flightId, Journey journey, Date journeyDate);
}
