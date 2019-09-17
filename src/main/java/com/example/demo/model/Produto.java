package com.example.demo.model;

import com.example.demo.core.Model;

public class Produto extends Model<Integer> {

	private int id;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
