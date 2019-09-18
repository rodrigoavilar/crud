package com.example.demo.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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

}
