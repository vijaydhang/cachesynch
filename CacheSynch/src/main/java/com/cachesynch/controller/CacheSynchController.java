package com.cachesynch.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cachesynch.bean.CacheSynchConstants;
import com.cachesynch.bean.PersonBean;
import com.cachesynch.bean.Result;
import com.cachesynch.service.CacheSynchServiceImpl;

/**
 * Controller class, any request sent on URI /cache-synch will be handled here
 * and respective method will be invoked based on the type of operation.
 * @author Vijay Dhang
 *
 */
@RestController
@RequestMapping(value = "/cache-synch")
public class CacheSynchController {
	
	private static final Logger LOGGER = LogManager.getLogger(CacheSynchController.class); 
	
	@Autowired
	CacheSynchServiceImpl service;
	
	/**
	 * Parse the incoming json message and store it into DB
	 * @param bean
	 * @return - Success or Failure status message
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> publisher(@RequestBody PersonBean bean) {
		
		LOGGER.info(CacheSynchConstants.ENTER);
		
		//Invoke service class method to persist data
		Result result = service.publish(bean);
		
		LOGGER.info(CacheSynchConstants.EXIT);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * Fetch message from the DB and 
	 * return list of all key value pairs
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonBean>> consumer(){
		
		LOGGER.info(CacheSynchConstants.ENTER);
		
		List<PersonBean> beans = service.consume();
		
		LOGGER.info(CacheSynchConstants.EXIT);
		
		return new ResponseEntity<>(beans, HttpStatus.OK);
	}
	
}
