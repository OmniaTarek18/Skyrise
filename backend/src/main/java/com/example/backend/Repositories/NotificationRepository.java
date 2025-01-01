package com.example.backend.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.CompositeKeys.NotificationPK;
import com.example.backend.Entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, NotificationPK> {
    Page<Notification> findByUserId(Integer userId, Pageable pageable);
}

