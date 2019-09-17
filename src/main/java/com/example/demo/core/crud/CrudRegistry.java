package com.example.demo.core.crud;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class CrudRegistry implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	private Map<String, Class<?>> beanDefinitions = new HashMap<String, Class<?>>();


	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {

		ConfigurableApplicationContext applicationContext = event.getApplicationContext();


		for (Map.Entry<String, Class<?>> entry : beanDefinitions.entrySet()) {
			String beanName = entry.getKey();
			Class<?> beanClass = entry.getValue();
			System.err.println(">" + beanName + " " + entry.getValue());

			mapRestMethods(applicationContext, beanName, beanClass);
		}
	}

	private void mapRestMethods(ConfigurableApplicationContext applicationContext, String beanName,
			Class<?> beanClass) {
		RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths(beanName).methods(RequestMethod.GET)
				.produces(MediaType.APPLICATION_JSON_VALUE).build();

		try {
			requestMappingHandlerMapping.registerMapping(requestMappingInfo, applicationContext.getBean(beanName),
					beanClass.getMethod("search", HttpServletRequest.class));

		} catch (BeansException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(String name, Class<?> clazz) {
		beanDefinitions.put(name, clazz);
	}
	
}
