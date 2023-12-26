package com.example.flightReservation.api;


import com.example.flightReservation.dto.AllocationDto;
import com.example.flightReservation.dto.CancelDto;
import com.example.flightReservation.dto.PaymentDto;
import com.example.flightReservation.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("Allocation")
public interface AllocationApi {

    @PostMapping()
    ResponseEntity<ResponseDto> add(@RequestBody  AllocationDto allocationDto);

    @PostMapping("Payment")
    ResponseEntity<ResponseDto> addPayment(@RequestBody PaymentDto paymentDto);

    @PostMapping("Cancel")
    ResponseEntity<ResponseDto> cancelSeat(@RequestBody CancelDto cancelDto);




}
