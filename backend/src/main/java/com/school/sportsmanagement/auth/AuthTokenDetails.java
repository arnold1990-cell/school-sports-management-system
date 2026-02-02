package com.school.sportsmanagement.auth;

import java.time.Instant;
import java.util.List;

public record AuthTokenDetails(String subject, List<String> roles, Instant expiresAt) {}
