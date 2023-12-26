package com.example.flightReservation.repository;

import com.example.flightReservation.entity.Allocation;
import com.example.flightReservation.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
    Optional<List<Payment>> findByBooking(Allocation allocation);

    Payment findByPnr(String pnr);
}
