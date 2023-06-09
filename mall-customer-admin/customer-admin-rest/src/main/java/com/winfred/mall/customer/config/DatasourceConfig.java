package com.winfred.mall.customer.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;


/**
 * @author winfred
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {
    "com.winfred.mall.customer.admin.mapper"
})
public class DatasourceConfig implements TransactionManagementConfigurer {

  private TransactionManager transactionManager;

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnBean(DataSource.class)
  public TransactionManager txManager(DataSource dataSource) {
    transactionManager = new DataSourceTransactionManager(dataSource);
    return transactionManager;
  }

  @Override
  public TransactionManager annotationDrivenTransactionManager() {
    return transactionManager;
  }
}
