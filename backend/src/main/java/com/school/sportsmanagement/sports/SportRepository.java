package com.school.sportsmanagement.sports;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, UUID> {}
