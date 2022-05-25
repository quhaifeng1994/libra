package io.github.bw.libra.server.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NacosConfig {

  private String namespace;
  private String serverAddr;
  private String group;
  private String username;
  private String password;
}
