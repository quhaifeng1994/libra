package io.github.bw.libra.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LibraServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraServerApplication.class, args);
  }

}
