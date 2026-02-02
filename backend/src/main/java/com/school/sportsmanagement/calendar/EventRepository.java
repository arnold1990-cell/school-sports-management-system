package com.school.sportsmanagement.calendar;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, UUID> {
  List<Event> findByEventStartAfterOrderByEventStartAsc(LocalDateTime start);
}
