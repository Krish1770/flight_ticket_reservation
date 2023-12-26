package com.example.flightReservation.repository.service;

import com.example.flightReservation.entity.Payment;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepoService {
    Payment findByPnr(String pnr);
}
