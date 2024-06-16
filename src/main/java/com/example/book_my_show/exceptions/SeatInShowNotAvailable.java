package com.example.book_my_show.exceptions;

public class SeatInShowNotAvailable extends RuntimeException{
    public SeatInShowNotAvailable(String message) {
        super(message);
    }
}
