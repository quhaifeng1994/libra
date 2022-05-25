package io.github.bw.libra.common.crypto;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5 {

  private MD5() {
  }

  public static String digestHex(String origin) {
    if (origin == null) {
      return null;
    }

    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      byte[] bs = digest.digest(origin.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();
      for (byte b : bs) {
        int c = b & 255;
        if (c < 16) {
          sb.append("0");
        }
        sb.append(Integer.toHexString(c));
      }
      return sb.toString();
    } catch (Exception e) {
      return null;
    }
  }
}
