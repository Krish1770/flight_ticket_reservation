package com.example.flightReservation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AssignRewardDto {
    private String userId;

    private Long rewardId;
}
