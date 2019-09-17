package com.example.demo.core.crud;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.core.crud.controller.CrudBaseController;
import com.example.demo.core.crud.controller.CrudControllerProxy;

public class CrudProxyBeanFactory {

	private Map<String, Object> proxies = new HashMap<String, Object>();

	@Autowired
	private CrudRegistry mapeador;

	@SuppressWarnings("unchecked")
	public <C> C createCrudControllerProxyBean(ClassLoader classLoader, Class<C> clazz, CRUD crud) {

		Object proxy = proxies.get(crud.value());

		if (proxy == null) {
			CrudControllerProxy crudProxy = new CrudControllerProxy();
			crudProxy.setCrud(crud);
			crudProxy.setCrudBaseController(new CrudBaseController());
			proxy = Proxy.newProxyInstance(classLoader, new Class[] { clazz }, crudProxy);
			proxies.put(crud.value(), proxy);
			mapeador.add(crud.value(), clazz);
		}

		return (C) proxy;
	}


	
}
