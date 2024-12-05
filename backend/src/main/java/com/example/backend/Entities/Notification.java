package com.example.backend.Entities;

import com.example.backend.Entities.CompositeKeys.NotificationPK;
import com.example.backend.Enums.MessageTitle;
import com.example.backend.Enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
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
@IdClass(NotificationPK.class)
public class Notification {
    @Id
    private Integer userId;

    @Id 
    private Integer notificationId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private MessageTitle title;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Status status;
}
