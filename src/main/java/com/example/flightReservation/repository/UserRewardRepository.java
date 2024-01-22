package com.example.flightReservation.repository;


import com.example.flightReservation.entity.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward,Long> {

}
