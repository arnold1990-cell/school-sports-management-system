package com.school.sportsmanagement.sports;

import com.school.sportsmanagement.common.ApiResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FixtureController {
  private final FixtureRepository fixtureRepository;
  private final ResultRepository resultRepository;

  public FixtureController(FixtureRepository fixtureRepository, ResultRepository resultRepository) {
    this.fixtureRepository = fixtureRepository;
    this.resultRepository = resultRepository;
  }

  @PostMapping("/fixtures")
  public ApiResponse<SportsDtos.FixtureResponse> createFixture(@Valid @RequestBody SportsDtos.FixtureRequest request) {
    Fixture fixture = new Fixture();
    fixture.setId(UUID.randomUUID());
    fixture.setTeamId(request.teamId());
    fixture.setOpponent(request.opponent());
    fixture.setVenue(request.venue());
    fixture.setMatchDateTime(request.matchDateTime());
    fixture.setHomeAway(request.homeAway());
    fixture.setStatus(request.status());
    Fixture saved = fixtureRepository.save(fixture);
    return ApiResponse.ok(toResponse(saved));
  }

  @GetMapping("/fixtures")
  public ApiResponse<List<SportsDtos.FixtureResponse>> listFixtures(
    @RequestParam(required = false) LocalDateTime start,
    @RequestParam(required = false) LocalDateTime end
  ) {
    List<Fixture> fixtures = (start != null && end != null)
      ? fixtureRepository.findByMatchDateTimeBetween(start, end)
      : fixtureRepository.findAll();
    return ApiResponse.ok(fixtures.stream().map(this::toResponse).toList());
  }

  @PostMapping("/fixtures/{id}/result")
  public ApiResponse<SportsDtos.ResultResponse> submitResult(
    @PathVariable UUID id,
    @Valid @RequestBody SportsDtos.ResultRequest request
  ) {
    Fixture fixture = fixtureRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Fixture not found"));
    Result result = new Result();
    result.setId(UUID.randomUUID());
    result.setFixtureId(fixture.getId());
    result.setHomeScore(request.homeScore());
    result.setAwayScore(request.awayScore());
    result.setSubmittedBy(request.submittedBy());
    result.setNotes(request.notes());
    Result saved = resultRepository.save(result);
    return ApiResponse.ok(new SportsDtos.ResultResponse(
      saved.getId().toString(),
      saved.getFixtureId(),
      saved.getHomeScore(),
      saved.getAwayScore(),
      saved.getSubmittedBy(),
      saved.getApprovedBy(),
      saved.getNotes()
    ));
  }

  private SportsDtos.FixtureResponse toResponse(Fixture fixture) {
    return new SportsDtos.FixtureResponse(
      fixture.getId().toString(),
      fixture.getTeamId(),
      fixture.getOpponent(),
      fixture.getVenue(),
      fixture.getMatchDateTime(),
      fixture.getHomeAway(),
      fixture.getStatus()
    );
  }
}
