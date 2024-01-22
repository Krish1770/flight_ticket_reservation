package com.example.flightReservation.Controller;

import com.example.flightReservation.api.AllocationApi;
import com.example.flightReservation.dto.AllocationDto;
import com.example.flightReservation.dto.CancelDto;
import com.example.flightReservation.dto.PaymentDto;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.util.XMLEventAllocator;
import java.text.ParseException;

@RestController
public class AllocationController implements AllocationApi {

    @Autowired
    private AllocationService allocationService;

    @Override
    public ResponseEntity<ResponseDto> add(AllocationDto allocationDto) {
        return allocationService.add(allocationDto);
    }

    @Override
    public ResponseEntity<ResponseDto> addPayment(PaymentDto paymentDto) throws ParseException {
        return allocationService.addPayment(paymentDto);
    }

    @Override
    public ResponseEntity<ResponseDto> cancelSeat(CancelDto cancelDto) {
        return allocationService.cancelSeat(cancelDto);
    }
}
