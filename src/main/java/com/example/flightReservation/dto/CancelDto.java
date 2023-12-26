package com.example.flightReservation.dto;

import jdk.jfr.MetadataDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CancelDto {

    private  Long seatId;

    private String seatType;

    private String pnr;
}
