package com.example.demo.core.crud.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.example.demo.core.crud.CRUD;

public class CrudControllerProxy implements InvocationHandler {

	private CRUD crud;
	private CrudBaseController crudBaseController;

	public void setCrud(CRUD crud) {
		this.crud = crud;
	}

	public void setCrudBaseController(CrudBaseController crudBaseController) {
		this.crudBaseController = crudBaseController;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object request = (null != args && args.length > 0) ? args[0] : null;
		
		
		Method innerMethod = crudBaseController.getClass().getMethod(method.getName(), method.getParameterTypes());
		
		
        System.err.println(crud + " " + request);
        
		return innerMethod.invoke(crudBaseController, args);
	}

}
