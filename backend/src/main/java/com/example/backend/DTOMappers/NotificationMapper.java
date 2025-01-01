package com.example.backend.DTOMappers;

import com.example.backend.DTOs.NotificationDTO;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.Notification;
import com.example.backend.Entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationMapper {
    
    public static NotificationDTO toDTO(Notification notification) {
        return new NotificationDTO(
            notification.getUserId(),
            notification.getFlight().getId(),
            notification.getTitle(),
            notification.getMessage(),
            notification.getStatus()
        );
    }

    public static Notification toEntity(NotificationDTO dto, Flight flight, User user) {
        Notification entity = Notification.builder()
            .user(user)
            .flight(flight)
            .title(dto.title())
            .message(dto.message())
            .status(dto.status())
            .build();

        return entity;
    }
}
