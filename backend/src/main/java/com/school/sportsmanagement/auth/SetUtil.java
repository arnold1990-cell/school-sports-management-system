package com.school.sportsmanagement.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SetUtil {
  private SetUtil() {}

  public static <T> Set<T> asSet(List<T> values) {
    return new HashSet<>(values);
  }
}
