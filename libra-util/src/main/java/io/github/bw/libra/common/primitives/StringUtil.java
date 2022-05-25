package io.github.bw.libra.common.primitives;


import io.github.bw.libra.common.annotations.VisibleForTesting;
import io.github.bw.libra.common.base.Preconditions;
import java.util.Objects;

/**
 * Inspired by Guava
 */
public final class StringUtil {

  public static final String EMPTY = "";

  private StringUtil() {
  }

  public static boolean equals(String a, String b) {
    return Objects.equals(a, b);
  }

  public static boolean equalsIgnoreCase(String a, String b) {
    if (a == b) {
      return true;
    } else if (a != null && b != null) {
      return a.equalsIgnoreCase(b);
    } else {
      return false;
    }
  }

  public static boolean contains(String param, CharSequence param2) {
    if (param == null) {
      return false;
    }
    if (param2 == null) {
      return false;
    }
    return param.contains(param2);
  }

  public static String firstNonBlank(String param1, String param2) {
    if (isNotBlank(param1)) {
      return param1;
    }
    if (isNotBlank(param2)) {
      return param2;
    }
    return null;
  }

  public static String firstNonBlank(String param1, String param2, String param3) {
    if (isNotBlank(param1)) {
      return param1;
    } else if (isNotBlank(param2)) {
      return param2;
    } else if (isNotBlank(param3)) {
      return param3;
    }
    return null;
  }

  public static String firstNonBlank(String... params) {
    for (String param : params) {
      if (isNotBlank(param)) {
        return param;
      }
    }
    return null;
  }

  public static String firstNonNull(String param1, String param2) {
    if (null != param1) {
      return param1;
    }
    return param2;
  }

  public static String firstNonNull(String param1, String param2, String param3) {
    if (null != param1) {
      return param1;
    } else if (null != param2) {
      return param2;
    }
    return param3;
  }

  public static String firstNonNull(String... params) {
    for (String param : params) {
      if (null != param) {
        return param;
      }
    }
    return null;
  }

  public static boolean startsWith(String string, String prefix) {
    Objects.requireNonNull(string);
    Objects.requireNonNull(prefix);
    if (string.length() < prefix.length()) {
      return false;
    }
    return string.startsWith(prefix);
  }

  public boolean endsWith(String string, String suffix) {
    Objects.requireNonNull(string);
    Objects.requireNonNull(suffix);
    if (string.length() < suffix.length()) {
      return false;
    }
    return string.endsWith(suffix);
  }

  public static boolean isNullOrEmpty(String string) {
    return string == null || string.isEmpty();
  }

