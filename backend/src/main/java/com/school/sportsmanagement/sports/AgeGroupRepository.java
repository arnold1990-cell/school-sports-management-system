package com.school.sportsmanagement.sports;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeGroupRepository extends JpaRepository<AgeGroup, UUID> {}
