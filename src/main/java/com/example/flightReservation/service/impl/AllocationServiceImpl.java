package com.example.flightReservation.service.impl;

import com.example.flightReservation.dto.*;
import com.example.flightReservation.entity.*;
import com.example.flightReservation.repository.AllocationRepository;
import com.example.flightReservation.repository.JourneyRepository;
import com.example.flightReservation.repository.PassengerRepository;
import com.example.flightReservation.repository.PaymentRepository;
import com.example.flightReservation.repository.service.AllocationRepoService;
import com.example.flightReservation.repository.service.PassengerRepoService;
import com.example.flightReservation.repository.service.PaymentRepoService;
import com.example.flightReservation.service.AllocationService;
import com.example.flightReservation.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.nio.file.attribute.AclFileAttributeView;
import java.sql.Time;
import java.util.*;

import static com.example.flightReservation.constants.BookingStatus.*;


@Service
public class AllocationServiceImpl implements AllocationService {

    @Autowired
    AllocationRepoService allocationRepoService;

    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PassengerRepoService passengerRepoService;

    @Autowired
    private PaymentRepoService paymentRepoService;



    @Transactional
    @Override
    public ResponseEntity<ResponseDto> add(AllocationDto allocationDto) {


        Allocation savedAllocation=new Allocation();
        List<Passenger> passengerList=new ArrayList<>();
        Optional<UserDetails> userDetails=userDetailsService.isUserPresent(allocationDto.getUserId());
       if(userDetails.isPresent())
       {
           Allocation allocation=new Allocation();
           allocation.setUserId(userDetails.get());
           allocation.setBookedDate(new Date());
           allocation.setEmailId(allocationDto.getEmailId());
           allocation.setMobileNumber(allocationDto.getMobileNumber());
          allocation.setFlightId(allocationDto.getFlightId());
           Optional<Journey> journey=journeyRepository.findById(allocationDto.getJourneyId());

           journey.ifPresent(allocation::setJourney);
//         if( journey.isPresent() )
//             allocation.setJourney(journey.get());
           allocation.setJourneyDate(allocationDto.getJourneyDate());
           allocation.setFlightId(allocation.getFlightId());


            savedAllocation=allocationRepository.save(allocation);

             for(PassengerDto passengerDto:allocationDto.getPassengerDtoList())
             {

                 Passenger passenger=new Passenger();

                 passenger.setPassengerName(passengerDto.getName());
                 passenger.setAge(passengerDto.getAge());
                 passenger.setGender(passengerDto.getGender());

//                 Example<Passenger> example=Example.of(passenger);

                 passenger.setBookedDate(new Date());
                 passenger.setSeatType(passengerDto.getSeatType());
                 passenger.setStatus(Blocked);
                 passenger.setSeatId(passengerDto.getSeatNo());

                 passenger.setBooking(savedAllocation);


              Passenger savedPassenger = passengerRepository.save(passenger);
             }

       }

        BookingResponseDto bookingResponseDto=new BookingResponseDto(savedAllocation,passengerList);

       return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"booking saved",bookingResponseDto));


    }

    @Override
    public ResponseEntity<ResponseDto> addPayment(PaymentDto paymentDto) {

        Optional<Allocation> allocation= allocationRepository.findById(paymentDto.getBookingId());
        Optional<List<Passenger>> passengers=null;

        Payment payment=new Payment();
        if(allocation.isPresent())
         passengers =passengerRepository.findByBooking(allocation.get());

        if(passengers.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"",""));
        for(Passenger passenger: passengers.get())
        {
            if(passenger.getStatus().toString().equals("Blocked"))
            {
                passenger.setStatus(Booked);
                passengerRepository.save(passenger);
            }

        }

        String pnr=pnrGenerator(allocation.get());
        payment=Payment.builder().
                booking(allocation.get())
                .paymentDate(new Date())
                .amount(paymentDto.getTotal())
                .pnr(pnr)
                .paymentType(paymentDto.getPaymentType()).build();

        paymentRepository.save(payment);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"payment done and seats are booked",paymentDto.getBookingId()));

    }

    @Override
    public ResponseEntity<ResponseDto> cancelSeat(CancelDto cancelDto) {

        String pnr=cancelDto.getPnr();
        Payment payment=paymentRepoService.findByPnr(pnr);

        Allocation allocation=payment.getBooking();

        Optional<List<Passenger>> passengerList=passengerRepoService.findByBooking(allocation);

        if(passengerList.isPresent())
        {
            for(Passenger passenger:passengerList.get())
            {
                if(passenger.getSeatType().equals(cancelDto.getSeatType()) && passenger.getStatus().toString().equals("Booked"))
                {
                    if(Long .parseLong(passenger.getSeatId().split("_")[2])== cancelDto.getSeatId())
                    {
                        passenger.setStatus(Cancelled);
                        passengerRepository.save(passenger);
                        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"ticket cancelled",""));
                    }
                }
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND,"given value not found",""));

    }

        @Scheduled(fixedDelay = 10000)
    public void changingBlocktoUnBlock()
    {
        List<Passenger> passengerList=passengerRepository.findAll();

        for(Passenger passenger:passengerList)
        {
             Date presentDate=new Date();

            System.out.println("checking");
                  long diff=presentDate.getTime()-passenger.getBookedDate().getTime();

                  if(diff>300000)
                  {
                      passenger.setStatus(unBlocked);
                      passengerRepository.save(passenger);
                  }
        }
    }

    private String pnrGenerator(Allocation allocation) {

        String alphabets="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random random=new Random();

        String pnrId="";

      Optional <List<Allocation>>allocationList=allocationRepoService.findAllByFlightIdAndJourneyAndJourneyDate(allocation.getFlightId(),allocation.getJourney(),allocation.getJourneyDate());

      List<String> pnrList=new ArrayList<>();
      for(Allocation allocation1:allocationList.get())
      {
              paymentRepository.findByBooking(allocation1).get().forEach(payments ->
                     pnrList.add(payments.getPnr()) );
      }
        while(pnrId.isEmpty() || pnrList.contains(pnrId)) {
            pnrId="";
            int initial = random.nextInt(100);
            pnrId += initial;
            for (int i = 0; i < 4; i++) {
                int k = random.nextInt(52);

                pnrId += alphabets.charAt(k);
            }

            int last = random.nextInt(100);
            pnrId += last;

            System.out.println(pnrId);
        }
        return pnrId;
    }
}
