package com.school.sportsmanagement.houses;

import com.school.sportsmanagement.common.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/houses")
public class HouseController {
  private final HouseRepository houseRepository;

  public HouseController(HouseRepository houseRepository) {
    this.houseRepository = houseRepository;
  }

  @PostMapping
  public ApiResponse<HouseDtos.HouseResponse> create(@Valid @RequestBody HouseDtos.HouseRequest request) {
    House house = new House();
    house.setId(UUID.randomUUID());
    house.setName(request.name());
    house.setColor(request.color());
    return ApiResponse.ok(toResponse(houseRepository.save(house)));
  }

  @GetMapping
  public ApiResponse<List<HouseDtos.HouseResponse>> list() {
    return ApiResponse.ok(houseRepository.findAll().stream().map(this::toResponse).toList());
  }

  @PutMapping("/{id}")
  public ApiResponse<HouseDtos.HouseResponse> update(@PathVariable UUID id, @Valid @RequestBody HouseDtos.HouseRequest request) {
    House house = houseRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("House not found"));
    house.setName(request.name());
    house.setColor(request.color());
    return ApiResponse.ok(toResponse(houseRepository.save(house)));
  }

  private HouseDtos.HouseResponse toResponse(House house) {
    return new HouseDtos.HouseResponse(house.getId().toString(), house.getName(), house.getColor());
  }
}
