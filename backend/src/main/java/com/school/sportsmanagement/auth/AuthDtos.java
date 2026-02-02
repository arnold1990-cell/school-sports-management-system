package com.school.sportsmanagement.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class AuthDtos {
  public record LoginRequest(@Email @NotBlank String email, @NotBlank String password) {}

  public record TokenResponse(String accessToken, String refreshToken) {}

  public record RefreshRequest(@NotBlank String refreshToken) {}

  public record CreateUserRequest(
    @Email @NotBlank String email,
    @NotBlank String password,
    List<String> roles
  ) {}

  public record UserProfile(String id, String email, List<String> roles) {}
}
