package com.example.flightReservation.service;


import com.example.flightReservation.dto.AllocationDto;
import com.example.flightReservation.dto.CancelDto;
import com.example.flightReservation.dto.PaymentDto;
import com.example.flightReservation.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AllocationService {
    ResponseEntity<ResponseDto> add(AllocationDto allocationDto);

    ResponseEntity<ResponseDto> addPayment(PaymentDto paymentDto);

    ResponseEntity<ResponseDto> cancelSeat(CancelDto cancelDto);
}
