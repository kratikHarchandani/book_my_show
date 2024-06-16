package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;
    @ManyToOne
    private City city;
    @OneToMany
    private List<Screen> screenList;

}
// Theatre : City
//    1   ->  1
//     M   <-  1

