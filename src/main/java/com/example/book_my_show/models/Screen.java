package com.example.book_my_show.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String screenNumber;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> featureList;
    @OneToMany
    private List<Seat> seatList;

}
