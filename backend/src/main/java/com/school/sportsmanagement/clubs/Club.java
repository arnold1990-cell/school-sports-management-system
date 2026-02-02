package com.school.sportsmanagement.clubs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "clubs")
public class Club {
  @Id
  private UUID id;

  @Column(nullable = false)
  private String name;

  private String description;

  @Column(nullable = false)
  private String type;

  @Column(name = "fee_amount")
  private BigDecimal feeAmount;

  @Column(name = "fee_frequency")
  private String feeFrequency;

  @Column(nullable = false)
  private int capacity;

  @Column(name = "season_id", nullable = false)
  private UUID seasonId;

  @Column(name = "staff_id", nullable = false)
  private UUID staffId;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getFeeAmount() {
    return feeAmount;
  }

  public void setFeeAmount(BigDecimal feeAmount) {
    this.feeAmount = feeAmount;
  }

  public String getFeeFrequency() {
    return feeFrequency;
  }

  public void setFeeFrequency(String feeFrequency) {
    this.feeFrequency = feeFrequency;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public UUID getSeasonId() {
    return seasonId;
  }

  public void setSeasonId(UUID seasonId) {
    this.seasonId = seasonId;
  }

  public UUID getStaffId() {
    return staffId;
  }

  public void setStaffId(UUID staffId) {
    this.staffId = staffId;
  }
}
