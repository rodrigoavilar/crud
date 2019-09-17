package com.example.demo.core.crud.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.example.demo.core.crud.CRUD;

public class CrudControllerProxy implements InvocationHandler {

	private CRUD crud;

	public void setCrud(CRUD crud) {
		this.crud = crud;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object request = (null != args && args.length > 0) ? args[0] : null;
		
        System.err.println(crud + " " + request);
        
		return null;
	}

}
