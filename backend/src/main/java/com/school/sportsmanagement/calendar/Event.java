package com.school.sportsmanagement.calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {
  @Id
  private UUID id;

  @Column(name = "season_id", nullable = false)
  private UUID seasonId;

  @Column(nullable = false)
  private String title;

  private String description;

  @Column(name = "event_start", nullable = false)
  private LocalDateTime eventStart;

  @Column(name = "event_end", nullable = false)
  private LocalDateTime eventEnd;

  private String location;

  @Column(name = "created_by", nullable = false)
  private UUID createdBy;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getEventStart() {
    return eventStart;
  }

  public void setEventStart(LocalDateTime eventStart) {
    this.eventStart = eventStart;
  }

  public LocalDateTime getEventEnd() {
    return eventEnd;
  }

  public void setEventEnd(LocalDateTime eventEnd) {
    this.eventEnd = eventEnd;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public UUID getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UUID createdBy) {
    this.createdBy = createdBy;
  }
}
