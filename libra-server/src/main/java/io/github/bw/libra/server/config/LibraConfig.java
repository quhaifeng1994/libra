package io.github.bw.libra.server.config;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("libra")
@Setter
@Getter
@ToString
public class LibraConfig {

  private List<RegistrationCenterConfig> registrationCenters;
  private List<ServiceConfig> services;
}
