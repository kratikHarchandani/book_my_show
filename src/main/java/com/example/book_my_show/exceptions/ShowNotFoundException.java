package com.example.book_my_show.exceptions;



public class ShowNotFoundException extends RuntimeException {
    public ShowNotFoundException(String message){
        super(message);
    }
}
