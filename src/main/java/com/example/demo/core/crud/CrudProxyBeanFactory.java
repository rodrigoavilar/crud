package com.example.demo.core.crud;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.core.Model;
import com.example.demo.core.ModelDTO;
import com.example.demo.core.crud.controller.CrudBaseController;
import com.example.demo.core.crud.controller.CrudControllerProxy;
import com.example.demo.core.repository.CrudBaseRepository;
import com.example.demo.core.service.CrudService;

public class CrudProxyBeanFactory {

	private Map<String, Object> proxies = new HashMap<String, Object>();

	@Autowired
	private CrudRegistry crudRegistry;

	@Autowired
	private ConfigurableApplicationContext context;

	@SuppressWarnings("unchecked")
	public <C> C createCrudControllerProxyBean(ClassLoader classLoader, Class<C> controllerInterface, CRUD crud) {

		Object proxy = proxies.get(crud.value());

		if (proxy == null) {

			CrudBaseRepository<Model<Serializable>, Serializable> repository = (CrudBaseRepository<Model<Serializable>, Serializable>) context
					.getBean(crud.repository());

			CrudService service = new CrudService();
			service.setRepository(repository);

			Class<ModelDTO> dtoClass = (Class<ModelDTO>) ((ParameterizedType) controllerInterface.getGenericInterfaces()[0]).getActualTypeArguments()[2];
			Class<Model<Serializable>> modelClass = (Class<Model<Serializable>>) ((ParameterizedType) controllerInterface.getGenericInterfaces()[0]).getActualTypeArguments()[0];
			
			CrudBaseController crudBaseController = new CrudBaseController();
			crudBaseController.setService(service);
			crudBaseController.setDtoClass(dtoClass);
			crudBaseController.setModelClass(modelClass);


			CrudControllerProxy crudProxy = new CrudControllerProxy();
			crudProxy.setCrud(crud);
			crudProxy.setCrudBaseController(crudBaseController);

			proxy = Proxy.newProxyInstance(classLoader, new Class[] { controllerInterface }, crudProxy);
			proxies.put(crud.value(), proxy);
			crudRegistry.add(crud.value(), controllerInterface);
		}

		return (C) proxy;
	}

}
