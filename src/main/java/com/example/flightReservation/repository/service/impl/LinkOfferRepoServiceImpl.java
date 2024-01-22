package com.example.flightReservation.repository.service.impl;


import com.example.flightReservation.entity.LinkOffer;
import com.example.flightReservation.repository.LinkOfferRepository;
import com.example.flightReservation.repository.service.LinkOfferRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkOfferRepoServiceImpl implements LinkOfferRepoService {

    @Autowired
    private LinkOfferRepository linkOfferRepository;
    @Override
    public LinkOffer getFirstByIsActive(Boolean isActive) {
        return linkOfferRepository.getFirstByIsActive(isActive);
    }
}
