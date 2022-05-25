package io.github.bw.libra.server.monitor;

import io.github.bw.libra.common.base.Preconditions;
import io.github.bw.libra.common.base.ValidateResult;
import io.github.bw.libra.common.collect.CollectionUtil;
import io.github.bw.libra.common.primitives.StringUtil;
import io.github.bw.libra.server.config.AlarmConfig;
import io.github.bw.libra.server.config.LibraConfig;
import io.github.bw.libra.server.config.ServiceConfig;
import io.github.bw.libra.server.discovery.DiscoveryClient;
import io.github.bw.libra.server.discovery.RegistrationCenterSupplier;
import io.github.bw.libra.server.discovery.ServiceInstance;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class ServiceMonitor {

  @Autowired
  private LibraConfig libraConfig;

  @Autowired
  private RegistrationCenterSupplier registrationCenterSupplier;

  public void monitor() {
    log.info("开始检测服务健康状态");
    List<ServiceConfig> services = libraConfig.getServices();
    if (CollectionUtil.isEmpty(services)) {
      log.info("结束检测服务健康状态，无服务需要检测");
    }
    List<ServiceConfig> needCheckServices = services.stream()
        .filter(it -> it.getAlarm() != null && it.getAlarm().isCheck()).toList();

    if (CollectionUtil.isEmpty(needCheckServices)) {
      log.info("结束检测服务健康状态，无服务需要检测");
    }

    StringBuilder result = new StringBuilder("\n");
    for (ServiceConfig needCheckService : needCheckServices) {
      ValidateResult validateResult = monitorService(needCheckService);
      result.append("\t\t\t\t\t|-\t").append(String.format("%-40s", needCheckService.getName()));
      if (validateResult.isPass()) {
        result.append("\t检测成功").append("\n");
      } else {
        result.append("\t检测失败，原因为：").append(validateResult.getMessage()).append("\n");
      }
    }

    log.info("结束检测服务健康状态: 最终结果为 {}", result);
  }

  private ValidateResult monitorService(ServiceConfig serviceConfig) {
    String name = serviceConfig.getName();
    try {
      String serviceId = serviceConfig.getServiceId();
      String registrationCenter = serviceConfig.getRegistrationCenter();
      AlarmConfig alarm = serviceConfig.getAlarm();

      DiscoveryClient discoveryClient = registrationCenterSupplier.getDiscoveryClient(registrationCenter);
      Preconditions.checkArgument(discoveryClient != null, "无法查询到注册中心 %s", registrationCenter);
      List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
      Preconditions.checkArgument(instances.size() >= alarm.getMinSize(), "服务数量不对，告警最小数量应为 %s, 现在为 %s",
          alarm.getMinSize(), instances.size());

      if (CollectionUtil.isNotEmpty(alarm.getMustHosts())) {
        Set<String> nowServiceHostSet = instances.stream().map(ServiceInstance::getHost).collect(Collectors.toSet());
        Set<String> copyMushHosts = new HashSet<>(alarm.getMustHosts());
        copyMushHosts.removeAll(nowServiceHostSet);
        Preconditions.checkArgument(copyMushHosts.isEmpty(), "服务 MustHosts 不对，告警中 %s 必须存在, 现在 %s 不存在",
            alarm.getMustHosts(), copyMushHosts);
      }

      return ValidateResult.SUCCESS;
    } catch (Exception e) {
      log.error("项目检测失败 {}, 原因：{}", name, e.getMessage(), e);
      return ValidateResult.failure(StringUtil.lenientFormat("项目检测失败 %s, 原因：%s", name, e.getMessage()));
    }
  }

}
