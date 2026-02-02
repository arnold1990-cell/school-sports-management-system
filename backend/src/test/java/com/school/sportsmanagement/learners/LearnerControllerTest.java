package com.school.sportsmanagement.learners;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class LearnerControllerTest {
  @Test
  void createMapsRequestToResponse() {
    LearnerRepository repository = mock(LearnerRepository.class);
    LearnerController controller = new LearnerController(repository);

    Learner entity = new Learner();
    entity.setId(UUID.randomUUID());
    entity.setAdmissionNo("A-001");
    entity.setFirstName("Tumi");
    entity.setLastName("Kelefa");
    entity.setGender("FEMALE");
    entity.setDateOfBirth(LocalDate.of(2010, 3, 2));
    entity.setGrade("Form 1");

    when(repository.save(org.mockito.ArgumentMatchers.any(Learner.class))).thenReturn(entity);

    LearnerDtos.LearnerRequest request = new LearnerDtos.LearnerRequest(
      "A-001",
      "Tumi",
      "Kelefa",
      "FEMALE",
      LocalDate.of(2010, 3, 2),
      "Form 1",
      null,
      null,
      null
    );

    LearnerDtos.LearnerResponse response = controller.create(request).data();
    assertThat(response.admissionNo()).isEqualTo("A-001");
    assertThat(response.firstName()).isEqualTo("Tumi");
  }
}
