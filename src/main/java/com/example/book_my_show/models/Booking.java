package com.example.book_my_show.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @OneToMany
    private List<Payment> paymentList;

    @ManyToMany
    private List<SeatInShow> seatInShowList;

    @ManyToOne
    private User user;

    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

}
