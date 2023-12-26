package com.example.flightReservation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassengerDto {

    private String name;

    private String gender;

    private Integer age;

    private String seatNo;

    private String seatType;

}
