package com.example.demo.core.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.core.BusinessException;
import com.example.demo.core.Model;
import com.example.demo.core.repository.CrudBaseRepository;


/**
 * The 'CrudService' class provides the common API for all CRUD services.
 * <p>
 * All CRUD services MUST extend this class.
 * <p>
 * Provides validations, insertion, update and deletion.
 *
 * @param <M> Model type.
 * @param <T> ID type.
 * @author Virtus
 */
public class CrudService extends SearchService {
	
	private CrudBaseRepository<Model<Serializable>, Serializable> repository;


    public CrudBaseRepository<Model<Serializable>, Serializable> getRepository() {
		return repository;
	}

	public void setRepository(CrudBaseRepository<Model<Serializable>, Serializable> repository) {
		this.repository = repository;
	}

	/**
     * Execution before the insert operation.
     *
     * @param model Model.
     */
    public Model<Serializable> preInsert(Model<Serializable> model) throws BusinessException {
        return model;
    }
    
    /**
     * Validates the insertion before perform.
     *
     * @param model Model.
     * @throws BusinessException If some rule is not acceptable.
     */
    public void validateInsert(Model<Serializable> model) throws BusinessException {

    }

    /**
     * Inserts the model.
     *
     * @param model Model.
     * @throws BusinessException If some rule is not acceptable.
     */
    @Transactional
    public Model<Serializable> insert(Model<Serializable> model) throws BusinessException {
        model = preInsert(model);

        validateInsert(model);

        return getRepository().save(model);
    }
    
    /**
     * Execution before the update operation.
     *
     * @param model Model.
     */
    public Model<Serializable> preUpdate(Model<Serializable> model) throws BusinessException {
        return model;
    }

    /**
     * Validates the update before perform.
     *
     * @param model Model.
     * @throws BusinessException If some rule is not acceptable.
     */
    public void validateUpdate(Model<Serializable> model) throws BusinessException {

    }

    /**
     * Updates the model.
     *
     * @param id    ID.
     * @param model Model.
     * @throws BusinessException If some rule is not acceptable.
     */
    @Transactional
    public void update(Serializable id, Model<Serializable> model) throws BusinessException {
        model = preUpdate(model);

        validateUpdate(model);

        getRepository().save(model);
    }

    /**
     * Execution before the delete operation.
     *
     * @param id ID.
     */
    public void preDelete(Serializable id) throws BusinessException {
    	
    }
    
    /**
     * Validates the deletion before perform.
     *
     * @param id ID.
     * @throws BusinessException If some rule is not acceptable.
     */
    public void validateDelete(Serializable id) throws BusinessException {

    }

    /**
     * Deletes the model.
     *
     * @param id ID.
     * @throws BusinessException If some rule is not acceptable.
     */
    @Transactional
    public void delete(Serializable id) throws BusinessException {
        validateDelete(id);

        preDelete(id);
        
        getRepository().deleteById(id);
    }
    
    /**
     * Deletes all IDs. 
     * 
     * @param ids
     * 		IDs.
     * @throws BusinessException 
     * 		If some rule is not acceptable.
     */
    public void delete(List<Serializable> ids) throws BusinessException {
    	for (Serializable id : ids) {
    		this.delete(id);
    	}
    }
}
