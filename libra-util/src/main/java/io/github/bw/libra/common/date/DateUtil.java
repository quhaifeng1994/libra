package io.github.bw.libra.common.date;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtil {

  private DateUtil() {
  }

  public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

  public static final String YYYYMMDD = "yyyy-MM-dd";

  public static final String YYYYMMDDSLASH = "yyyy/MM/dd";

  /**
   * Gets now to night diff minutes.
   *
   * @return the now to night diff minutes
   */
  public static long getNowToNightDiffMinutes() {

    LocalDateTime now = LocalDateTime.now();
    LocalDateTime night = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);

    Duration duration = Duration.between(now, night);
    return duration.toMinutes();
  }

  public static long getNowToNightDiffSeconds() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime night = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);

    Duration duration = Duration.between(now, night);
    return duration.getSeconds();
  }

  public static String format(Date date, String format) {
    String result = "";
    try {
      if (date != null) {
        DateFormat df = new SimpleDateFormat(format);
        result = df.format(date);
      }
    } catch (Exception e) {
      // no op
    }
    return result;
  }

  public static boolean isValidDate(String str, String format) {
    boolean convertSuccess = true;
    SimpleDateFormat sdf = new SimpleDateFormat(format);

    try {
      sdf.setLenient(false);
      sdf.parse(str);
    } catch (ParseException var5) {
      convertSuccess = false;
    }

    return convertSuccess;
  }
}
