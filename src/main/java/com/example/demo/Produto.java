package com.example.demo;

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
