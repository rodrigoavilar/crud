package com.example.demo.produto;

import com.example.demo.core.crud.CRUD;
import com.example.demo.core.crud.controller.CrudController;

@CRUD(value = "produtos", repository = ProdutoRepository.class)
public interface ProdutoController extends CrudController<Produto, Integer, ProdutoDTO> {

}
