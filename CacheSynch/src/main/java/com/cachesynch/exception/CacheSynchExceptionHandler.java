package com.cachesynch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cachesynch.bean.Result;
/**
 * Custom exception handler class which will be called
 *  when any matching exception defined against each method 
 *  is thrown in the project and construct the response as per the need
 * 
 * @author Vijay Dhang
 *
 */
@ControllerAdvice
public class CacheSynchExceptionHandler {

	/**
	 * Handle generic exception which not caught in the project
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public final ResponseEntity<Result> unhandledException(Exception ex){
		Result result = new Result(ex.getMessage());
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
