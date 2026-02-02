package com.school.sportsmanagement.auth;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final AuthUserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthService(
    AuthUserRepository userRepository,
    RefreshTokenRepository refreshTokenRepository,
    AuthenticationManager authenticationManager,
    PasswordEncoder passwordEncoder,
    JwtService jwtService
  ) {
    this.userRepository = userRepository;
    this.refreshTokenRepository = refreshTokenRepository;
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public AuthDtos.TokenResponse login(AuthDtos.LoginRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );
    AuthUser user = userRepository.findByEmail(request.email())
      .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    return issueTokens(user);
  }

  public AuthDtos.TokenResponse refresh(AuthDtos.RefreshRequest request) {
    RefreshToken token = refreshTokenRepository.findByTokenAndRevokedFalse(request.refreshToken())
      .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));
    if (token.getExpiresAt().isBefore(Instant.now())) {
      throw new IllegalArgumentException("Refresh token expired");
    }
    return issueTokens(token.getUser());
  }

  public void logout(AuthDtos.RefreshRequest request) {
    refreshTokenRepository.findByTokenAndRevokedFalse(request.refreshToken())
      .ifPresent(token -> {
        token.setRevoked(true);
        refreshTokenRepository.save(token);
      });
  }

  public AuthUser createUser(AuthDtos.CreateUserRequest request, List<Role> roles) {
    AuthUser user = new AuthUser();
    user.setId(UUID.randomUUID());
    user.setEmail(request.email());
    user.setPasswordHash(passwordEncoder.encode(request.password()));
    user.setStatus("ACTIVE");
    user.setCreatedAt(Instant.now());
    user.setRoles(SetUtil.asSet(roles));
    return userRepository.save(user);
  }

  private AuthDtos.TokenResponse issueTokens(AuthUser user) {
    String accessToken = jwtService.createAccessToken(user);
    String refreshToken = jwtService.createRefreshToken(user);
    RefreshToken refreshEntity = new RefreshToken();
    refreshEntity.setId(UUID.randomUUID());
    refreshEntity.setUser(user);
    refreshEntity.setToken(refreshToken);
    refreshEntity.setExpiresAt(Instant.now().plus(jwtService.getRefreshTtl()));
    refreshEntity.setRevoked(false);
    refreshTokenRepository.save(refreshEntity);
    return new AuthDtos.TokenResponse(accessToken, refreshToken);
  }
}
