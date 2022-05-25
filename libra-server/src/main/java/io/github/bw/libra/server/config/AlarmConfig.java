package io.github.bw.libra.server.config;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AlarmConfig {

  private boolean check = true;
  private int minSize;
  private Set<String> mustHosts;
}
