package com.school.sportsmanagement.sports;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, UUID> {
  Optional<Result> findByFixtureId(UUID fixtureId);
}
