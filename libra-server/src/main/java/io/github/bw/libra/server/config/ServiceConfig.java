package io.github.bw.libra.server.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ServiceConfig {

  private String name;
  private String serviceId;
  private String registrationCenter;
  private AlarmConfig alarm;
}
