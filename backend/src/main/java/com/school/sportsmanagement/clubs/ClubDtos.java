package com.school.sportsmanagement.clubs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class ClubDtos {
  public record ClubRequest(
    @NotBlank String name,
    String description,
    @NotBlank String type,
    BigDecimal feeAmount,
    String feeFrequency,
    @NotNull Integer capacity,
    @NotNull UUID seasonId,
    @NotNull UUID staffId
  ) {}

  public record ClubResponse(
    String id,
    String name,
    String description,
    String type,
    BigDecimal feeAmount,
    String feeFrequency,
    int capacity,
    UUID seasonId,
    UUID staffId
  ) {}

  public record EnrollmentRequest(@NotNull UUID learnerId, @NotBlank String status) {}

  public record EnrollmentResponse(
    String id,
    UUID clubId,
    UUID learnerId,
    String status,
    Instant enrolledAt
  ) {}

  public record PaymentRequest(@NotNull BigDecimal amount, @NotBlank String reference) {}

  public record PaymentResponse(
    String id,
    UUID enrollmentId,
    BigDecimal amount,
    Instant paidAt,
    String reference
  ) {}
}
