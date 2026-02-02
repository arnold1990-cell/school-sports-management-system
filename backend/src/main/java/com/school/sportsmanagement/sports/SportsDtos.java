package com.school.sportsmanagement.sports;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class SportsDtos {
  public record SportRequest(@NotBlank String name) {}
  public record SportResponse(String id, String name) {}

  public record AgeGroupRequest(@NotBlank String name, @NotNull Integer minAge, @NotNull Integer maxAge) {}
  public record AgeGroupResponse(String id, String name, int minAge, int maxAge) {}

  public record TeamRequest(
    @NotBlank String name,
    @NotNull UUID sportId,
    UUID houseId,
    @NotNull UUID ageGroupId,
    @NotNull UUID seasonId
  ) {}
  public record TeamResponse(String id, String name, UUID sportId, UUID houseId, UUID ageGroupId, UUID seasonId) {}

  public record FixtureRequest(
    @NotNull UUID teamId,
    @NotBlank String opponent,
    @NotBlank String venue,
    @NotNull LocalDateTime matchDateTime,
    @NotBlank String homeAway,
    @NotBlank String status
  ) {}
  public record FixtureResponse(
    String id,
    UUID teamId,
    String opponent,
    String venue,
    LocalDateTime matchDateTime,
    String homeAway,
    String status
  ) {}

  public record ResultRequest(
    @NotNull Integer homeScore,
    @NotNull Integer awayScore,
    @NotNull UUID submittedBy,
    String notes
  ) {}

  public record ResultResponse(
    String id,
    UUID fixtureId,
    int homeScore,
    int awayScore,
    UUID submittedBy,
    UUID approvedBy,
    String notes
  ) {}
}
