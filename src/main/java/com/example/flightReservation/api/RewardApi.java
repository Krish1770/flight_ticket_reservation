package com.example.flightReservation.api;


import com.example.flightReservation.dto.AssignRewardDto;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.dto.RewardStatusDto;
import jdk.jfr.Registered;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

@RequestMapping("reward")
public interface RewardApi {


//    @PostMapping("/assign")
//    public ResponseEntity<ResponseDto> assignReward(@RequestBody AssignRewardDto assignRewardDto) throws ParseException;

    @PostMapping("status")
    public ResponseEntity<ResponseDto> changeStatus(@RequestBody RewardStatusDto rewardStatusDto);
}
