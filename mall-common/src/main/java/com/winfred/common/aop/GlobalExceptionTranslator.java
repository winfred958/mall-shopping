package com.winfred.common.aop;


import com.winfred.common.entity.BaseResponse;
import com.winfred.common.entity.EnumResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionTranslator {

  @ExceptionHandler(value = {
      NullPointerException.class
  })
  public BaseResponse handlerServiceException(NullPointerException e) {
    log.error("", e);
    return BaseResponse.send(EnumResponseType.DEFAULT_ERROR);
  }


  @ExceptionHandler(value = {
      Throwable.class
  })
  public BaseResponse handlerXXXException(Throwable e) {
    log.error("", e);
    return BaseResponse.send(EnumResponseType.DEFAULT_ERROR);
  }
}
