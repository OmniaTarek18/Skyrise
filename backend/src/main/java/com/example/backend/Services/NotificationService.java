package com.example.backend.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.backend.DTOMappers.NotificationMapper;
import com.example.backend.DTOMappers.PageResponseMapper;
import com.example.backend.DTOs.NotificationDTO;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.Notification;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.NotificationRepository;
import com.example.backend.Repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public PageResponse<NotificationDTO> getNotification(Integer userId, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 10);
        Page<Notification> notifications = notificationRepository.findByUserId(userId, pageable);
        Page<NotificationDTO> dtos = notifications.map(NotificationMapper::toDTO);
        return PageResponseMapper.toPageResponse(dtos);
    }

    public boolean addNotificationToConcernedUsers(NotificationDTO dto, List<Integer> userIds) {
        Optional<Flight> flight = flightRepository.findFlightByFlightId(dto.flightId());
        if (flight.isEmpty()) {
            throw new EntityNotFoundException("Flight doesn't exist with ID: " + dto.flightId());
        }
        List<Notification> notifications = userIds.stream().map(userId -> {
            Notification notification = NotificationMapper.toEntity(dto, flight.get(), userRepository.findById(userId).get());
            notification.setUserId(userId);
            return notification;
        }).collect(Collectors.toList());
        notificationRepository.saveAll(notifications);

        userIds.forEach(userId -> messagingTemplate.convertAndSend("/topic/user-" + userId, dto));

        return true;
    }
}
