package com.school.sportsmanagement.learners;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class LearnerDtos {
  public record LearnerRequest(
    @NotBlank String admissionNo,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String gender,
    @NotNull LocalDate dateOfBirth,
    @NotBlank String grade,
    String className,
    String guardianPhone,
    String notes
  ) {}

  public record LearnerResponse(
    String id,
    String admissionNo,
    String firstName,
    String lastName,
    String gender,
    LocalDate dateOfBirth,
    String grade,
    String className,
    String guardianPhone,
    String notes
  ) {}
}
