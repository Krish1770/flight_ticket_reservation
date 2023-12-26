package com.example.flightReservation.dto;


import ch.qos.logback.core.Layout;
import com.example.flightReservation.entity.LayOut;
import com.example.flightReservation.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatAllocationDto {

    private LayOut layout;

    private List<Seat> seat;
}
