package com.example.book_my_show.services;

import com.example.book_my_show.exceptions.SeatInShowNotAvailable;
import com.example.book_my_show.exceptions.ShowNotFoundException;
import com.example.book_my_show.exceptions.UserNotFoundException;
import com.example.book_my_show.models.*;
import com.example.book_my_show.repositories.BookingRepository;
import com.example.book_my_show.repositories.SeatInShowRepository;
import com.example.book_my_show.repositories.ShowRepository;
import com.example.book_my_show.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private SeatInShowRepository seatInShowRepository;
    private BookingRepository bookingRepository;
    private PriceCalculatorService priceCalculatorService;
    BookingService(UserRepository userRepository,
                   ShowRepository showRepository,
                   SeatInShowRepository seatInShowRepository,
                   BookingRepository bookingRepository,
                   PriceCalculatorService priceCalculatorService){
        this.userRepository=userRepository;
        this.showRepository=showRepository;
        this.seatInShowRepository=seatInShowRepository;
        this.bookingRepository=bookingRepository;
        this.priceCalculatorService=priceCalculatorService;
    }



    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookmovie(Long userId, Long showId, List<Long> seatInShowIds){
         /*
            -------------TAKE A LOCK----------------
            1. Get the user from userId.
            2. Get the show from showId.
            3. Get the list of showSeats from showSeatIds.
            4. Check if all the show seats are available.
            5. If all the show seats are not available then throw an exception.
            6. If all are available, then change the status to be LOCKED.
            7. Change the status in DB as well.
            8. Create the Booking Object, and store it in DB.
            9. Return the Booking Object.
            -----------RELEASE THE LOCK---------------
         */
        Optional<User> userOptional=this.userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("Invalid UserId,please check again");
        }
        User user=userOptional.get();
        Optional<Show> showOptional=this.showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new ShowNotFoundException("Invalid ShowId,please check again");
        }
        Show show=showOptional.get();

        List<SeatInShow> seatInShowList=seatInShowRepository.findAllById(seatInShowIds);
        for(SeatInShow seatinshow:seatInShowList){
            if(!seatinshow.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new SeatInShowNotAvailable("The seat with id: "+seatinshow.getId()+" is not available");
            }
        }

        List<SeatInShow> seatInShowList1=new ArrayList<>();
        for(SeatInShow seatinshow:seatInShowList){
            seatinshow.setShowSeatStatus(ShowSeatStatus.LOCKED);
            seatInShowList1.add(this.seatInShowRepository.save(seatinshow));
        }

        Booking booking=new Booking();
        booking.setUser(user);
        booking.setBookingStatus(BookingStatus.IN_PROGRESS);
        booking.setSeatInShowList(seatInShowList1);
        booking.setCreatedAt(new Date());
        booking.setLastModifiedAt(new Date());
        booking.setAmount(this.priceCalculatorService.calculateBookingPrice(seatInShowList1,show));


        return this.bookingRepository.save(booking);
    }
}
