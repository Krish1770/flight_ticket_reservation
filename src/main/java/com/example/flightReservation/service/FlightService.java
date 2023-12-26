package com.example.flightReservation.service;


import com.example.flightReservation.dto.CheckFlightDto;
import com.example.flightReservation.dto.FlightDto;
import com.example.flightReservation.dto.FlightSearchDto;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.entity.Flight;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FlightService {
       ResponseEntity<ResponseDto> add(FlightDto flightDto);

    ResponseEntity<ResponseDto> get(FlightSearchDto flightSearchDto);

    ResponseEntity<ResponseDto> checkBus(CheckFlightDto checkFlightDto);
}
