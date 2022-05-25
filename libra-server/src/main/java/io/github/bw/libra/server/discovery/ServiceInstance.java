package io.github.bw.libra.server.discovery;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ServiceInstance {

  private String serviceId;
  private String host;
  private int port;
  private Map<String, String> metadata;
}
