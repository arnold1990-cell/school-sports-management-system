package com.school.sportsmanagement.seasons;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class SeasonDtos {
  public record SeasonRequest(
    @NotNull Integer year,
    @NotNull Integer term,
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate
  ) {}

  public record SeasonResponse(
    String id,
    int year,
    int term,
    LocalDate startDate,
    LocalDate endDate,
    boolean active
  ) {}
}
