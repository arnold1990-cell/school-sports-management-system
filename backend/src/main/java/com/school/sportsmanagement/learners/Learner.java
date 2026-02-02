package com.school.sportsmanagement.learners;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "learners")
public class Learner {
  @Id
  private UUID id;

  @Column(name = "admission_no", nullable = false, unique = true)
  private String admissionNo;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String gender;

  @Column(name = "dob", nullable = false)
  private LocalDate dateOfBirth;

  @Column(nullable = false)
  private String grade;

  @Column(name = "class_name")
  private String className;

  @Column(name = "guardian_phone")
  private String guardianPhone;

  private String notes;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getAdmissionNo() {
    return admissionNo;
  }

  public void setAdmissionNo(String admissionNo) {
    this.admissionNo = admissionNo;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getGuardianPhone() {
    return guardianPhone;
  }

  public void setGuardianPhone(String guardianPhone) {
    this.guardianPhone = guardianPhone;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
