package com.example.flightReservation.Controller;

import com.example.flightReservation.api.RewardApi;
import com.example.flightReservation.dto.AssignRewardDto;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.dto.RewardStatusDto;
import com.example.flightReservation.service.RewardService;
import com.example.flightReservation.service.impl.RewardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RestController
public class RewardController implements RewardApi {


    @Autowired
    private RewardService rewardService;
//    @Override
//    public ResponseEntity<ResponseDto> assignReward(AssignRewardDto assignRewardDto) throws ParseException {
//        return rewardService.RewardStatusDto(assignRewardDto);
//    }

    @Override
    public ResponseEntity<ResponseDto> changeStatus(RewardStatusDto rewardStatusDto) {
        return rewardService.changeStatus(rewardStatusDto);
    }
}
