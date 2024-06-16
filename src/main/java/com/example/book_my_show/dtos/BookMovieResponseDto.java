package com.example.book_my_show.dtos;

import com.example.book_my_show.models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private Long bookingId;
    private int amount;
    private ResponseStatus responseStatus;
}
