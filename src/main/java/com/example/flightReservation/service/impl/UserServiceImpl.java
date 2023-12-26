package com.example.flightReservation.service.impl;

import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.dto.UserDto;
import com.example.flightReservation.entity.UserDetails;
import com.example.flightReservation.repository.UserRepository;
import com.example.flightReservation.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<ResponseDto> add(UserDto userDto) {

        UserDetails user= UserDetails.builder().emailId(userDto.getEmailId()).
                gender(userDto.getGender()).
                mobileNumber(userDto.getUserName())
                        .userName(userDto.getMobileNumber()).build();


       UserDetails savedUser=userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"user saved",savedUser));
    }

    @Override
    public Optional<UserDetails> isUserPresent(String userId) {
        return userRepository.findById(userId);
    }
}
