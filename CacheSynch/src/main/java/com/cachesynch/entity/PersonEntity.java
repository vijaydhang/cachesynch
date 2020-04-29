package com.cachesynch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Entity class mapped to PERSON table in DB
 * @author Vijay Dhang
 *
 */

@Entity
@Table(name="PERSON")
public class PersonEntity {
	
	//The version annotation is used to enable Optimistic locking
	//which maintain synchronization between multiple service instances
	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String userName;
	
	private String role;
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PersonEntity [id=" + id + ", userName=" + userName + ", role=" + role + ", status=" + status + "]";
	}
	
}
