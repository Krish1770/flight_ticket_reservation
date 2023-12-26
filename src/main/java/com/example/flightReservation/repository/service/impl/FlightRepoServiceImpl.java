package com.example.flightReservation.repository.service.impl;


import com.example.flightReservation.entity.Flight;
import com.example.flightReservation.repository.FlightRepository;
import com.example.flightReservation.repository.service.FlightRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightRepoServiceImpl implements FlightRepoService {

    @Autowired
    private FlightRepository flightRepository;
    @Override
    public Optional<List<Flight>> findByJourneyListBoardingPointAndJourneyListDepartingPoint(String from, String to) {
        return flightRepository.findByJourneyListBoardingPointAndJourneyListDepartingPoint(from,to);
    }
}
