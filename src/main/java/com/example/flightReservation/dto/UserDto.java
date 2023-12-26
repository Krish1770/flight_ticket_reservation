package com.example.flightReservation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    @JsonProperty("userName")
    private String userName;
    @JsonProperty("mobileNumber")
    private String mobileNumber;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("emailId")
    private String emailId;
}
