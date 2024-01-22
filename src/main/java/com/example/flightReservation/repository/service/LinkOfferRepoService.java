package com.example.flightReservation.repository.service;


import com.example.flightReservation.entity.LinkOffer;
import org.springframework.stereotype.Service;

@Service
public interface LinkOfferRepoService {
    LinkOffer getFirstByIsActive(Boolean isActive);
}
