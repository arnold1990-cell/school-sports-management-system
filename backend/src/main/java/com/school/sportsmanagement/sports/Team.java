package com.school.sportsmanagement.sports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "teams")
public class Team {
  @Id
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(name = "sport_id", nullable = false)
  private UUID sportId;

  @Column(name = "house_id")
  private UUID houseId;

  @Column(name = "age_group_id", nullable = false)
  private UUID ageGroupId;

  @Column(name = "season_id", nullable = false)
  private UUID seasonId;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getSportId() {
    return sportId;
  }

  public void setSportId(UUID sportId) {
    this.sportId = sportId;
  }

  public UUID getHouseId() {
    return houseId;
  }

  public void setHouseId(UUID houseId) {
    this.houseId = houseId;
  }

  public UUID getAgeGroupId() {
    return ageGroupId;
  }

  public void setAgeGroupId(UUID ageGroupId) {
    this.ageGroupId = ageGroupId;
  }

  public UUID getSeasonId() {
    return seasonId;
  }

  public void setSeasonId(UUID seasonId) {
    this.seasonId = seasonId;
  }
}
