package com.example.backend.Entities;

import java.time.LocalDateTime;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Entities.CompositeKeys.ReservationPK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ReservationPK.class)
public class Feedback {

    @Id
    Integer userId;

    @Id
    Integer flightId;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("flightId")
    private Flight flight;

    String comment;

    @Column(nullable = false)
    LocalDateTime dateOfCreation;

    @Column(nullable = false)
    short stars;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    QualityRating punctuality;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    QualityRating comfort;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    QualityRating service;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    QualityRating foodAndBeverage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    QualityRating cleanliness;
}