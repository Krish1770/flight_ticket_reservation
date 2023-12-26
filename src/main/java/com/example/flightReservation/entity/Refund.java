package com.example.flightReservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String refundId;

    private Boolean isFullCancellation;

    private String referenceId;

    private Date refundDate;
    /*
      isFullCancellation = true the referenceId=AllocationId;
      else referenceId=passengerId;
     */

}
