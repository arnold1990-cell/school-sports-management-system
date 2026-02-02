package com.school.sportsmanagement.clubs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "club_enrollments")
public class ClubEnrollment {
  @Id
  private UUID id;

  @Column(name = "club_id", nullable = false)
  private UUID clubId;

  @Column(name = "learner_id", nullable = false)
  private UUID learnerId;

  @Column(nullable = false)
  private String status;

  @Column(name = "enrolled_at", nullable = false)
  private Instant enrolledAt;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getClubId() {
    return clubId;
  }

  public void setClubId(UUID clubId) {
    this.clubId = clubId;
  }

  public UUID getLearnerId() {
    return learnerId;
  }

  public void setLearnerId(UUID learnerId) {
    this.learnerId = learnerId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Instant getEnrolledAt() {
    return enrolledAt;
  }

  public void setEnrolledAt(Instant enrolledAt) {
    this.enrolledAt = enrolledAt;
  }
}
