package io.github.bw.libra.common.date;

import java.sql.Timestamp;

public class TimestampUtil {

  private TimestampUtil() {
  }

  public static Timestamp getTimestamp() {
    return new Timestamp(System.currentTimeMillis());
  }

  public static long getTime() {
    return System.currentTimeMillis();
  }

  public static Timestamp getTimestamp(long d) {
    return new Timestamp(System.currentTimeMillis() + d);
  }
}
