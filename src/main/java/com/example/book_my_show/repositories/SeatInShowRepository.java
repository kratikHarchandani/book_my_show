package com.example.book_my_show.repositories;

import com.example.book_my_show.models.SeatInShow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatInShowRepository extends JpaRepository<SeatInShow,Long> {
    @Override
    Optional<SeatInShow> findById(Long aLong);


    //List<SeatInShow> findAllById(List<Long> showseatids);

    @Override
    SeatInShow  save(SeatInShow entity);
}
