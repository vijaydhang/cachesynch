package com.cachesynch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cachesynch.entity.PersonEntity;

/**
 * Repository class to 
 * handle DB operation related to PERSON table
 * @author Vijay Dhang
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

	//Return list of rows which matches the status passed as an argument
	List<PersonEntity> findByStatus(String status);
	
	//Return list of rows which the status is not equal to the 
	//status passed as an argument
	List<PersonEntity> findByStatusNot(String status);
	
}
