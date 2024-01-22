package com.example.flightReservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserReward {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = UserDetails.class,cascade = CascadeType.ALL)
    private UserDetails userId;

    private String status;

    /*
    allotted,expired,used,opened
     */

    private Date createdAt;

    private Date claimedAt;

    private Date expiryDate;

    @ManyToOne(targetEntity = Rewards.class,cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName ="rewardId",name = "rewardId")
    private Rewards rewardId;

    private String linkId;

    private Boolean isScratched;
}
