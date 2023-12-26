package com.example.flightReservation.repository;

import com.example.flightReservation.entity.LayOut;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayOutRepository extends MongoRepository<LayOut,String> {
}
