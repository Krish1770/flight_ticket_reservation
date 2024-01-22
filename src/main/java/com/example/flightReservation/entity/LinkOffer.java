package com.example.flightReservation.entity;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.FilenameFilter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "linkOffer")
public class LinkOffer {

    @Id
    private String id;

    private String link;

    private Boolean isActive;
}
