package com.example.backend.Entities.CompositeKeys;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class ReservationPK implements Serializable{
    private Integer userId;
    private Integer flightId;
}
