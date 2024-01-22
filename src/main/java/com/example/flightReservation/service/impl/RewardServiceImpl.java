package com.example.flightReservation.service.impl;


import com.example.flightReservation.dto.AssignRewardDto;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.dto.RewardStatusDto;
import com.example.flightReservation.entity.Rewards;
import com.example.flightReservation.entity.UserDetails;
import com.example.flightReservation.entity.UserReward;
import com.example.flightReservation.repository.RewardRepository;
import com.example.flightReservation.repository.UserRewardRepository;
import com.example.flightReservation.service.RewardService;
import org.hibernate.usertype.UserVersionType;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private UserRewardRepository userRewardRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

        @Override
    public ResponseEntity<ResponseDto> changeStatus(RewardStatusDto rewardStatusDto) {

         Optional<UserReward> userReward=userRewardRepository.findById(rewardStatusDto.getUserRewardId());

         if(userReward.isPresent())
         {
             String existingStatus=userReward.get().getStatus();
             if(rewardStatusDto.getStatusFlag()==1)  //used
             {
               if (existingStatus.equals("allotted"))
               {
                   return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.BAD_REQUEST,"cannot be used before opening",""));

               }
               else if(existingStatus.equals("opened"))
               {
                   userReward.get().setStatus("used");
                   userRewardRepository.save(userReward.get());
               }
               else if(existingStatus.equals("used"))
                {
                   return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.BAD_REQUEST,"already used",""));
                }

               else if(existingStatus.equals("expired"))
               {
                   return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.BAD_REQUEST,"the reward got expired",""));

               }
             }
             else if(rewardStatusDto.getStatusFlag()==2)
             {
                 if (existingStatus.equals("allotted"))
                 {
                     userReward.get().setStatus("opened");
                     userRewardRepository.save(userReward.get());

                 }
                 else if(existingStatus.equals("opened"))
                 {
                     return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.BAD_REQUEST,"already opened",""));

                 }
                 else if(existingStatus.equals("used"))
                 {
                     return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.BAD_REQUEST,"already used",""));

                 }

                 else if(existingStatus.equals("expired"))
                 {
                     return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.BAD_REQUEST,"the reward got expired",""));

                 }
             }

         }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.NOT_FOUND,"given id not found",""));
    }

    public static Date convertToDateUsingDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

 @Scheduled(fixedDelay = 30000)
    public void isExpired() throws ParseException {

     Date date=new Date();
     List<UserReward> userRewards=userRewardRepository.findAll();

//     for(UserReward i: userRewards)

     userRewards.forEach(i->
     {
         if(!i.getStatus().equals("expired"))
         {
              if((new Date().getTime()-i.getExpiryDate().getTime())>0)
              {
                  i.setStatus("expired");
              }
         }
     });


     }
 }


