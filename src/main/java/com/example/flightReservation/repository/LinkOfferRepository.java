package com.example.flightReservation.repository;


import com.example.flightReservation.entity.LinkOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkOfferRepository extends MongoRepository<LinkOffer,String> {

    LinkOffer getFirstByIsActive(Boolean isActive);
}
