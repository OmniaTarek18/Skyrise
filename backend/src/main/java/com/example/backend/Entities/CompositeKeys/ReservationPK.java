package com.example.backend.Entities.CompositeKeys;

import java.io.Serializable;

<<<<<<< HEAD
import jakarta.persistence.Column;
=======
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend
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
<<<<<<< HEAD
    @Column(name = "user_id")
=======
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend
    private Integer userId;
    private Integer flightId;
}
