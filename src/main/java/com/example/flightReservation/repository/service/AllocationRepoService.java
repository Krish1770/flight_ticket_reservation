package com.example.flightReservation.repository.service;

import com.example.flightReservation.entity.Allocation;
import com.example.flightReservation.entity.Journey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public interface AllocationRepoService {
    Optional<List<Allocation>> findAllByFlightIdAndJourney(String flightId, Optional<Journey> journey1);

    Optional<List<Allocation>> findAllByFlightIdAndJourneyAndJourneyDate(String flightId, Journey journey, Date journeyDate);
}
