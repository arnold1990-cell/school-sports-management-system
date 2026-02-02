package com.school.sportsmanagement.clubs;

import com.school.sportsmanagement.common.ApiResponse;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clubs")
public class ClubController {
  private final ClubRepository clubRepository;
  private final ClubEnrollmentRepository enrollmentRepository;
  private final ClubPaymentRepository paymentRepository;

  public ClubController(
    ClubRepository clubRepository,
    ClubEnrollmentRepository enrollmentRepository,
    ClubPaymentRepository paymentRepository
  ) {
    this.clubRepository = clubRepository;
    this.enrollmentRepository = enrollmentRepository;
    this.paymentRepository = paymentRepository;
  }

  @PostMapping
  public ApiResponse<ClubDtos.ClubResponse> create(@Valid @RequestBody ClubDtos.ClubRequest request) {
    Club club = new Club();
    club.setId(UUID.randomUUID());
    club.setName(request.name());
    club.setDescription(request.description());
    club.setType(request.type());
    club.setFeeAmount(request.feeAmount());
    club.setFeeFrequency(request.feeFrequency());
    club.setCapacity(request.capacity());
    club.setSeasonId(request.seasonId());
    club.setStaffId(request.staffId());
    return ApiResponse.ok(toResponse(clubRepository.save(club)));
  }

  @GetMapping
  public ApiResponse<List<ClubDtos.ClubResponse>> list() {
    return ApiResponse.ok(clubRepository.findAll().stream().map(this::toResponse).toList());
  }

  @PostMapping("/{id}/enroll")
  public ApiResponse<ClubDtos.EnrollmentResponse> enroll(
    @PathVariable UUID id,
    @Valid @RequestBody ClubDtos.EnrollmentRequest request
  ) {
    ClubEnrollment enrollment = new ClubEnrollment();
    enrollment.setId(UUID.randomUUID());
    enrollment.setClubId(id);
    enrollment.setLearnerId(request.learnerId());
    enrollment.setStatus(request.status());
    enrollment.setEnrolledAt(Instant.now());
    ClubEnrollment saved = enrollmentRepository.save(enrollment);
    return ApiResponse.ok(new ClubDtos.EnrollmentResponse(
      saved.getId().toString(),
      saved.getClubId(),
      saved.getLearnerId(),
      saved.getStatus(),
      saved.getEnrolledAt()
    ));
  }

  @PostMapping("/enrollments/{id}/payments")
  public ApiResponse<ClubDtos.PaymentResponse> recordPayment(
    @PathVariable UUID id,
    @Valid @RequestBody ClubDtos.PaymentRequest request
  ) {
    ClubPayment payment = new ClubPayment();
    payment.setId(UUID.randomUUID());
    payment.setEnrollmentId(id);
    payment.setAmount(request.amount());
    payment.setPaidAt(Instant.now());
    payment.setReference(request.reference());
    ClubPayment saved = paymentRepository.save(payment);
    return ApiResponse.ok(new ClubDtos.PaymentResponse(
      saved.getId().toString(),
      saved.getEnrollmentId(),
      saved.getAmount(),
      saved.getPaidAt(),
      saved.getReference()
    ));
  }

  private ClubDtos.ClubResponse toResponse(Club club) {
    return new ClubDtos.ClubResponse(
      club.getId().toString(),
      club.getName(),
      club.getDescription(),
      club.getType(),
      club.getFeeAmount(),
      club.getFeeFrequency(),
      club.getCapacity(),
      club.getSeasonId(),
      club.getStaffId()
    );
  }
}
