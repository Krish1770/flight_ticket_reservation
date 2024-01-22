package com.example.flightReservation.service.impl;

import com.example.flightReservation.dto.*;
import com.example.flightReservation.entity.*;
import com.example.flightReservation.repository.*;
import com.example.flightReservation.repository.service.AllocationRepoService;
import com.example.flightReservation.repository.service.LinkOfferRepoService;
import com.example.flightReservation.repository.service.PassengerRepoService;
import com.example.flightReservation.repository.service.PaymentRepoService;
import com.example.flightReservation.service.AllocationService;
import com.example.flightReservation.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.nio.file.attribute.AclFileAttributeView;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.flightReservation.constants.BookingStatus.*;


@Service
public class AllocationServiceImpl implements AllocationService {

    @Autowired
    AllocationRepoService allocationRepoService;

    @Autowired
    LinkOfferRepoService linkOfferRepoService;

    @Autowired
    private PassengerBookingRepository passengerBookingRepository;

    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private LinkOfferRepository linkOfferRepository;

    @Autowired
    private RewardRepository rewardRepository;


    @Autowired
    private PassengerRepoService passengerRepoService;

    @Autowired
    private UserRewardRepository userRewardRepository;

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
           allocation.setJourneyDate(allocationDto.getJourneyDate());
           allocation.setFlightId(allocation.getFlightId());


            savedAllocation=allocationRepository.save(allocation);

             for(PassengerDto passengerDto:allocationDto.getPassengerDtoList())
             {

                 Passenger passenger=new Passenger();

                 passenger.setPassengerName(passengerDto.getName());
                 passenger.setAge(passengerDto.getAge());
                 passenger.setGender(passengerDto.getGender());

                 Example<Passenger> example=Example.of(passenger);

                 PassengerBooking passengerBooking=new PassengerBooking();
                 passengerBooking.setBookedDate(new Date());
                 passengerBooking.setSeatType(passengerDto.getSeatType());
                 passengerBooking.setStatus(Blocked);
                 passengerBooking.setSeatId(passengerDto.getSeatNo());

                 passengerBooking.setBooking(savedAllocation);


              Passenger savedPassenger = passengerRepository.save(passenger);

              passengerBooking.setPassenger(savedPassenger);
              passengerBookingRepository.save(passengerBooking);
             }

       }

        BookingResponseDto bookingResponseDto=new BookingResponseDto(savedAllocation,passengerList);

       return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"booking saved",bookingResponseDto));


    }

    @Override
    public ResponseEntity<ResponseDto> addPayment(PaymentDto paymentDto) throws ParseException {

        Optional<Allocation> allocation= allocationRepository.findById(paymentDto.getBookingId());
        Optional<List<PassengerBooking>> passengers=null;

        Payment payment=new Payment();
        if(allocation.isPresent()) {
            passengers = passengerBookingRepository.findByBooking(allocation.get());

            assert passengers.isPresent();
            if (passengers.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, "", ""));
            for (PassengerBooking passenger : passengers.get()) {
                if (passenger.getStatus().toString().equals("Blocked")) {
                    passenger.setStatus(Booked);
                    passengerBookingRepository.save(passenger);
                }

            }

            String pnr = pnrGenerator(allocation.get());
            payment = Payment.builder().
                    booking(allocation.get())
                    .paymentDate(new Date())
                    .amount(paymentDto.getTotal())
                    .pnr(pnr)
                    .paymentType(paymentDto.getPaymentType()).build();

            paymentRepository.save(payment);

            assignReward(allocation.get().getUserId());

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, "payment done and seats are booked", paymentDto.getBookingId()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(HttpStatus.BAD_REQUEST, "error occured", ""));

    }

    @Override
    public ResponseEntity<ResponseDto> cancelSeat(CancelDto cancelDto) {

        String pnr=cancelDto.getPnr();
        Payment payment=paymentRepoService.findByPnr(pnr);

        Allocation allocation=payment.getBooking();

        Optional<List<PassengerBooking>> passengerList=passengerBookingRepository.findByBooking(allocation);

        if(passengerList.isPresent())
        {
            for(PassengerBooking passenger:passengerList.get())
            {
                if(passenger.getSeatType().equals(cancelDto.getSeatType()) && passenger.getStatus().toString().equals("Booked"))
                {
                    if(Long .parseLong(passenger.getSeatId().split("_")[2])== cancelDto.getSeatId())
                    {
                        passenger.setStatus(Cancelled);
                        passengerBookingRepository.save(passenger);
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

             PassengerBooking passengerBooking=passengerBookingRepository.findByPassenger(passenger);
                  long diff=presentDate.getTime()-passengerBooking.getBookedDate().getTime();

                  if(diff>300000)
                  {
                      passengerBooking.setStatus(unBlocked);
                      passengerBookingRepository.save(passengerBooking);
                  }
        }
    }

    private String pnrGenerator(Allocation allocation) {

        String alphabets="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random random=new Random();

        String pnrId="";
//        List<Allocation> allocationList = new ArrayList<>();

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

    public ResponseEntity<ResponseDto> assignReward(UserDetails userDetails) throws ParseException {

        UserReward userReward=new UserReward();

        userReward.setCreatedAt(new Date());

        String rewardType="";

//        Optional<Rewards> rewards=rewardRepository.findById(assignRewardDto.getRewardId());
        Random random=new Random();
       int choice=random.nextInt(0,4);

       Rewards rewards=new Rewards();

         if(choice==1)
         {
          rewardType+="link";


         }
       else if(choice==2)
         {
             rewardType+="cashBack";
         }
       else
         {
             rewardType+="code";
         }



          Rewards rewards1=rewardRepository.getRandomReward(rewardType);

         userReward.setRewardId(rewards1);

       LinkOffer linkOffer=new LinkOffer();
       if(choice==1) {
           linkOffer = linkOfferRepoService.getFirstByIsActive(true);
           userReward.setLinkId(linkOffer.getId());
           linkOffer.setIsActive(false);
           linkOfferRepository.save(linkOffer);
       }

            userReward.setUserId(userDetails);
            userReward.setStatus("alotted");

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH,rewards1.getValidity());
            String expDate= sdf.format(calendar.getTime());
            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(expDate);
            userReward.setExpiryDate(date);
            userReward.setCreatedAt(new Date());

            userRewardRepository.save(userReward);


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"reward added to the user",""));


    }


}
