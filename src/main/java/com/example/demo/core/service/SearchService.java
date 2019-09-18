package com.example.demo.core.service;

import java.io.Serializable;

import com.example.demo.core.Model;
import com.example.demo.core.PageListDTO;
import com.example.demo.core.SearchFilterDTO;
import com.example.demo.core.repository.SearchBaseRepository;


/**
 * The 'SearchService' provides the common API for all services that
 * do search operations with models.
 * 
 * All search model services MUST extend this class.
 * 
 * @author Virtus
 *
 * @param <M> Model type.
 * @param <T> ID type.
 */
public abstract class SearchService extends BaseService {

	/**
	 * Gets the search Repository.
	 * 
	 * @return Search Repository.
	 */
	protected abstract SearchBaseRepository<Model<Serializable>,Serializable> getRepository();
	
	/**
	 * Searches the model with the filter.
	 * 
	 * @param filter
	 * 		Filter.
	 * @return Instances founded.
	 */
	public PageListDTO search(SearchFilterDTO filter) {
		return getRepository().search(filter);
	}

	/**
	 * Gets one model instance by ID.
	 * 
	 * @param id
	 * 		ID.
	 * @return Instance founded.
	 */
	public Model<Serializable> getOne(Serializable id) {
		return getRepository().findById(id).get();
	}
}