  public static boolean isBlank(String string) {
    if (string == null || string.length() == 0) {
      return true;
    }
    int strLen = string.length();
    for (int i = 0; i < strLen; ++i) {
      if (!Character.isWhitespace(string.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean isNotBlank(String string) {
    return !isBlank(string);
  }

  public static boolean isNotBlankFrontEndSafe(String text) {
    if (isBlank(text)) {
      return false;
    }
    if ("null".equals(text) || "undefined".equals(text)) {
      return false;
    }
    return true;
  }

  public static String replaceFrontEndDefaultSign(String text) {
    if (text == null) {
      return null;
    }
    if ("null".equals(text) || "undefined".equals(text)) {
      return null;
    }
    return text;
  }

  public static boolean hasLength(String string) {
    return !isNullOrEmpty(string);
  }

  public static String nullToEmpty(String string) {
    return (string == null) ? EMPTY : string;
  }

  public static String emptyToNull(String string) {
    return isNullOrEmpty(string) ? null : string;
  }

  public static String emptyToOther(String source, String defaultValue) {
    return isNullOrEmpty(source) ? defaultValue : source;
  }

  public static String blankToNull(String string) {
    return blankToOther(string, null);
  }

  public static String blankToEmpty(String string) {
    return blankToOther(string, EMPTY);
  }

  public static String blankToOther(String source, String defaultValue) {
    return isBlank(source) ? defaultValue : source;
  }

  public static String padStart(String string, int minLength, char padChar) {
    Objects.requireNonNull(string);
    if (string.length() >= minLength) {
      return string;
    }
    StringBuilder sb = new StringBuilder(minLength);
    for (int i = string.length(); i < minLength; i++) {
      sb.append(padChar);
    }
    sb.append(string);
    return sb.toString();
  }

  public static String padEnd(String string, int minLength, char padChar) {
    Objects.requireNonNull(string);
    if (string.length() >= minLength) {
      return string;
    }
    StringBuilder sb = new StringBuilder(minLength);
    sb.append(string);
    for (int i = string.length(); i < minLength; i++) {
      sb.append(padChar);
    }
    return sb.toString();
  }

  public static String repeat(String string, int count) {
    Objects.requireNonNull(string);

    if (count <= 1) {
      Preconditions.checkArgument(count >= 0, "invalid count: %s", count);
      return (count == 0) ? "" : string;
    }

    // IF YOU MODIFY THE CODE HERE, you must update StringsRepeatBenchmark
    final int len = string.length();
    final long longSize = (long) len * (long) count;
    final int size = (int) longSize;
    if (size != longSize) {
      throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
    }

    final char[] array = new char[size];
    string.getChars(0, len, array, 0);
    int n;
    for (n = len; n < size - n; n <<= 1) {
      System.arraycopy(array, 0, array, n, n);
    }
    System.arraycopy(array, 0, array, n, size - n);
    return new String(array);
  }

  public static String commonPrefix(CharSequence a, CharSequence b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    int maxPrefixLength = Math.min(a.length(), b.length());
    int p = 0;
    while (p < maxPrefixLength && a.charAt(p) == b.charAt(p)) {
      p++;
    }
    if (validSurrogatePairAt(a, p - 1) || validSurrogatePairAt(b, p - 1)) {
      p--;
    }
    return a.subSequence(0, p).toString();
  }

  public static String commonSuffix(CharSequence a, CharSequence b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    int maxSuffixLength = Math.min(a.length(), b.length());
    int s = 0;
    while (s < maxSuffixLength && a.charAt(a.length() - s - 1) == b.charAt(b.length() - s - 1)) {
      s++;
    }
    if (validSurrogatePairAt(a, a.length() - s - 1)
        || validSurrogatePairAt(b, b.length() - s - 1)) {
      s--;
    }
    return a.subSequence(a.length() - s, a.length()).toString();
  }

  @VisibleForTesting
  static boolean validSurrogatePairAt(CharSequence string, int index) {
    return index >= 0
        && index <= (string.length() - 2)
        && Character.isHighSurrogate(string.charAt(index))
        && Character.isLowSurrogate(string.charAt(index + 1));
  }

  public static String lenientFormat(
      String template, Object... args) {
    template = String.valueOf(template); // null -> "null"

    if (args == null) {
      args = new Object[]{"(Object[])null"};
    } else {
      for (int i = 0; i < args.length; i++) {
        args[i] = lenientToString(args[i]);
      }
    }

    // start substituting the arguments into the '%s' placeholders
    StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
    int templateStart = 0;
    int i = 0;
    while (i < args.length) {
      int placeholderStart = template.indexOf("%s", templateStart);
      if (placeholderStart == -1) {
        break;
      }
      builder.append(template, templateStart, placeholderStart);
      builder.append(args[i++]);
      templateStart = placeholderStart + 2;
    }
    builder.append(template, templateStart, template.length());

    // if we run out of placeholders, append the extra args in square braces
    if (i < args.length) {
      builder.append(" [");
      builder.append(args[i++]);
      while (i < args.length) {
        builder.append(", ");
        builder.append(args[i++]);
      }
      builder.append(']');
    }

    return builder.toString();
  }

  private static String lenientToString(Object o) {
    if (o == null) {
      return "null";
    }
    try {
      return o.toString();
    } catch (Exception e) {
      String objectToString =
          o.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(o));
      return "<" + objectToString + " threw " + e.getClass().getName() + ">";
    }
  }
}
