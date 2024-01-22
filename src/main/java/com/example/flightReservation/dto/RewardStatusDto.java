package com.example.flightReservation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RewardStatusDto {
    private Long userRewardId;
    private Integer statusFlag;
    //1 opened

    //2 used

    //3 expired
}
