package com.example.demo.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Base class for all DTO that references a Model. 
 * 
 * @author Virtus
 *
 */
@JsonInclude(Include.NON_NULL)
public class ModelDTO {

}

