package com.school.sportsmanagement.sports;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixtureRepository extends JpaRepository<Fixture, UUID> {
  List<Fixture> findByMatchDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
