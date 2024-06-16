package com.example.book_my_show.repositories;

import com.example.book_my_show.models.SeatInShow;
import com.example.book_my_show.models.SeatTypeInShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatTypeInShowRepository extends JpaRepository<SeatTypeInShow,Long> {
    //List<SeatTypeInShow> findAllByShow(Long showId);
    List<SeatTypeInShow> findAllByShow(Long showId);
}
