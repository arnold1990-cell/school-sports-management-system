package com.school.sportsmanagement.houses;

import jakarta.validation.constraints.NotBlank;

public class HouseDtos {
  public record HouseRequest(@NotBlank String name, String color) {}

  public record HouseResponse(String id, String name, String color) {}
}
