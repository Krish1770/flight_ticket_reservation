package com.example.flightReservation.config;


import com.mongodb.annotations.Beta;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class mapper {

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

}
