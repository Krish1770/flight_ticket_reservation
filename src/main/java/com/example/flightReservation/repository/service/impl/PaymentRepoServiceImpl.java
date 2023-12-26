package com.example.flightReservation.repository.service.impl;

import com.example.flightReservation.entity.Payment;
import com.example.flightReservation.repository.PaymentRepository;
import com.example.flightReservation.repository.service.PaymentRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PaymentRepoServiceImpl implements PaymentRepoService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment findByPnr(String pnr) {
        return paymentRepository.findByPnr(pnr);
    }
}
