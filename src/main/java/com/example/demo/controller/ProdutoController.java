package com.example.demo.controller;

import com.example.demo.core.crud.CRUD;
import com.example.demo.core.crud.controller.CrudController;
import com.example.demo.model.Produto;

@CRUD("produtos")
public interface ProdutoController extends CrudController<Produto, Integer, ProdutoDTO> {

}
