package com.winfred.mall.customer.service.imp;

import com.winfred.mall.customer.repository.CustomerRepository;
import com.winfred.mall.customer.repository.CustomerRepositoryFactory;
import com.winfred.mall.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 根据配置, 决定使用具体的 {@link CustomerRepository}
 *
 * @author winfred958
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepositoryFactory customerRepositoryFactory;

  @Autowired
  public CustomerServiceImpl(CustomerRepositoryFactory customerRepositoryFactory) {
    this.customerRepositoryFactory = customerRepositoryFactory;
  }

  @Override
  public UserDetails getUserDetails(String username) {
    CustomerRepository customerRepository = customerRepositoryFactory.getCustomerRepository();
    return customerRepository.getUserDetails(username);
  }
}
