package com.school.sportsmanagement.calendar;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<TimetableEntry, UUID> {
  List<TimetableEntry> findBySeasonId(UUID seasonId);
}
