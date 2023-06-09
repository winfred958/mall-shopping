package com.winfred.mall.customer.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author kai.hu@shuyun.com
 */
@Slf4j
@AutoConfigureBefore(JacksonAutoConfiguration.class)
@Configuration
public class JacksonConfig {

  @Bean(name = "globalJacksonMapper")
  @Primary
  @ConditionalOnMissingBean(ObjectMapper.class)
  public ObjectMapper configObjectMapper(@Autowired Jackson2ObjectMapperBuilder builder) {
    final ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    // null 不参与序列化
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper;
  }
}
