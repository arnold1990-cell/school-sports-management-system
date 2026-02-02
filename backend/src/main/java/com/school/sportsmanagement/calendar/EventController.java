package com.school.sportsmanagement.calendar;

import com.school.sportsmanagement.common.ApiResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
  private final EventRepository eventRepository;

  public EventController(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @PostMapping
  public ApiResponse<CalendarDtos.EventResponse> create(@Valid @RequestBody CalendarDtos.EventRequest request) {
    Event event = new Event();
    event.setId(UUID.randomUUID());
    event.setSeasonId(request.seasonId());
    event.setTitle(request.title());
    event.setDescription(request.description());
    event.setEventStart(request.eventStart());
    event.setEventEnd(request.eventEnd());
    event.setLocation(request.location());
    event.setCreatedBy(request.createdBy());
    return ApiResponse.ok(toResponse(eventRepository.save(event)));
  }

  @GetMapping
  public ApiResponse<List<CalendarDtos.EventResponse>> listUpcoming() {
    List<Event> events = eventRepository.findByEventStartAfterOrderByEventStartAsc(LocalDateTime.now());
    return ApiResponse.ok(events.stream().map(this::toResponse).toList());
  }

  @PutMapping("/{id}")
  public ApiResponse<CalendarDtos.EventResponse> update(
    @PathVariable UUID id,
    @Valid @RequestBody CalendarDtos.EventRequest request
  ) {
    Event event = eventRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    event.setSeasonId(request.seasonId());
    event.setTitle(request.title());
    event.setDescription(request.description());
    event.setEventStart(request.eventStart());
    event.setEventEnd(request.eventEnd());
    event.setLocation(request.location());
    event.setCreatedBy(request.createdBy());
    return ApiResponse.ok(toResponse(eventRepository.save(event)));
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> delete(@PathVariable UUID id) {
    eventRepository.deleteById(id);
    return ApiResponse.ok(null);
  }

  private CalendarDtos.EventResponse toResponse(Event event) {
    return new CalendarDtos.EventResponse(
      event.getId().toString(),
      event.getSeasonId(),
      event.getTitle(),
      event.getDescription(),
      event.getEventStart(),
      event.getEventEnd(),
      event.getLocation(),
      event.getCreatedBy()
    );
  }
}
