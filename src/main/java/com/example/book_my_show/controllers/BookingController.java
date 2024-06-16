package com.example.book_my_show.controllers;

import com.example.book_my_show.dtos.BookMovieRequestDto;
import com.example.book_my_show.dtos.BookMovieResponseDto;
import com.example.book_my_show.models.Booking;
import com.example.book_my_show.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;
    @Autowired
    BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }
    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto){
        Booking booking=this.bookingService.bookmovie(bookMovieRequestDto.getUserId(),
                bookMovieRequestDto.getShowId(),bookMovieRequestDto.getSeat_in_showList());
        return null;
    }

}
