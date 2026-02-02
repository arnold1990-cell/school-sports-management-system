package com.school.sportsmanagement.seasons;

import com.school.sportsmanagement.common.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seasons")
public class SeasonController {
  private final SeasonRepository seasonRepository;

  public SeasonController(SeasonRepository seasonRepository) {
    this.seasonRepository = seasonRepository;
  }

  @PostMapping
  public ApiResponse<SeasonDtos.SeasonResponse> create(@Valid @RequestBody SeasonDtos.SeasonRequest request) {
    Season season = new Season();
    season.setId(UUID.randomUUID());
    season.setYear(request.year());
    season.setTerm(request.term());
    season.setStartDate(request.startDate());
    season.setEndDate(request.endDate());
    season.setActive(false);
    return ApiResponse.ok(toResponse(seasonRepository.save(season)));
  }

  @GetMapping
  public ApiResponse<List<SeasonDtos.SeasonResponse>> list() {
    return ApiResponse.ok(seasonRepository.findAll().stream().map(this::toResponse).toList());
  }

  @PutMapping("/{id}/activate")
  public ApiResponse<SeasonDtos.SeasonResponse> activate(@PathVariable UUID id) {
    seasonRepository.findAll().forEach(season -> {
      season.setActive(season.getId().equals(id));
      seasonRepository.save(season);
    });
    Season season = seasonRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Season not found"));
    return ApiResponse.ok(toResponse(season));
  }

  private SeasonDtos.SeasonResponse toResponse(Season season) {
    return new SeasonDtos.SeasonResponse(
      season.getId().toString(),
      season.getYear(),
      season.getTerm(),
      season.getStartDate(),
      season.getEndDate(),
      season.isActive()
    );
  }
}
