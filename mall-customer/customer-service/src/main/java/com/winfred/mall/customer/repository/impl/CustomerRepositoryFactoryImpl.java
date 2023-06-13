package com.winfred.mall.customer.repository.impl;

import com.winfred.common.enu.EnumStorageType;
import com.winfred.mall.customer.repository.CustomerRepository;
import com.winfred.mall.customer.repository.CustomerRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * @author winfred958
 */
@Repository
public class CustomerRepositoryFactoryImpl implements CustomerRepositoryFactory {

  private static final EnumStorageType DEFAULT_STORAGE_TYPE = EnumStorageType.MySQL;

  private static final Map<EnumStorageType, CustomerRepository> CUSTOMER_REPOSITORY_MAP = new HashMap<>();

  @SuppressWarnings("all")
  @Autowired
  private  ApplicationContext applicationContext;

  @PostConstruct
  public void initRepositoryFactory(){
    CUSTOMER_REPOSITORY_MAP.put(EnumStorageType.MySQL, new CustomerRepositoryJdbc(applicationContext));
  }

  @Override
  public CustomerRepository getCustomerRepository() {
    return getCustomerRepository(DEFAULT_STORAGE_TYPE);
  }

  @Override
  public CustomerRepository getCustomerRepository(EnumStorageType storageType) {
    return CUSTOMER_REPOSITORY_MAP.get(storageType);
  }
}
