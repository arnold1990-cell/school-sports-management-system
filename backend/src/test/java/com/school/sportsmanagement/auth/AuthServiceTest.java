package com.school.sportsmanagement.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AuthServiceTest {
  @Test
  void loginIssuesTokens() {
    AuthUserRepository userRepository = mock(AuthUserRepository.class);
    RefreshTokenRepository refreshTokenRepository = mock(RefreshTokenRepository.class);
    AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    JwtService jwtService = new JwtService("issuer", Duration.ofMinutes(15), Duration.ofDays(1), "test-secret-key-test-secret-key-123456");
    AuthService authService = new AuthService(
      userRepository,
      refreshTokenRepository,
      authenticationManager,
      new BCryptPasswordEncoder(),
      jwtService
    );

    AuthUser user = new AuthUser();
    user.setId(UUID.randomUUID());
    user.setEmail("admin@school.local");
    user.setPasswordHash("hash");
    user.setStatus("ACTIVE");
    user.setCreatedAt(Instant.now());
    Role role = new Role();
    role.setId(UUID.randomUUID());
    role.setName("ADMIN");
    user.setRoles(Set.of(role));

    when(userRepository.findByEmail("admin@school.local")).thenReturn(java.util.Optional.of(user));

    AuthDtos.TokenResponse response = authService.login(new AuthDtos.LoginRequest("admin@school.local", "pass"));
    assertThat(response.accessToken()).isNotBlank();
    assertThat(response.refreshToken()).isNotBlank();
  }
}
