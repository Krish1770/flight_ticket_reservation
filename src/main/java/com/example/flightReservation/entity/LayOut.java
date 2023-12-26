package com.example.flightReservation.entity;


import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "flight_layout")
public class LayOut{

    @Id
    private String id;

    private Integer[][] businessLayout;

    private Integer[][] economicalLayout;

}
