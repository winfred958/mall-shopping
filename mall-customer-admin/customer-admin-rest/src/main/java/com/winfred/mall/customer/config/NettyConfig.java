package com.winfred.mall.customer.config;

import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannelRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.boot.web.reactive.server.ConfigurableReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.netty.http.server.HttpServer;

/**
 * @author kai.hu@shuyun.com
 */
@Slf4j
@Configuration
@Order(-1)
public class NettyConfig {

  @Value(value = "${reactor.netty.ioSelectCount:16}")
  private Integer ioSelectCount;

  @Value(value = "${reactor.netty.ioWorkerCount:128}")
  private Integer ioWorkerCount;

  @Value(value = "${netty.work.group.size:256}")
  private Integer workGroupThreads;

  @Bean
  public ConfigurableReactiveWebServerFactory nettyServerConfig() {
    final String worker = String.valueOf(Math.max(Runtime.getRuntime().availableProcessors() * 2, ioWorkerCount));
    final String selector = String.valueOf(Math.max(Runtime.getRuntime().availableProcessors(), ioSelectCount) - 1);
    System.setProperty("reactor.netty.ioWorkerCount", worker);
    System.setProperty("reactor.netty.ioSelectCount", selector);
    final NettyReactiveWebServerFactory nettyReactiveWebServerFactory = new NettyReactiveWebServerFactory();
    NettyServerCustomizer customizer = new NettyServerCustomizer() {

      final EventLoopGroup bossGroup = new NioEventLoopGroup(16);
      final EventLoopGroup workGroup = new NioEventLoopGroup(workGroupThreads);

      @Override
      public HttpServer apply(HttpServer httpServer) {
        httpServer
            .tcpConfiguration(tcpServer -> {
              tcpServer
                  .doOnChannelInit((connectionObserver, channel, remoteAddress) -> {
                    channel
                        .config()
                        .setAutoClose(true)
                        .setAutoRead(true)
                        .setAllocator(PooledByteBufAllocator.DEFAULT)
                        .setRecvByteBufAllocator(new ServerChannelRecvByteBufAllocator())
                    ;
                  })
                  //.group(bossGroup, workGroup)
                  // 临时存放已完成三次握手的请求的队列的最大长度
                  .option(ChannelOption.SO_BACKLOG, 8192)
                  // 开启缓冲池重用
                  .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                  .option(ChannelOption.TCP_NODELAY, true)
                  .option(ChannelOption.SO_RCVBUF, 8 * 1024)
                  .option(ChannelOption.SO_SNDBUF, 8 * 1024)
                  .option(ChannelOption.SINGLE_EVENTEXECUTOR_PER_GROUP, false)
                  .option(ChannelOption.AUTO_CLOSE, true)
                  .option(ChannelOption.SO_LINGER, 0)

                  .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                  .childOption(ChannelOption.SO_RCVBUF, 8 * 1024)
                  .childOption(ChannelOption.SO_SNDBUF, 8 * 1024)
                  .childOption(ChannelOption.TCP_NODELAY, true)
                  .runOn(workGroup)
              //.channel(NioServerSocketChannel.class)
              ;
              return tcpServer;
            });
        return httpServer;
      }
    };
    nettyReactiveWebServerFactory.addServerCustomizers(customizer);
    return nettyReactiveWebServerFactory;
  }
}
