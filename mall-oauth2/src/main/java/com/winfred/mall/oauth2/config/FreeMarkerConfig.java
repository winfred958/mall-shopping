package com.winfred.mall.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author winfred958
 */
@Configuration
public class FreeMarkerConfig {

  @Bean
  public FreeMarkerConfigurer freemarkerConfig() throws IOException {
    FreeMarkerConfigurer freemarkerConfig = new FreeMarkerConfigurer();
    freemarkerConfig.setTemplateLoaderPaths("classpath:/templates/", "classpath:/static/**");
    freemarkerConfig.setDefaultEncoding("UTF-8");

    Properties settings = new Properties();
    settings.setProperty("template_update_delay", "0");
    settings.setProperty("url_escaping_charset", "UTF-8");
    settings.setProperty("locale", "zh_CN");
    settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
    settings.setProperty("number_format", "#.##");
    freemarkerConfig.setFreemarkerSettings(settings);
    return freemarkerConfig;
  }

  @Bean
  public FreeMarkerViewResolver freemarkerViewResolver() {
    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
    resolver.setPrefix("");
    resolver.setSuffix(".ftl");
    resolver.setContentType("text/html; charset=UTF-8");
    resolver.setRequestContextAttribute("request");
    return resolver;
  }
}
