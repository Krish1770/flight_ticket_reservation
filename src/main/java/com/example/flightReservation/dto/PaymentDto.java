package com.example.flightReservation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {

    private String paymentType;

    private Long total;

    private String bookingId;

}
