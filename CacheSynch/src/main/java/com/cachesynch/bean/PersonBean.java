package com.cachesynch.bean;

import com.cachesynch.entity.PersonEntity;

/**
 * This class is designed to transfer data 
 * between controller to service and vice versa.
 * 
 * @author Vijay Dhang
 *
 */
public class PersonBean {
	
	private Long id;
	
	private String userName;
	
	private String role;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return "PersonBean [id=" + id + ", userName=" + userName + ", role=" + role + "]";
	}

	public PersonEntity createEntity() {
		PersonEntity entity = new PersonEntity();
		entity.setRole(this.getRole());
		entity.setUserName(this.getUserName());
		
		return entity;
	}
	
	public static PersonBean valueOf(PersonEntity entity) {
		PersonBean bean = new PersonBean();
		bean.setRole(entity.getRole());
		bean.setUserName(entity.getUserName());
		
		return bean;
	}

}
