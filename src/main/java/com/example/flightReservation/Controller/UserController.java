package com.example.flightReservation.Controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.example.flightReservation.api.UserApi;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.dto.UserDto;
import com.example.flightReservation.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;
    @Override
    public ResponseEntity<ResponseDto> add(UserDto userDto) {
        return userService.add(userDto);
    }
}
