package io.github.bw.libra.server.task;

import io.github.bw.libra.server.monitor.ServiceMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class MonitorTask {

  @Autowired
  private ServiceMonitor serviceMonitor;

  @Scheduled(fixedDelay = 10_000)
  public void serviceMonitor() {
    serviceMonitor.monitor();
  }
}
