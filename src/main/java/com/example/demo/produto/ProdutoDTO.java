package com.example.demo.produto;

import com.example.demo.core.ModelDTO;

public class ProdutoDTO extends ModelDTO {

	private int id;
	private String descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
