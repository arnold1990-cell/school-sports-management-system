package com.school.sportsmanagement.calendar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class CalendarDtos {
  public record EventRequest(
    @NotNull UUID seasonId,
    @NotBlank String title,
    String description,
    @NotNull LocalDateTime eventStart,
    @NotNull LocalDateTime eventEnd,
    String location,
    @NotNull UUID createdBy
  ) {}

  public record EventResponse(
    String id,
    UUID seasonId,
    String title,
    String description,
    LocalDateTime eventStart,
    LocalDateTime eventEnd,
    String location,
    UUID createdBy
  ) {}

  public record TimetableRequest(
    @NotNull UUID seasonId,
    @NotBlank String dayOfWeek,
    @NotNull LocalTime startTime,
    @NotNull LocalTime endTime,
    @NotBlank String type,
    String location,
    UUID teamId,
    UUID clubId
  ) {}

  public record TimetableResponse(
    String id,
    UUID seasonId,
    String dayOfWeek,
    LocalTime startTime,
    LocalTime endTime,
    String type,
    String location,
    UUID teamId,
    UUID clubId
  ) {}
}
