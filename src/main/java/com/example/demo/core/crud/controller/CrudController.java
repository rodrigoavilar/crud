package com.example.demo.core.crud.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.core.BusinessException;
import com.example.demo.core.Model;
import com.example.demo.core.ModelDTO;

public interface CrudController<M extends Model<T>, T extends Serializable, D extends ModelDTO> {

	@GetMapping
    public ResponseEntity<ModelDTO> search(HttpServletRequest request);
    
	@GetMapping("/{id}")
    public D getOne(HttpServletRequest request, @PathVariable T id);
    
	@PostMapping
	ResponseEntity<D> insert(HttpServletRequest request, @Valid @RequestBody D modelDTO) throws BusinessException;

	@PutMapping("/{id}")
	ResponseEntity<D> update(HttpServletRequest request, @Valid @PathVariable T id, @RequestBody D modelDTO) throws BusinessException;

	@PatchMapping("/{id}")
	public ResponseEntity<D> updatePartial(HttpServletRequest request, @PathVariable T id, @RequestBody D modelDTO) throws BusinessException;
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable T id) throws BusinessException;

}
