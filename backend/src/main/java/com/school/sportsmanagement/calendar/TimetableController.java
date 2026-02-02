package com.school.sportsmanagement.calendar;

import com.school.sportsmanagement.common.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/timetable")
public class TimetableController {
  private final TimetableRepository timetableRepository;

  public TimetableController(TimetableRepository timetableRepository) {
    this.timetableRepository = timetableRepository;
  }

  @PostMapping
  public ApiResponse<CalendarDtos.TimetableResponse> create(@Valid @RequestBody CalendarDtos.TimetableRequest request) {
    TimetableEntry entry = new TimetableEntry();
    entry.setId(UUID.randomUUID());
    entry.setSeasonId(request.seasonId());
    entry.setDayOfWeek(request.dayOfWeek());
    entry.setStartTime(request.startTime());
    entry.setEndTime(request.endTime());
    entry.setType(request.type());
    entry.setLocation(request.location());
    entry.setTeamId(request.teamId());
    entry.setClubId(request.clubId());
    return ApiResponse.ok(toResponse(timetableRepository.save(entry)));
  }

  @GetMapping
  public ApiResponse<List<CalendarDtos.TimetableResponse>> list(@RequestParam UUID seasonId) {
    return ApiResponse.ok(timetableRepository.findBySeasonId(seasonId).stream().map(this::toResponse).toList());
  }

  private CalendarDtos.TimetableResponse toResponse(TimetableEntry entry) {
    return new CalendarDtos.TimetableResponse(
      entry.getId().toString(),
      entry.getSeasonId(),
      entry.getDayOfWeek(),
      entry.getStartTime(),
      entry.getEndTime(),
      entry.getType(),
      entry.getLocation(),
      entry.getTeamId(),
      entry.getClubId()
    );
  }
}
