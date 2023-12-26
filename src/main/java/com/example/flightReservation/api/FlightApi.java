package com.example.flightReservation.api;

import com.example.flightReservation.dto.CheckFlightDto;
import com.example.flightReservation.dto.FlightDto;
import com.example.flightReservation.dto.FlightSearchDto;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.entity.Flight;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("Flight")
public interface FlightApi {

    @PostMapping()
     ResponseEntity<ResponseDto> add(@RequestBody FlightDto flightDto);

    @GetMapping()
    ResponseEntity<ResponseDto> get(@RequestBody FlightSearchDto flightSearchDto);

    @GetMapping("specific")
    ResponseEntity<ResponseDto> checkBus(@RequestBody CheckFlightDto checkFlightDto);




}
