package com.example.demo.core.crud.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.core.BusinessException;
import com.example.demo.core.ModelDTO;
//import com.example.demo.core.service.CrudService;
//import com.example.demo.core.util.NullAwareBeanUtils;


/**
 * The 'CrudBaseController' class provides the common API for CRUD controllers.
 * <p>
 * If a controller is a model CRUD, this controller MUST extend this class.
 * <p>
 * Provides insertion, update and deletion for the model.
 *
 * @param <M> Model type.
 * @param <T> Model ID type.
 * @param <D> Model DTO type.
 * @author Virtus
 */
public class CrudBaseController extends SearchBaseController {

    /**
     * Gets the model CRUD service.
     *
     * @return Model CRUD service.
     */
//    protected abstract CrudService<M, T> getService();

    /**
     * Inserts the model instance.
     *
     * @param modelDTO Model DTO.
     * @return Response.
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<ModelDTO> insert(HttpServletRequest request, @Valid @RequestBody ModelDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
        try {
        	return null; 
//            M model = getService().insert(toModel(modelDTO));
//            return created(toDTO(model));
        } catch (Exception e) {
            return notAcceptable(locale, e);
        }
    }

    /**
     * Updates the model instance.
     *
     * @param id       ID of instance.
     * @param modelDTO Model DTO.
     * @return Response.
     * @throws Exception
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelDTO> update(HttpServletRequest request, @Valid @PathVariable Serializable id, @RequestBody ModelDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
        try {
//            getService().update(id, toModel(modelDTO));
            return ok(modelDTO);
        } catch (Exception e) {
            return notAcceptable(locale, e);
        }
    }

    /**
     * Updates the model instance partially.
     *
     * @param id       ID of instance.
     * @param modelDTO Model.
     * @return Response.
     * @throws Exception
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ModelDTO> updatePartial(HttpServletRequest request, @PathVariable Serializable id, @RequestBody ModelDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
        try {
//            M model = toModel(modelDTO);
//            M dbModel = getService().getOne(id);
//            NullAwareBeanUtils.getInstance().copyProperties(dbModel, model);
//            getService().update(id, dbModel);
            return ok(modelDTO);
        } catch (Exception e) {
            return notAcceptable(locale, e);
        }
    }

    /**
     * Deletes the model instance.
     *
     * @param id ID of instance.
     * @return Response.
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Serializable id) throws BusinessException {
//        getService().delete(id);

        return success();
    }

    /**
     * Response CREATED (201) for the REST requests.
     *
     * @param element Element.
     * @return CREATED (201).
     */
    protected <E> ResponseEntity<E> created(E element) {
        return new ResponseEntity<>(element, HttpStatus.CREATED);
    }
}
