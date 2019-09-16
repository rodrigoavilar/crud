package com.example.demo;

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
public class Mapeador implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {

		ConfigurableApplicationContext applicationContext = event.getApplicationContext();

		RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths("teste").methods(RequestMethod.GET)
				.produces(MediaType.APPLICATION_JSON_VALUE).build();

		try {
			requestMappingHandlerMapping.registerMapping(requestMappingInfo,
					applicationContext.getBean("produtos"),
					XController.class.getMethod("search", HttpServletRequest.class));
			
		} catch (BeansException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
