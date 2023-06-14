package com.winfred.mall.oauth2.auth.storage;

import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winfred958
 */
public interface AuthStoragePipeline {
  /**
   * 开始处理
   *
   * @param authorization
   */
  void doSave(final OAuth2Authorization authorization);

  void doRemove(final OAuth2Authorization authorization);

  AuthStoragePipeline addHandler(AuthStorageHandler handler);

  public static AuthStoragePipeline build() {
    return new AuthStoragePipeline() {

      private final List<AuthStorageHandler> handlers = new ArrayList<>();

      @Override
      public void doSave(final OAuth2Authorization authorization) {
        for (AuthStorageHandler handler : handlers) {
          if (handler.hit(authorization)) {
            handler.save(authorization);
          }
        }
      }

      @Override
      public void doRemove(final OAuth2Authorization authorization) {
        for (AuthStorageHandler handler : handlers) {
          if (handler.hit(authorization)) {
            handler.remove(authorization);
          }
        }
      }

      @Override
      public AuthStoragePipeline addHandler(final AuthStorageHandler handler) {
        handlers.add(handler);
        return this;
      }
    };
  }
}
