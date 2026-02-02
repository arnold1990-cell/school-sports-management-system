package com.school.sportsmanagement.sports;

import com.school.sportsmanagement.common.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SportsController {
  private final SportRepository sportRepository;
  private final AgeGroupRepository ageGroupRepository;
  private final TeamRepository teamRepository;

  public SportsController(SportRepository sportRepository, AgeGroupRepository ageGroupRepository, TeamRepository teamRepository) {
    this.sportRepository = sportRepository;
    this.ageGroupRepository = ageGroupRepository;
    this.teamRepository = teamRepository;
  }

  @PostMapping("/sports")
  public ApiResponse<SportsDtos.SportResponse> createSport(@Valid @RequestBody SportsDtos.SportRequest request) {
    Sport sport = new Sport();
    sport.setId(UUID.randomUUID());
    sport.setName(request.name());
    return ApiResponse.ok(new SportsDtos.SportResponse(sportRepository.save(sport).getId().toString(), sport.getName()));
  }

  @GetMapping("/sports")
  public ApiResponse<List<SportsDtos.SportResponse>> listSports() {
    return ApiResponse.ok(sportRepository.findAll().stream()
      .map(sport -> new SportsDtos.SportResponse(sport.getId().toString(), sport.getName()))
      .toList());
  }

  @PostMapping("/age-groups")
  public ApiResponse<SportsDtos.AgeGroupResponse> createAgeGroup(@Valid @RequestBody SportsDtos.AgeGroupRequest request) {
    AgeGroup ageGroup = new AgeGroup();
    ageGroup.setId(UUID.randomUUID());
    ageGroup.setName(request.name());
    ageGroup.setMinAge(request.minAge());
    ageGroup.setMaxAge(request.maxAge());
    AgeGroup saved = ageGroupRepository.save(ageGroup);
    return ApiResponse.ok(new SportsDtos.AgeGroupResponse(saved.getId().toString(), saved.getName(), saved.getMinAge(), saved.getMaxAge()));
  }

  @GetMapping("/age-groups")
  public ApiResponse<List<SportsDtos.AgeGroupResponse>> listAgeGroups() {
    return ApiResponse.ok(ageGroupRepository.findAll().stream()
      .map(group -> new SportsDtos.AgeGroupResponse(group.getId().toString(), group.getName(), group.getMinAge(), group.getMaxAge()))
      .toList());
  }

  @PostMapping("/teams")
  public ApiResponse<SportsDtos.TeamResponse> createTeam(@Valid @RequestBody SportsDtos.TeamRequest request) {
    Team team = new Team();
    team.setId(UUID.randomUUID());
    team.setName(request.name());
    team.setSportId(request.sportId());
    team.setHouseId(request.houseId());
    team.setAgeGroupId(request.ageGroupId());
    team.setSeasonId(request.seasonId());
    Team saved = teamRepository.save(team);
    return ApiResponse.ok(new SportsDtos.TeamResponse(saved.getId().toString(), saved.getName(), saved.getSportId(), saved.getHouseId(), saved.getAgeGroupId(), saved.getSeasonId()));
  }

  @GetMapping("/teams/{id}")
  public ApiResponse<SportsDtos.TeamResponse> getTeam(@PathVariable UUID id) {
    Team team = teamRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Team not found"));
    return ApiResponse.ok(new SportsDtos.TeamResponse(team.getId().toString(), team.getName(), team.getSportId(), team.getHouseId(), team.getAgeGroupId(), team.getSeasonId()));
  }
}
