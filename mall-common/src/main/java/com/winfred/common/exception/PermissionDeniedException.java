package com.winfred.common.exception;

public class PermissionDeniedException extends RuntimeException {
  public PermissionDeniedException(String message) {
    super(message);
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    return this;
  }
}
