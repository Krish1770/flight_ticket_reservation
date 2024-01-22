package com.example.flightReservation.api;


import com.example.flightReservation.dto.ResponseDto;
import com.example.flightReservation.entity.LinkOffer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("LinkOffer")
public interface LinkOfferApi {

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addLinkOffers(@RequestBody  LinkOffer linkOffer);
}
