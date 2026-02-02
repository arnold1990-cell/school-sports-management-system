package com.school.sportsmanagement.calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "timetable_entries")
public class TimetableEntry {
  @Id
  private UUID id;

  @Column(name = "season_id", nullable = false)
  private UUID seasonId;

  @Column(name = "day_of_week", nullable = false)
  private String dayOfWeek;

  @Column(name = "start_time", nullable = false)
  private LocalTime startTime;

  @Column(name = "end_time", nullable = false)
  private LocalTime endTime;

  @Column(nullable = false)
  private String type;

  private String location;

  @Column(name = "team_id")
  private UUID teamId;

  @Column(name = "club_id")
  private UUID clubId;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getSeasonId() {
    return seasonId;
  }

  public void setSeasonId(UUID seasonId) {
    this.seasonId = seasonId;
  }

  public String getDayOfWeek() {
    return dayOfWeek;
  }

  public void setDayOfWeek(String dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public UUID getTeamId() {
    return teamId;
  }

  public void setTeamId(UUID teamId) {
    this.teamId = teamId;
  }

  public UUID getClubId() {
    return clubId;
  }

  public void setClubId(UUID clubId) {
    this.clubId = clubId;
  }
}
