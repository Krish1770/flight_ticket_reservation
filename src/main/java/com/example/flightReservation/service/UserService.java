package com.example.flightReservation.service;


import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.dto.UserDto;

import com.example.flightReservation.entity.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    ResponseEntity<ResponseDto> add(UserDto userDto);

    Optional<UserDetails> isUserPresent(String userId);
}
