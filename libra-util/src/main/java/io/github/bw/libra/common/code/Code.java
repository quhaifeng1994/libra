package io.github.bw.libra.common.code;

public class Code {

  public static final Code SUCCESS = new Code(0, "成功");

  private final int no;
  private final String message;

  public Code(int no, String message) {
    this.no = no;
    this.message = message;
  }

  public int getNo() {
    return this.no;
  }

  public String getMessage() {
    return this.message;
  }
}
