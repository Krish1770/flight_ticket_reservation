package com.example.flightReservation.service.impl;


import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.entity.LinkOffer;
import com.example.flightReservation.repository.LinkOfferRepository;
import com.example.flightReservation.service.LinkOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class LinkOfferServiceImpl implements LinkOfferService
{

    @Autowired
    private LinkOfferRepository linkOfferRepository;
    @Override
    public ResponseEntity<ResponseDto> addLinkOffers(LinkOffer linkOffer) {
        LinkOffer savedLinkOffer=linkOfferRepository.save(linkOffer);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"linkOffer Added",savedLinkOffer));

    }
}
