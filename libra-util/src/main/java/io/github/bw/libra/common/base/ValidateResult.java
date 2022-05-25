package io.github.bw.libra.common.base;

public final class ValidateResult {

  public static final ValidateResult SUCCESS = new ValidateResult(true, null);

  private final boolean pass;
  private final String message;

  private ValidateResult(boolean pass, String message) {
    this.pass = pass;
    this.message = message;
  }

  public static ValidateResult failure(String message) {
    return new ValidateResult(false, message);
  }

  public boolean isFailure() {
    return !pass;
  }

  public boolean isPass() {
    return pass;
  }

  public String getMessage() {
    return message;
  }
}
