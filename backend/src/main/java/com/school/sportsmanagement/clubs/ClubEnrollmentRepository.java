package com.school.sportsmanagement.clubs;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubEnrollmentRepository extends JpaRepository<ClubEnrollment, UUID> {
  List<ClubEnrollment> findByClubId(UUID clubId);
}
