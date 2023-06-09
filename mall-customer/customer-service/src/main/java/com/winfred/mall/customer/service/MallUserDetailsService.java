package com.winfred.mall.customer.service;

import com.winfred.mall.customer.entity.RoleInfoEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

import java.util.Collection;

/**
 * @author winfred958
 */
public interface MallUserDetailsService extends ReactiveUserDetailsService {

  Collection<RoleInfoEntity> getUserRoles(Long userId);
}
