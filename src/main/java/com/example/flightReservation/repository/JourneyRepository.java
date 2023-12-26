package com.example.flightReservation.repository;

import com.example.flightReservation.entity.Journey;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JourneyRepository extends JpaRepository<Journey,Long> {

    Optional<List<Journey>> findByBoardingPointAndDepartingPoint(String from, String to);
}
