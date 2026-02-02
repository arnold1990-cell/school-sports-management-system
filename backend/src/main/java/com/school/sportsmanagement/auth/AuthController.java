package com.school.sportsmanagement.auth;

import com.school.sportsmanagement.common.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final AuthService authService;
  private final AuthUserRepository userRepository;
  private final RoleRepository roleRepository;

  public AuthController(AuthService authService, AuthUserRepository userRepository, RoleRepository roleRepository) {
    this.authService = authService;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @PostMapping("/login")
  public ApiResponse<AuthDtos.TokenResponse> login(@Valid @RequestBody AuthDtos.LoginRequest request) {
    return ApiResponse.ok(authService.login(request));
  }

  @PostMapping("/refresh")
  public ApiResponse<AuthDtos.TokenResponse> refresh(@Valid @RequestBody AuthDtos.RefreshRequest request) {
    return ApiResponse.ok(authService.refresh(request));
  }

  @PostMapping("/logout")
  public ApiResponse<Void> logout(@Valid @RequestBody AuthDtos.RefreshRequest request) {
    authService.logout(request);
    return ApiResponse.ok(null);
  }

  @PostMapping("/users")
  public ApiResponse<AuthDtos.UserProfile> createUser(@Valid @RequestBody AuthDtos.CreateUserRequest request) {
    List<Role> roles = request.roles() == null ? List.of() : request.roles().stream()
      .map(roleName -> roleRepository.findByName(roleName)
        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
      .collect(Collectors.toList());
    AuthUser user = authService.createUser(request, roles);
    return ApiResponse.ok(new AuthDtos.UserProfile(
      user.getId().toString(),
      user.getEmail(),
      user.getRoles().stream().map(Role::getName).toList()
    ));
  }

  @GetMapping("/me")
  public ApiResponse<AuthDtos.UserProfile> me(Authentication authentication) {
    String email = authentication.getName();
    AuthUser user = userRepository.findByEmail(email)
      .orElseThrow(() -> new IllegalArgumentException("User not found"));
    return ApiResponse.ok(new AuthDtos.UserProfile(
      user.getId().toString(),
      user.getEmail(),
      user.getRoles().stream().map(Role::getName).toList()
    ));
  }
}
