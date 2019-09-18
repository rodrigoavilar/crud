package com.example.demo.core.crud;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
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
import org.springframework.web.servlet.mvc.method.RequestMappingInfo.Builder;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.example.demo.core.ModelDTO;

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

		Object bean = applicationContext.getBean(beanName);
		Class<?> dtoClass = ModelDTO.class; 
		//Class<?> dtoClass = (Class<?>) ((ParameterizedType) beanClass.getGenericInterfaces()[0]).getActualTypeArguments()[2];

		registerRequestMappingInfo(beanName, beanClass, bean, RequestMethod.GET, null, MediaType.APPLICATION_JSON_VALUE,
				"search", HttpServletRequest.class);

		registerRequestMappingInfo(beanName, beanClass, bean, RequestMethod.POST,
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE, "insert", HttpServletRequest.class,
				dtoClass, Locale.class);

	}

	private void registerRequestMappingInfo(String path, Class<?> beanClass, Object bean, RequestMethod requestMethod,
			String consumes, String produces, String methodName, Class<?>... methodArgs) {

		Builder builder = RequestMappingInfo.paths(path).methods(requestMethod);

		if (consumes != null) {
			builder.consumes(consumes);
		}

		if (produces != null) {
			builder.produces(produces);
		}

		RequestMappingInfo requestMappingInfo = builder.build();

		try {
			Method method = beanClass.getMethod(methodName, methodArgs);
			requestMappingHandlerMapping.registerMapping(requestMappingInfo, bean, method);

		} catch (BeansException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	public void add(String name, Class<?> clazz) {
		beanDefinitions.put(name, clazz);
	}

}
