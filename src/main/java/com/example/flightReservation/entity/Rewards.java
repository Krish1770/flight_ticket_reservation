package com.example.flightReservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Rewards {

    @Id
    private Long rewardId;

    private Integer validity;

    private String description;

    private String type;

    private Long rewardValue;


}
