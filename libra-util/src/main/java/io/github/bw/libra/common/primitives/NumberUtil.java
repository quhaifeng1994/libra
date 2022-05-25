package io.github.bw.libra.common.primitives;

public final class NumberUtil {

  private NumberUtil() {
  }

  public static boolean isParsable(String str) {
    if (StringUtil.isBlank(str)) {
      return false;
    }
    if (str.charAt(str.length() - 1) == '.') {
      return false;
    }
    if (str.charAt(0) == '-') {
      if (str.length() == 1) {
        return false;
      }
      return withDecimalsParsing(str, 1);
    }
    return withDecimalsParsing(str, 0);
  }

  private static boolean withDecimalsParsing(final String str, final int beginIdx) {
    int decimalPoints = 0;
    for (int i = beginIdx; i < str.length(); i++) {
      final boolean isDecimalPoint = str.charAt(i) == '.';
      if (isDecimalPoint) {
        decimalPoints++;
      }
      if (decimalPoints > 1) {
        return false;
      }
      if (!isDecimalPoint && !Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }
}
