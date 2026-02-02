package com.school.sportsmanagement.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final String issuer;
  private final Duration accessTtl;
  private final Duration refreshTtl;
  private final Key key;

  public JwtService(
    @Value("${app.jwt.issuer}") String issuer,
    @Value("${app.jwt.access-token-ttl}") Duration accessTtl,
    @Value("${app.jwt.refresh-token-ttl}") Duration refreshTtl,
    @Value("${app.jwt.secret}") String secret
  ) {
    this.issuer = issuer;
    this.accessTtl = accessTtl;
    this.refreshTtl = refreshTtl;
    this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(java.util.Base64.getEncoder().encodeToString(secret.getBytes())));
  }

  public String createAccessToken(AuthUser user) {
    Instant now = Instant.now();
    List<String> roles = user.getRoles().stream().map(Role::getName).toList();
    return Jwts.builder()
      .issuer(issuer)
      .subject(user.getId().toString())
      .issuedAt(Date.from(now))
      .expiration(Date.from(now.plus(accessTtl)))
      .claim("roles", roles)
      .signWith(key)
      .compact();
  }

  public String createRefreshToken(AuthUser user) {
    Instant now = Instant.now();
    return Jwts.builder()
      .issuer(issuer)
      .subject(user.getId().toString())
      .issuedAt(Date.from(now))
      .expiration(Date.from(now.plus(refreshTtl)))
      .claim("type", "refresh")
      .signWith(key)
      .compact();
  }

  public AuthTokenDetails parseToken(String token) {
    var parsed = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
    var claims = parsed.getPayload();
    List<String> roles = claims.get("roles", List.class);
    return new AuthTokenDetails(
      claims.getSubject(),
      roles == null ? List.of() : roles,
      claims.getExpiration().toInstant()
    );
  }

  public Duration getRefreshTtl() {
    return refreshTtl;
  }
}
