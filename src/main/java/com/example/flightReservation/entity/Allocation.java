package com.example.flightReservation.entity;



import com.example.flightReservation.constants.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Allocation {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
    private String bookingId;


//    private Journey journey;

 @JoinColumn(referencedColumnName = "userId",name = "userId")
 @ManyToOne(targetEntity = UserDetails.class,cascade = CascadeType.ALL)
    private UserDetails userId;



    private Date journeyDate;

    private String mobileNumber;

    private String emailId;

    private  Date bookedDate;

    private String flightId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JdbcTypeCode(SqlTypes.JSON)
    private Journey journey;

}
