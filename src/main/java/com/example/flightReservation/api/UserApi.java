package com.example.flightReservation.api;


import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
public interface UserApi {

    @PostMapping()
    ResponseEntity<ResponseDto> add(@RequestBody UserDto userDto);

}
