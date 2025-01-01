package com.example.backend.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.backend.DTOs.NotificationDTO;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Services.NotificationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<PageResponse<NotificationDTO>> getNotifications(@PathVariable Integer userId, @RequestParam int pageNum) {
        PageResponse<NotificationDTO> notifications = notificationService.getNotification(userId, pageNum);
        return ResponseEntity.ok(notifications);
    }
}
