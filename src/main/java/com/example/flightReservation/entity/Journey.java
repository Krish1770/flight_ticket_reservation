package com.example.flightReservation.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long journeyId;

    @Column(name = "boardingTime")
    private LocalTime boardingTime;

    @Column(name = "departingTime")
    private LocalTime departingTime;

    @Column(name = "boardingPoint")
    private String boardingPoint;

    @Column(name = "departingPoint")
    private String departingPoint;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "alloc")
//    private List<Allocation> allocations;
}
