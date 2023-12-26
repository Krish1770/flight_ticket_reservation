package com.example.flightReservation.repository.service.impl;

import com.example.flightReservation.entity.Journey;
import com.example.flightReservation.repository.JourneyRepository;
import com.example.flightReservation.repository.service.JourneyRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JourneyRepoServiceImpl implements JourneyRepoService {

    @Autowired
    private JourneyRepository journeyRepository;
    @Override
    public Optional<List<Journey>> findByBoardingPointAndDepartingPoint(String from, String to) {
        Optional<List<Journey>> journey=journeyRepository.findByBoardingPointAndDepartingPoint(from,to);

        System.out.println(journey.toString());
        return journey;
    }
}
