package com.school.sportsmanagement.learners;

import com.school.sportsmanagement.common.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/learners")
public class LearnerController {
  private final LearnerRepository learnerRepository;

  public LearnerController(LearnerRepository learnerRepository) {
    this.learnerRepository = learnerRepository;
  }

  @PostMapping
  public ApiResponse<LearnerDtos.LearnerResponse> create(@Valid @RequestBody LearnerDtos.LearnerRequest request) {
    Learner learner = toEntity(request, new Learner());
    learner.setId(UUID.randomUUID());
    return ApiResponse.ok(toResponse(learnerRepository.save(learner)));
  }

  @GetMapping
  public ApiResponse<List<LearnerDtos.LearnerResponse>> list() {
    List<LearnerDtos.LearnerResponse> learners = learnerRepository.findAll().stream()
      .map(this::toResponse)
      .toList();
    return ApiResponse.ok(learners);
  }

  @GetMapping("/{id}")
  public ApiResponse<LearnerDtos.LearnerResponse> get(@PathVariable UUID id) {
    Learner learner = learnerRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Learner not found"));
    return ApiResponse.ok(toResponse(learner));
  }

  @PutMapping("/{id}")
  public ApiResponse<LearnerDtos.LearnerResponse> update(
    @PathVariable UUID id,
    @Valid @RequestBody LearnerDtos.LearnerRequest request
  ) {
    Learner learner = learnerRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Learner not found"));
    toEntity(request, learner);
    return ApiResponse.ok(toResponse(learnerRepository.save(learner)));
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> delete(@PathVariable UUID id) {
    learnerRepository.deleteById(id);
    return ApiResponse.ok(null);
  }

  private Learner toEntity(LearnerDtos.LearnerRequest request, Learner learner) {
    learner.setAdmissionNo(request.admissionNo());
    learner.setFirstName(request.firstName());
    learner.setLastName(request.lastName());
    learner.setGender(request.gender());
    learner.setDateOfBirth(request.dateOfBirth());
    learner.setGrade(request.grade());
    learner.setClassName(request.className());
    learner.setGuardianPhone(request.guardianPhone());
    learner.setNotes(request.notes());
    return learner;
  }

  private LearnerDtos.LearnerResponse toResponse(Learner learner) {
    return new LearnerDtos.LearnerResponse(
      learner.getId().toString(),
      learner.getAdmissionNo(),
      learner.getFirstName(),
      learner.getLastName(),
      learner.getGender(),
      learner.getDateOfBirth(),
      learner.getGrade(),
      learner.getClassName(),
      learner.getGuardianPhone(),
      learner.getNotes()
    );
  }
}
