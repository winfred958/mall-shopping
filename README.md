# 介绍

## nacos本地测试环境

### 1.安装 docker

- [官方文档](https://docs.docker.com/engine/install/centos/)

### 2.配置环境&国内镜像

- current user 添加 docker 用户组
    - ```shell
      usermod -aG docker ${USER}
      ```
- vim /etc/docker/daemon.json
    - ```json
      {
        "registry-mirrors": [
          "https://registry.docker-cn.com",
          "https://docker.mirrors.ustc.edu.cn",
          "http://hub-mirror.c.163.com",
          "https://cr.console.aliyun.com/"
        ]
      }
      ```
- 重启docker

### 3.安装 docker compose

- [官方文档](https://docs.docker.com/compose/install/)

### 4. dev 基础环境

- 启动 nacos, shenyu
    - ```shell
      docker-compose deploy/docker/compose-dev.yaml up -d
      ```

## 使用的组件介绍

### 1. [Spring Cloud Alibaba](https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md)

#### 1.1 [nacos](https://github.com/alibaba/Nacos)

```text
服务注册中心, 提供服务发现能力
配置中心, 提供可视化的配置, client端有刷新配置功能
```

#### 1.2  [shenyu](https://github.com/dromara/soul)

```text
业务网关
```

#### 1.3 [sentinel](https://github.com/alibaba/Sentinel)

- 把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性.
- [spring-cloud-starter-alibaba-sentinel](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel)
- spring cloud gateway 整合
    - [spring cloud gateway 整合文档](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel#spring-cloud-gateway-%E6%94%AF%E6%8C%81)

#### 1.4 [dubbo](https://github.com/SpringCloud/spring-cloud-dubbo)

## 其他

### 代码自动生成

- 模块名: code-generator
- 代码自动生成相关配置
    - [Constant](code-generator/src/main/java/com/winfred/dataworks/general/Constant.java)
- 代码生成模板配置(不会经常变更)
    - [templates](code-generator/src/main/resources/templates)
- 生成代码
    - 运行 [CodeGeneral](code-generator/src/main/java/com/winfred/dataworks/general/CodeGeneral.java)
