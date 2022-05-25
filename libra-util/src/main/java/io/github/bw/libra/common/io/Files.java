package io.github.bw.libra.common.io;

import io.github.bw.libra.common.base.Preconditions;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public final class Files {

  private Files() {
  }

  public static BufferedReader newReader(File file, Charset charset) throws FileNotFoundException {
    Preconditions.checkNotNull(file);
    Preconditions.checkNotNull(charset);
    return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
  }

  public static List<String> readLines(File file, Charset charset) throws IOException {
    BufferedReader reader = newReader(file, charset);
    return reader.lines().collect(Collectors.toList());
  }

  public static String readAsString(File file, Charset charset) throws IOException {
    BufferedReader reader = newReader(file, charset);
    return reader.lines().collect(Collectors.joining());
  }

  public static String readAsString(String filePath, Charset charset) throws IOException {
    Preconditions.checkNotNull(filePath);
    File file = new File(filePath);
    return readAsString(file, charset);
  }

  public static BufferedWriter newWriter(File file, Charset charset) throws FileNotFoundException {
    Preconditions.checkNotNull(file);
    Preconditions.checkNotNull(charset);
    return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
  }
}
