package com.example.flightReservation.Controller;

import com.example.flightReservation.api.LinkOfferApi;
import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.entity.LinkOffer;
import com.example.flightReservation.service.LinkOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkOfferController implements LinkOfferApi {

    @Autowired
    private LinkOfferService linkOfferService;
    @Override
    public ResponseEntity<ResponseDto> addLinkOffers(LinkOffer linkOffer) {
        return linkOfferService.addLinkOffers(linkOffer);
    }
}
