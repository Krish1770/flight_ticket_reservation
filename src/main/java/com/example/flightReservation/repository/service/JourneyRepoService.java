package com.example.flightReservation.repository.service;

import com.example.flightReservation.entity.Journey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface JourneyRepoService {
    Optional<List<Journey>> findByBoardingPointAndDepartingPoint(String from, String to);
}
