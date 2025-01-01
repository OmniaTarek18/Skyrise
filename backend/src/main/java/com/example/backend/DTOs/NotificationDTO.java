package com.example.backend.DTOs;

import com.example.backend.Enums.MessageTitle;
import com.example.backend.Enums.Status;

public record NotificationDTO(
    Integer userId, 
    Integer flightId,
    MessageTitle title, 
    String message, 
    Status status
) {

}
