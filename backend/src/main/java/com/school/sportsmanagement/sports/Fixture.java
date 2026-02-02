package com.school.sportsmanagement.sports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fixtures")
public class Fixture {
  @Id
  private UUID id;

  @Column(name = "team_id", nullable = false)
  private UUID teamId;

  @Column(nullable = false)
  private String opponent;

  @Column(nullable = false)
  private String venue;

  @Column(name = "match_date_time", nullable = false)
  private LocalDateTime matchDateTime;

  @Column(name = "home_away", nullable = false)
  private String homeAway;

  @Column(nullable = false)
  private String status;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getTeamId() {
    return teamId;
  }

  public void setTeamId(UUID teamId) {
    this.teamId = teamId;
  }

  public String getOpponent() {
    return opponent;
  }

  public void setOpponent(String opponent) {
    this.opponent = opponent;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public LocalDateTime getMatchDateTime() {
    return matchDateTime;
  }

  public void setMatchDateTime(LocalDateTime matchDateTime) {
    this.matchDateTime = matchDateTime;
  }

  public String getHomeAway() {
    return homeAway;
  }

  public void setHomeAway(String homeAway) {
    this.homeAway = homeAway;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
