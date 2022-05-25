package io.github.bw.libra.common.base;


import java.util.Random;

public final class RandomUtil {

  private static final Random RANDOM = new Random();

  private static final String SMALLER_WARNING = "Start value must be smaller or equal to end value.";
  private static final String RANGE_WARNING = "Both range values must be non-negative.";

  private RandomUtil() {
  }

  public static boolean nextBoolean() {
    return RANDOM.nextBoolean();
  }

  public static byte[] nextBytes(int count) {
    Preconditions.checkArgument(count >= 0, "Count cannot be negative.");

    final byte[] result = new byte[count];
    RANDOM.nextBytes(result);
    return result;
  }

  public static int nextInt(int startInclusive, int endExclusive) {
    Preconditions.checkArgument(endExclusive >= startInclusive,
        SMALLER_WARNING);
    Preconditions.checkArgument(startInclusive >= 0, RANGE_WARNING);

    if (startInclusive == endExclusive) {
      return startInclusive;
    }

    return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
  }

  public static int nextInt() {
    return nextInt(0, Integer.MAX_VALUE);
  }

  public static long nextLong(final long startInclusive, final long endExclusive) {
    Preconditions.checkArgument(endExclusive >= startInclusive,
        SMALLER_WARNING);
    Preconditions.checkArgument(startInclusive >= 0, RANGE_WARNING);

    if (startInclusive == endExclusive) {
      return startInclusive;
    }

    return (long) nextDouble(startInclusive, endExclusive);
  }

  public static long nextLong() {
    return nextLong(0, Long.MAX_VALUE);
  }

  public static double nextDouble(final double startInclusive, final double endExclusive) {
    Preconditions.checkArgument(endExclusive >= startInclusive,
        SMALLER_WARNING);
    Preconditions.checkArgument(startInclusive >= 0, RANGE_WARNING);
    return startInclusive + ((endExclusive - startInclusive) * RANDOM.nextDouble());
  }

  public static double nextDouble() {
    return nextDouble(0, Double.MAX_VALUE);
  }

  public static float nextFloat(final float startInclusive, final float endExclusive) {
    Preconditions.checkArgument(endExclusive >= startInclusive,
        SMALLER_WARNING);
    Preconditions.checkArgument(startInclusive >= 0, RANGE_WARNING);

    return startInclusive + ((endExclusive - startInclusive) * RANDOM.nextFloat());
  }

  public static float nextFloat() {
    return nextFloat(0, Float.MAX_VALUE);
  }
}
