package com.example.flightReservation.repository.service.impl;

import com.example.flightReservation.entity.Allocation;
import com.example.flightReservation.entity.Passenger;
import com.example.flightReservation.repository.PassengerRepository;
import com.example.flightReservation.repository.service.PassengerRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PassengerRepoServiceImpl implements PassengerRepoService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Override
    public Optional<List<Passenger>> findByBooking(Allocation allocation1) {
        return passengerRepository.findByBooking(allocation1);
    }
}
