package com.example.demo.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.core.LoginUserDTO;


/**
 * The 'BaseService' class provides the common API for all services.
 * <p>
 * All services MUST extend this class.
 *
 * @author Virtus
 */
public abstract class BaseService {

    /**
     * Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Gets the current user.
     * 
     * @return Current user.
     */
    protected LoginUserDTO getCurrentUser() {
        return (LoginUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    /**
     * Gets the current user login.
     * 
     * @return Login.
     */
    protected String getCurrentLogin() {
    	return getCurrentUser().getLogin();
    }
    
    /**
     * Gets the current user ID.
     * 
     * @return ID.
     */
    protected Integer getCurrentUserId() {
    	return getCurrentUser().getId();
    }
}
