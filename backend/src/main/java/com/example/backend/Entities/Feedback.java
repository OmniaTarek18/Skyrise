package com.example.backend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Feedback {
    @Id
    private Integer id ;
    @ManyToOne
    private Flight flight;
}
