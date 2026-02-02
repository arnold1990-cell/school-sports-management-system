package com.school.sportsmanagement.houses;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, UUID> {}
