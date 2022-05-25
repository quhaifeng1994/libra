package io.github.bw.libra.common.exception;

public class PreconditionIllegalPermissionsException extends PreconditionException {

  public PreconditionIllegalPermissionsException(String errorMessage) {
    super(errorMessage);
  }

  public PreconditionIllegalPermissionsException() {
    super();
  }
}
