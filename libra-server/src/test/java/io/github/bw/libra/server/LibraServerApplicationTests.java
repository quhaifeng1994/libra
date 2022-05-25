package io.github.bw.libra.server;

import io.github.bw.libra.server.config.LibraConfig;
import io.github.bw.libra.server.monitor.ServiceMonitor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class LibraServerApplicationTests {

  @Autowired
  private LibraConfig libraConfig;

  @Autowired
  private ServiceMonitor serviceMonitor;

  @Test
  void contextLoads() {
    log.info("libraConfig: {}", libraConfig);
    serviceMonitor.monitor();
  }

}
