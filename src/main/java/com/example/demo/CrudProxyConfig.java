package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrudProxyConfig {

    @Bean
    public CrudProxy crudProxy() {
        return new CrudProxy();
    }

    @Bean(name = "crudProxyBeanFactory")
    public CrudProxyBeanFactory crudProxyBeanFactory() {
        return new CrudProxyBeanFactory();
    }
}
