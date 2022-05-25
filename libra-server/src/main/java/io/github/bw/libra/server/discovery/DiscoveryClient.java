package io.github.bw.libra.server.discovery;

import java.util.List;

public interface DiscoveryClient {

  List<ServiceInstance> getInstances(String serviceId);

  List<String> getServices();
}
