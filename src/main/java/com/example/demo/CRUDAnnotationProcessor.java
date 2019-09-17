package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.core.crud.CRUD;

@Component
public class CRUDAnnotationProcessor implements BeanPostProcessor {

	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		CRUD crudAnnotation = bean.getClass().getAnnotation(CRUD.class);
		if (crudAnnotation != null) {
			String mapping = crudAnnotation.value();
			
			
			System.err.println("Passou " + bean + " " + beanName);
		}
		return bean;
	}
}
