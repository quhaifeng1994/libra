package io.github.bw.libra.server.discovery;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ListView;
import io.github.bw.libra.common.collect.CollectionUtil;
import io.github.bw.libra.server.config.NacosConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import lombok.SneakyThrows;

public class NacosDiscoveryClient implements DiscoveryClient {

  private NacosConfig nacosConfig;
  private NamingService namingService;

  @SneakyThrows
  public NacosDiscoveryClient(NacosConfig nacosConfig) {
    this.nacosConfig = nacosConfig;
    Properties properties = new Properties();
    properties.put(PropertyKeyConst.NAMESPACE, nacosConfig.getNamespace());
    properties.put(PropertyKeyConst.USERNAME, nacosConfig.getUsername());
    properties.put(PropertyKeyConst.PASSWORD, nacosConfig.getPassword());
    properties.put(PropertyKeyConst.SERVER_ADDR, nacosConfig.getServerAddr());
    this.namingService = NacosFactory.createNamingService(properties);
  }

  @SneakyThrows
  @Override
  public List<ServiceInstance> getInstances(String serviceId) {
    List<Instance> allInstances = namingService.selectInstances(serviceId, nacosConfig.getGroup(), true);
    if (CollectionUtil.isNotEmpty(allInstances)) {
      return allInstances.stream().map(it -> {
        ServiceInstance instance = new ServiceInstance();
        instance.setServiceId(it.getServiceName());
        instance.setHost(it.getIp());
        instance.setPort(it.getPort());
        instance.setMetadata(it.getMetadata());
        return instance;
      }).toList();
    }
    return new ArrayList<>();
  }

  @SneakyThrows
  @Override
  public List<String> getServices() {
    ListView<String> servicesOfServer = namingService.getServicesOfServer(1, 100_000, nacosConfig.getGroup());
    return servicesOfServer.getData() == null ? new ArrayList<>() : servicesOfServer.getData();
  }
}
