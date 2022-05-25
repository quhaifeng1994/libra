package io.github.bw.libra.server.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RegistrationCenterConfig {

  private String name;
  private String type;
  private NacosConfig nacos;
  private ConsulConfig consul;
}
