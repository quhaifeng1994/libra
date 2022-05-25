package io.github.bw.libra.common.base;


import static io.github.bw.libra.common.primitives.StringUtil.lenientFormat;

import io.github.bw.libra.common.exception.PreconditionException;
import io.github.bw.libra.common.exception.PreconditionIllegalPermissionsException;
import io.github.bw.libra.common.primitives.StringUtil;

/**
 * 各种奇葩校验
 * <p>
 * Inspired by Guava
 *
 * @author zhangyunan
 */
public final class Preconditions {

  private Preconditions() {
  }

  public static void checkGTZero(Integer param) {
    if (param == null || param <= 0) {
      throw new PreconditionException();
    }
  }

  public static void checkGTZero(Integer param, String errorMessage) {
    if (param == null || param <= 0) {
      throw new PreconditionException(errorMessage);
    }
  }

  public static void checkGTZero(Integer param, String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (param == null || param <= 0) {
      throw new PreconditionException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }

  public static void checkRange(int param, int min, int max) {
    if (param < min || param > max) {
      throw new PreconditionException();
    }
  }

  public static void checkRange(int param, int min, int max, String errorMessage) {
    if (param < min || param > max) {
      throw new PreconditionException(errorMessage);
    }
  }

  /**
   * Check permissions.
   *
   * @param expression the expression
   */
  public static void checkPermissions(boolean expression) {
    if (!expression) {
      throw new PreconditionIllegalPermissionsException();
    }
  }

  public static void checkPermissions(boolean expression, String errorMessage) {
    if (!expression) {
      throw new PreconditionIllegalPermissionsException(errorMessage);
    }
  }

  /**
   * Check permissions.
   *
   * @param expression           the expression
   * @param errorMessageTemplate the error message template
   * @param errorMessageArgs     the error message args
   */
  public static void checkPermissions(
      boolean expression,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (!expression) {
      throw new PreconditionIllegalPermissionsException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }

  /**
   * Check argument.
   *
   * @param expression the expression
   */
  public static void checkArgument(boolean expression) {
    if (!expression) {
      throw new PreconditionException();
    }
  }

  /**
   * Check argument.
   *
   * @param expression   the expression
   * @param errorMessage the error message
   */
  public static void checkArgument(boolean expression, String errorMessage) {
    if (!expression) {
      throw new PreconditionException(errorMessage);
    }
  }

  /**
   * Check argument.
   *
   * @param expression           the expression
   * @param errorMessageTemplate the error message template
   * @param errorMessageArgs     the error message args
   */
  public static void checkArgument(
      boolean expression,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (!expression) {
      throw new PreconditionException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }

  /**
   * Check state.
   *
   * @param expression the expression
   */
  public static void checkState(boolean expression) {
    if (!expression) {
      throw new PreconditionException();
    }
  }

  /**
   * Check state.
   *
   * @param expression   the expression
   * @param errorMessage the error message
   */
  public static void checkState(boolean expression, String errorMessage) {
    if (!expression) {
      throw new PreconditionException(errorMessage);
    }
  }

  /**
   * Check state.
   *
   * @param expression           the expression
   * @param errorMessageTemplate the error message template
   * @param errorMessageArgs     the error message args
   */
  public static void checkState(
      boolean expression,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (!expression) {
      throw new PreconditionException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }

  public static void checkNull(Object reference) {
    if (reference != null) {
      throw new PreconditionException();
    }
  }

  public static void checkNull(
      Object reference, String errorMessage) {
    if (reference != null) {
      throw new PreconditionException(errorMessage);
    }
  }

  public static void checkNull(
      Object reference,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (reference != null) {
      throw new PreconditionException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }

  /**
   * Check not null.
   *
   * @param reference the reference
   */
  public static void checkNotNull(Object reference) {
    if (reference == null) {
      throw new PreconditionException();
    }
  }

  /**
   * Check not null.
   *
   * @param reference    the reference
   * @param errorMessage the error message
   */
  public static void checkNotNull(
      Object reference, String errorMessage) {
    if (reference == null) {
      throw new PreconditionException(errorMessage);
    }
  }

  /**
   * Check not null.
   *
   * @param reference            the reference
   * @param errorMessageTemplate the error message template
   * @param errorMessageArgs     the error message args
   */
  public static void checkNotNull(
      Object reference,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (reference == null) {
      throw new PreconditionException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }


  /**
   * Check not null.
   *
   * @param reference the reference
   */
  public static void checkNotEmpty(String reference) {
    if (StringUtil.isNullOrEmpty(reference)) {
      throw new PreconditionException();
    }
  }

  public static void checkNotEmpty(
      String reference, String errorMessage) {
    if (StringUtil.isNullOrEmpty(reference)) {
      throw new PreconditionException(errorMessage);
    }
  }

  public static void checkNotEmpty(
      String reference,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (StringUtil.isNullOrEmpty(reference)) {
      throw new PreconditionException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }

  /**
   * Check not null.
   *
   * @param reference the reference
   */
  public static void checkNotBlank(String reference) {
    if (StringUtil.isBlank(reference)) {
      throw new PreconditionException();
    }
  }

  public static void checkNotBlank(
      String reference, String errorMessage) {
    if (StringUtil.isBlank(reference)) {
      throw new PreconditionException(errorMessage);
    }
  }

  public static void checkNotBlank(
      String reference,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (StringUtil.isBlank(reference)) {
      throw new PreconditionException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }

  /**
   * Check element index int.
   *
   * @param index the index
   * @param size  the size
   * @return the int
   */
  public static int checkElementIndex(int index, int size) {
    return checkElementIndex(index, size, "index");
  }

  /**
   * Check element index int.
   *
   * @param index the index
   * @param size  the size
   * @param desc  the desc
   * @return the int
   */
  public static int checkElementIndex(int index, int size, String desc) {
    // Carefully optimized for execution by hotspot (explanatory comment above)
    if (index < 0 || index >= size) {
      throw new PreconditionException(badElementIndex(index, size, desc));
    }
    return index;
  }

  private static String badElementIndex(int index, int size, String desc) {
    if (index < 0) {
      return lenientFormat("%s (%s) must not be negative", desc, index);
    } else if (size < 0) {
      throw new PreconditionException("negative size: " + size);
    } else { // index >= size
      return lenientFormat("%s (%s) must be less than size (%s)", desc, index, size);
    }
  }

  /**
   * Check position index int.
   *
   * @param index the index
   * @param size  the size
   * @return the int
   */
  public static int checkPositionIndex(int index, int size) {
    return checkPositionIndex(index, size, "index");
  }

  /**
   * Check position index int.
   *
   * @param index the index
   * @param size  the size
   * @param desc  the desc
   * @return the int
   */
  public static int checkPositionIndex(int index, int size, String desc) {
    // Carefully optimized for execution by hotspot (explanatory comment above)
    if (index < 0 || index > size) {
      throw new PreconditionException(badPositionIndex(index, size, desc));
    }
    return index;
  }

  private static String badPositionIndex(int index, int size, String desc) {
    if (index < 0) {
      return lenientFormat("%s (%s) must not be negative", desc, index);
    } else if (size < 0) {
      throw new PreconditionException("negative size: " + size);
    } else { // index > size
      return lenientFormat("%s (%s) must not be greater than size (%s)", desc, index, size);
    }
  }

  /**
   * Check position indexes.
   *
   * @param start the start
   * @param end   the end
   * @param size  the size
   */
  public static void checkPositionIndexes(int start, int end, int size) {
    // Carefully optimized for execution by hotspot (explanatory comment above)
    if (start < 0 || end < start || end > size) {
      throw new PreconditionException(badPositionIndexes(start, end, size));
    }
  }

  private static String badPositionIndexes(int start, int end, int size) {
    if (start < 0 || start > size) {
      return badPositionIndex(start, size, "start index");
    }
    if (end < 0 || end > size) {
      return badPositionIndex(end, size, "end index");
    }
    // end < start
    return lenientFormat("end index (%s) must not be less than start index (%s)", end, start);
  }
}
