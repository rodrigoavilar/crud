package com.example.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CrudProxy implements InvocationHandler {

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
