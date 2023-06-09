package com.winfred.mall.customer.repository;

import com.winfred.common.enu.EnumStorageType;

/**
 * @author winfred958
 */
public interface CustomerRepositoryFactory {

  CustomerRepository getCustomerRepository();

  CustomerRepository getCustomerRepository(EnumStorageType storageType);
}
