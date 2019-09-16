package com.example.demo;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CrudProxyBeanFactory {

	private Map<String, Object> proxies = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	public <C> C createCrudProxyBean(ClassLoader classLoader, Class<C> clazz, CRUD crud) {

		Object proxy = proxies.get(crud.value());

		if (proxy == null) {
			CrudProxy crudProxy = new CrudProxy();
			crudProxy.setCrud(crud);
			proxy = Proxy.newProxyInstance(classLoader, new Class[] { clazz }, crudProxy);
			proxies.put(crud.value(), proxy);
		}

		return (C) proxy;
	}
}
