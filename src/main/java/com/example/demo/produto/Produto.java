package com.example.demo.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.core.Model;

@Entity
public class Produto extends Model<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
