# libra

你可以在 `libra-server/src/main/resources/application-local.yaml` 中添加配置来启动

```yaml
libra:
  registration-centers: # 注册中心的配置
    - name: dailynacos  # 名称，要求唯一
      type: nacos       # 注册中心类型，暂时只支持 nacos
      nacos: # nacos 的配置
        namespace:
        server-addr:
        group: DEFAULT_GROUP
        username:
        password:
  services: # 要监控的服务
    - name: 用户服务                    # 告警时用于输出的名称，建议唯一 
      service-id: user-server          # 注册中心上对应的 serviceId 
      registration-center: dailynacos  # 注册中心的名称，对应上面的配置
      alarm: # 告警的配置，可不配，如果不配则不告警
        check: true                    # 是否进行检测，默认为 true, 可以不配置，当为 false 时，则不进行监控
        min-size: 1                    # 存活服务的的最小数量，低于最小数量则告警
        mustHosts: # 必须匹配上的 hosts 列表，如果配置的 hosts 中不在存活的实例中，则告警，可以不配 
          - 127.0.0.1
```