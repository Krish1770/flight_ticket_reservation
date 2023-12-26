package com.example.flightReservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String paymentId;

    private String pnr;

    private Long amount;

    private Date paymentDate;

    private String paymentType;

    @JoinColumn(referencedColumnName = "bookingId",name = "bookingId")
    @ManyToOne(targetEntity = Allocation.class,cascade = CascadeType.ALL)
    private Allocation booking;

    private String status;





}
