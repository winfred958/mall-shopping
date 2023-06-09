package com.winfred.mall.customer.config;

import com.winfred.common.entity.BaseResponse;
import com.winfred.common.entity.DefaultException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.net.ConnectException;

/**
 * 处理 rest 层的全局异常
 *
 * @author kai.hu@shuyun.com
 */
@Slf4j
@Configuration
@Aspect
public class GlobalExceptionConfig {

  @Around(value = "execution(* com.winfred.mall.customer.rest.*.*(..))")
  public Object aroundExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Object result = null;
    try {
      result = joinPoint.proceed();
    } catch (DefaultException defaultException) {
      log.error("", defaultException);
      return Mono.just(defaultException.getResponse());
    } catch (ConnectException connectException) {
      log.error("ConnectException ", connectException);
      return Mono.just(BaseResponse.send(connectException));
    } catch (Exception e) {
      log.error("", e);
      return Mono.just(BaseResponse.send(e));
    }
    return result;
  }
}
