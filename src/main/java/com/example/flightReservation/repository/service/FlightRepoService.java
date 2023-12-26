package com.example.flightReservation.repository.service;


import com.example.flightReservation.entity.Flight;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FlightRepoService {
    Optional<List<Flight>> findByJourneyListBoardingPointAndJourneyListDepartingPoint(String from, String to);
}
