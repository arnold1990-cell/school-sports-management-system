package com.school.sportsmanagement.clubs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "club_payments")
public class ClubPayment {
  @Id
  private UUID id;

  @Column(name = "enrollment_id", nullable = false)
  private UUID enrollmentId;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(name = "paid_at", nullable = false)
  private Instant paidAt;

  private String reference;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getEnrollmentId() {
    return enrollmentId;
  }

  public void setEnrollmentId(UUID enrollmentId) {
    this.enrollmentId = enrollmentId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Instant getPaidAt() {
    return paidAt;
  }

  public void setPaidAt(Instant paidAt) {
    this.paidAt = paidAt;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }
}
