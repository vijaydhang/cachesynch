package com.cachesynch.bean;

/**
 * A common class to hold output of any operation
 * 
 * @author Vijay Dhang
 *
 */
public class Result {
	
	private String status;
	

	public Result(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + "]";
	}
	
}
