package com.example.flightReservation.Controller;

import com.example.flightReservation.api.FlightApi;
import com.example.flightReservation.dto.CheckFlightDto;
import com.example.flightReservation.dto.FlightDto;
import com.example.flightReservation.dto.FlightSearchDto;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.entity.Flight;
import com.example.flightReservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FlightController implements FlightApi {

    @Autowired
    private FlightService flightService;
    @Override
    public ResponseEntity<ResponseDto> add(FlightDto flightDto) {
      return  flightService.add(flightDto);
    }

    @Override
    public ResponseEntity<ResponseDto> get(FlightSearchDto flightSearchDto) {
        return flightService.get(flightSearchDto);
    }

    @Override
    public ResponseEntity<ResponseDto> checkBus(CheckFlightDto checkFlightDto) {
        return flightService.checkBus(checkFlightDto);
    }


}
