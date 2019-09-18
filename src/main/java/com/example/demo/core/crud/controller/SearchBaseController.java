package com.example.demo.core.crud.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.core.Model;
import com.example.demo.core.ModelDTO;
import com.example.demo.core.PageListDTO;
import com.example.demo.core.SearchFilterDTO;
import com.example.demo.core.service.SearchService;
//import com.example.demo.core.service.SearchService;
import com.example.demo.core.util.JSonUtil;

/**
 * The 'SearchBaseController' class provides the common API for controllers
 * to search models.
 * <p>
 * If a controller needs to search using a model, this controller MUST extend this class.
 * <p>
 * Provides search operations.
 *
 * If a controller needs to search using a model, this controller MUST extend this class.
 *
 * Provides search operations.
 *
 * @author Virtus
 *
 * @param <M> Model type.
 * @param <T> Model ID type.
 * @param <D> Model DTO type.
 * @author Virtus
 */
public abstract class SearchBaseController extends BaseController {
	
	private Class<Model<Serializable>> modelClass; 
	private Class<ModelDTO> dtoClass;

	/**
	 * Index order of the Model class in generics declaration. 
	 */
	protected static final Integer MODEL_INDEX_ORDER = 0;
	
	/**
	 * Index order of the DTO class in generics declaration. 
	 */
	protected static final Integer DTO_INDEX_ORDER = 2;
	
    /**
     * The model search service.
     *
     * @return Model search service.
     */
    protected abstract SearchService getService();

    /**
     * Searchs the model with the filter.
     *
     * @return DTO with list of model founded and filtered.
     */
    @GetMapping
    public ResponseEntity<ModelDTO> search(HttpServletRequest request) {
        SearchFilterDTO filter = makeSearchFilter(request);
        
        PageListDTO response = searchInService(request, filter);
        response.setItems(toListDTO(response.getItems()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Searchs the model with the filter.
     *
     * @param filterJSon Filter as JSon text.
     * @return DTO with list of model founded and filtered.
     */
    @GetMapping(params = {"filter"})
	public ResponseEntity<ModelDTO> search(HttpServletRequest request, @RequestParam("filter") String filterJSon) {
		SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
		filter = customSearchFilter(request, filter);
		
		PageListDTO response = searchInService(request, filter);
		response.setItems(toListDTO(response.getItems()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    /**
     * Gets one model by its specific ID as DTO.
     *
     * @param id ID of instance.
     * @return DTO of Model instance founded.
     */
    @GetMapping("/{id}")
    public ModelDTO getOne(HttpServletRequest request, @PathVariable Serializable id) {
        return toDTO(getOneModel(id));
    }
    
    /**
     * Creates a default search filter.
     * 
     * @param request
     * 		Http Request.
     * @return Search filter.
     */
    protected SearchFilterDTO makeSearchFilter(HttpServletRequest request) {
    	return new SearchFilterDTO();
    }
    
    /**
     * Customize the search filter.
     * 
     * @param request
     * 		Http Request.
     * @param filter
     * 		Search filter.
     * @return Custom search filter. 
     */
    protected SearchFilterDTO customSearchFilter(HttpServletRequest request, SearchFilterDTO filter) {
    	return filter;
    }
    
    /**
     * Call the service to search the items.
     * 
     * @param request
     * 		Http Request.
     * @param filter
     * 		Search Filter.
     * @return Pageable List.
     */
    protected PageListDTO searchInService(HttpServletRequest request, SearchFilterDTO filter) {
    	return getService().search(filter);
    }
    
    /**
     * Gets one model by its specific ID as DTO.
     *
     * @param id ID of instance.
     * @return Model instance founded.
     */
    protected Model<Serializable> getOneModel(Serializable id) {
    	return getService().getOne(id);
    }

    /**
     * Converts the Model DTO into Model.
     *
     * @param modelDTO Model DTO.
     * @return Model.
     */
    protected Model<Serializable> toModel(ModelDTO modelDTO) {
        return mapTo(modelDTO, getModelClass());
    }

    /**
     * Converts the Model into Model DTO.
     *
     * @param model Model.
     * @return Model DTO.
     */
    protected ModelDTO toDTO(Model<Serializable> model) {
        return mapTo(model, getDTOClass());
    }

    /**
     * Converts the list of models into list of DTOs.
     *
     * @param items Model items.
     * @return DTOs.
     */
    protected List<ModelDTO> toListDTO(List<?> items) {
        return toList(items, getDTOClass());
    }

    /**
     * Gets the Model class.
     * 
     * @return Model class.
     */
	protected Class<Model<Serializable>> getModelClass() {
    	return modelClass;
    }
    
    /**
     * Gets the DTO class.
     * 
     * @return DTO class.
     */
	protected Class<ModelDTO> getDTOClass() {
    	return dtoClass;
    }

	public void setModelClass(Class<Model<Serializable>> modelClass) {
		this.modelClass = modelClass;
	}

	public void setDtoClass(Class<ModelDTO> dtoClass) {
		this.dtoClass = dtoClass;
	}
	
	
    
}
