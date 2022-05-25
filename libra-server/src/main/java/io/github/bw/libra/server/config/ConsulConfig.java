package io.github.bw.libra.server.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ConsulConfig {

  private String host;
  private Integer port;
  private String token;
}
