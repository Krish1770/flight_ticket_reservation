package com.example.flightReservation.repository;

import com.example.flightReservation.entity.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RewardRepository extends JpaRepository<Rewards,Long> {

    @Query(nativeQuery=true, value="SELECT *  FROM Rewards WHERE type=type ORDER BY random() LIMIT 1")
    public Rewards getRandomReward(@Param("type") String type);
}
