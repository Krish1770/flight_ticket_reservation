package com.example.flightReservation.service;

import com.example.flightReservation.dto.AssignRewardDto;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.dto.RewardStatusDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface RewardService {
//    ResponseEntity<ResponseDto> RewardStatusDto(AssignRewardDto assignRewardDto) throws ParseException;

    ResponseEntity<ResponseDto> changeStatus(RewardStatusDto rewardStatusDto);
}
