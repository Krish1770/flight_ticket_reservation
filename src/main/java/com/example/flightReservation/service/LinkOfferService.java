package com.example.flightReservation.service;

import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.entity.LinkOffer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface LinkOfferService {
    ResponseEntity<ResponseDto> addLinkOffers(LinkOffer linkOffer);
}
