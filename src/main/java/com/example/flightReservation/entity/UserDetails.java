package com.example.flightReservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity

public class UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "mobileNumber")
    private String mobileNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "isUserActive")
    private Boolean isUserActive;
//
//    @Column(name = "userName")
//    private Boolean isUser;


}
