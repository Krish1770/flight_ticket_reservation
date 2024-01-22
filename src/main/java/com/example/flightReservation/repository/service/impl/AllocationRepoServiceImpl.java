package com.example.flightReservation.repository.service.impl;

import com.example.flightReservation.entity.Allocation;
import com.example.flightReservation.entity.Journey;
import com.example.flightReservation.entity.UserDetails;
import com.example.flightReservation.repository.AllocationRepository;
import com.example.flightReservation.repository.service.AllocationRepoService;
import com.example.flightReservation.service.AllocationService;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AllocationRepoServiceImpl implements AllocationRepoService {

    @Autowired
    private AllocationRepository allocationRepository;

    @Override
    public Optional<List<Allocation>> findAllByFlightIdAndJourney(String flightId, Optional<Journey> journey1) {
        return allocationRepository.findAllByFlightIdAndJourney(flightId,journey1.get());
    }

    @Override
    public Optional<List<Allocation>> findAllByFlightIdAndJourneyAndJourneyDate(String flightId, Journey journey, Date journeyDate) {
        return allocationRepository.findAllByFlightIdAndJourneyAndJourneyDate(flightId,journey,journeyDate);
    }

    @Override
    public Optional<List<Allocation>> findAllByUserId(UserDetails userDetails) {
        return allocationRepository.findAllByUserId(userDetails);
    }
}
