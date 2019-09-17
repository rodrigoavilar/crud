package com.example.demo.core.crud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrudProxyConfig {

    @Bean(name = "crudProxyBeanFactory")
    public CrudProxyBeanFactory crudProxyBeanFactory() {
        return new CrudProxyBeanFactory();
    }
}
