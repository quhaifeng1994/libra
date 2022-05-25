package io.github.bw.libra.server.discovery;

import io.github.bw.libra.common.collect.CollectionUtil;
import io.github.bw.libra.server.config.LibraConfig;
import io.github.bw.libra.server.config.RegistrationCenterConfig;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class RegistrationCenterSupplier {

  @Autowired
  private LibraConfig libraConfig;

  Map<String, DiscoveryClient> discoveryClientMap = new ConcurrentHashMap<>();

  @PostConstruct
  public void init() {
    List<RegistrationCenterConfig> registrationCenters = libraConfig.getRegistrationCenters();
    if (CollectionUtil.isEmpty(registrationCenters)) {
      return;
    }

    for (RegistrationCenterConfig registrationCenter : registrationCenters) {
      if (registrationCenter.getType().equals("nacos")) {
        NacosDiscoveryClient nacosDiscoveryClient = new NacosDiscoveryClient(registrationCenter.getNacos());
        discoveryClientMap.put(registrationCenter.getName(), nacosDiscoveryClient);
      } else if (registrationCenter.getType().equals("consul")) {
        ConsulDiscoveryClient consulDiscoveryClient = new ConsulDiscoveryClient(registrationCenter.getConsul());
        discoveryClientMap.put(registrationCenter.getName(), consulDiscoveryClient);
      }
    }
  }

  public DiscoveryClient getDiscoveryClient(String name) {
    return discoveryClientMap.get(name);
  }

}
