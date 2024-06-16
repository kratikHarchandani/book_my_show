package com.example.book_my_show.services;

import com.example.book_my_show.models.SeatInShow;
import com.example.book_my_show.models.SeatTypeInShow;
import com.example.book_my_show.models.Show;
import com.example.book_my_show.repositories.SeatInShowRepository;
import com.example.book_my_show.repositories.SeatTypeInShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {
    private SeatTypeInShowRepository seatTypeInShowRepository;
    public PriceCalculatorService(SeatTypeInShowRepository seatTypeInShowRepository){
        this.seatTypeInShowRepository=seatTypeInShowRepository;
    }
    public int calculateBookingPrice(List<SeatInShow> seatInShows, Show show){
        int amount=0;
        List<SeatTypeInShow> seatTypeInShowList=this.seatTypeInShowRepository.findAllByShow(show.getId());
        for(SeatInShow seat:seatInShows){
            for(SeatTypeInShow seatTypeInShow:seatTypeInShowList){
                if(seat.getSeat().getSeatType().equals(seatTypeInShow.getSeatType())){
                    amount+=seatTypeInShow.getAmount();
                }
            }
        }
        return amount;
    }
}
