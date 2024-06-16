package com.example.book_my_show.dtos;

import com.example.book_my_show.models.SeatInShow;
import com.example.book_my_show.models.Show;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDto {
    private List<Long> seat_in_showList;
    private Long userId;
    private Long showId;
}
