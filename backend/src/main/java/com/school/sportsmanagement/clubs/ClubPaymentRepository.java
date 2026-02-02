package com.school.sportsmanagement.clubs;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubPaymentRepository extends JpaRepository<ClubPayment, UUID> {
  List<ClubPayment> findByEnrollmentId(UUID enrollmentId);
}
