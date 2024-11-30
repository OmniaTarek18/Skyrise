package com.example.backend.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.backend.Entities.Feedback;
import com.example.backend.Entities.CompositeKeys.ReservationPK;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, ReservationPK>, JpaSpecificationExecutor<Feedback> {
    Page<Feedback> findAll(Pageable pageable);
}
