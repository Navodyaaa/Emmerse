package com.emmerseweb.util;

public class StringUtil {

  private StringUtil() {}

  public static String formatString(final String baseStr, String... objects) {
    return String.format(baseStr, (Object) objects);
  }
}
