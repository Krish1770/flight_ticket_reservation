package com.example.flightReservation.service.impl;


import com.example.flightReservation.dto.*;
import com.example.flightReservation.entity.*;
import com.example.flightReservation.repository.*;
import com.example.flightReservation.repository.service.AllocationRepoService;
import com.example.flightReservation.repository.service.FlightRepoService;
import com.example.flightReservation.repository.service.JourneyRepoService;
import com.example.flightReservation.repository.service.PassengerRepoService;
import com.example.flightReservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private JourneyRepoService journeyRepoService;

@Autowired
private AllocationRepoService allocationRepoService;

    @Autowired
    private LayOutRepository layOutRepository;

    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    private PassengerBookingRepository passengerBookingRepository;
@Autowired
private FlightRepository flightRepository;

@Autowired
 private   FlightRepoService flightRepoService;

@Autowired
private PassengerRepoService passengerRepoService;

@Autowired
private UserRepository userRepository;

@Autowired
private JourneyRepository journeyRepository;
    @Override
    public ResponseEntity<ResponseDto> add(FlightDto flightDto) {

        Flight flight=flightDto.getFlight();
       List<Journey> journeyList=new ArrayList<>();

       List<Journey> journeyList1=flight.getJourneyList();


       for (Journey journey : journeyList1)
       {
           Journey savedJourney=journeyRepository.save(journey);
           journeyList.add(savedJourney);
       }

       flight.setJourneyList(journeyList);


        LayOut layout=flightDto.getLayout();

        Flight savedFlight=flightRepository.save(flight);
            layout.setId(savedFlight.getId());
        layOutRepository.save(layout);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"flight info saved",savedFlight));
    }

    @Override
    public ResponseEntity<ResponseDto> get(FlightSearchDto flightSearchDto) {

        String from=flightSearchDto.getBoardingPoint();
        String to= flightSearchDto.getDepartingPoint();

//        Optional<List<Journey>> journeyList=journeyRepoService.findByBoardingPointAndDepartingPoint(from,to);

          Optional<List<Flight>> flightList=flightRepoService.findByJourneyListBoardingPointAndJourneyListDepartingPoint(from,to);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"flightList list",flightList));
    }

    @Override
    public ResponseEntity<ResponseDto> checkBus(CheckFlightDto checkFlightDto) {

        String from=checkFlightDto.getBoardingPoint();
        String to= checkFlightDto.getDepartingPoint();

        Optional<Flight> flight=flightRepository.findById(checkFlightDto.getFlightId());

        Integer[][] copyOfBusinessLayout=layOutRepository.findById(flight.get().getId()).get().getBusinessLayout();

        Integer[][] copyOfEconomicLayout=layOutRepository.findById(flight.get().getId()).get().getEconomicalLayout();

        Long journeyId=0L;
        Date journeyDate=checkFlightDto.getDate();



      for(Journey journey:flight.get().getJourneyList()) {

          if (journey.getDepartingPoint().equals(to) && journey.getBoardingPoint().equals(from)) {
              journeyId = journey.getJourneyId();
          }
      }
          Optional<Journey>journey1 =journeyRepository.findById(journeyId);

        Optional<List<Allocation>> allocation=allocationRepoService.findAllByFlightIdAndJourney(checkFlightDto.getFlightId(),journey1);

//ArrayList<String> bookingIdList=new ArrayList<>();
//allocation.get().stream().forEach(i->bookingIdList.add(i.getBookingId()));


         for(Allocation allocation1:allocation.get())
          {
              Optional<List< PassengerBooking >> passengers=passengerBookingRepository.findByBooking(allocation1);

              for (PassengerBooking passenger:passengers.get())
              {

                  if(passenger.getStatus().toString().equals("Blocked") || passenger.getStatus().toString().equals("Booked")) {
                      String seat = passenger.getSeatId();

                      String[] arr = seat.split(" ")[0].split("_");

                      if (passenger.getSeatType().equals("Economy")) {
                          copyOfEconomicLayout[Integer.parseInt(arr[0]) - 1][Integer.parseInt(arr[1]) - 1] = 0;
                      } else {
                          copyOfBusinessLayout[Integer.parseInt(arr[0]) - 1][Integer.parseInt(arr[1]) - 1] = 0;
                      }
                  }
              }


          }

         List<String> previouslyUsedNames=new ArrayList<>();

         Optional<UserDetails> userDetails=userRepository.findById(checkFlightDto.getUserId());
         Optional<List<Allocation>> allocationList=allocationRepoService.findAllByUserId(userDetails.get());


        allocationList.ifPresent(allocations -> allocations.forEach(i -> passengerBookingRepository.findByBooking(i).get().forEach(j -> previouslyUsedNames.add(j.getPassenger().getPassengerName()))));







      LayOut layOut=new LayOut();
      layOut.setBusinessLayout(copyOfBusinessLayout);
      layOut.setEconomicalLayout(copyOfEconomicLayout);

      List<Seat> seat=flight.get().getSeat();

        SeatAllocationDto seatAllocationDto=new SeatAllocationDto(layOut,seat);

        CheckFlightResultDto checkFlightResultDto =new CheckFlightResultDto(seatAllocationDto,previouslyUsedNames);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK,"layout sent",checkFlightResultDto));
    }
}
