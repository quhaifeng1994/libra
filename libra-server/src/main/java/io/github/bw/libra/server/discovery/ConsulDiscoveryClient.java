package io.github.bw.libra.server.discovery;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.catalog.CatalogServicesRequest;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.HealthService;
import io.github.bw.libra.common.collect.CollectionUtil;
import io.github.bw.libra.server.config.ConsulConfig;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;

public class ConsulDiscoveryClient implements DiscoveryClient {

  private ConsulConfig config;
  private ConsulClient client;

  @SneakyThrows
  public ConsulDiscoveryClient(ConsulConfig consulConfig) {
    this.config = consulConfig;
    client = new ConsulClient(consulConfig.getHost(), consulConfig.getPort());
  }

  @SneakyThrows
  @Override
  public List<ServiceInstance> getInstances(String serviceId) {
    HealthServicesRequest request = HealthServicesRequest.newBuilder().setPassing(true)
        .setQueryParams(QueryParams.DEFAULT).setToken(config.getToken()).build();

    Response<List<HealthService>> services = client.getHealthServices(serviceId, request);
    if (services == null) {
      return Collections.emptyList();
    }

    if (CollectionUtil.isEmpty(services.getValue())) {
      return Collections.emptyList();
    }

    return services.getValue().stream().map(it -> {
      ServiceInstance instance = new ServiceInstance();
      it.getNode();
      instance.setServiceId(it.getService().getService());
      instance.setHost(it.getService().getAddress());
      instance.setPort(it.getService().getPort());
      instance.setMetadata(it.getService().getMeta());
      return instance;
    }).toList();
  }

  @SneakyThrows
  @Override
  public List<String> getServices() {
    CatalogServicesRequest request = CatalogServicesRequest.newBuilder()
        .setToken(config.getToken())
        .setQueryParams(QueryParams.DEFAULT).build();
    Response<Map<String, List<String>>> services = client.getCatalogServices(request);
    if (services == null) {
      return Collections.emptyList();
    }
    return Lists.newArrayList(services.getValue().keySet());
  }
}
