package com.cachesynch.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cachesynch.bean.CacheSynchConstants;
import com.cachesynch.bean.PersonBean;
import com.cachesynch.bean.Result;
import com.cachesynch.entity.PersonEntity;
import com.cachesynch.repository.PersonRepository;

/**
 * Service class called from controller get the data validate and 
 * convert and persist data as per business needs and 
 * return the response back to controller
 * 
 * @author Vijay Dhang
 *
 */
@Service
public class CacheSynchServiceImpl implements ICachesynchService {

	private static final Logger LOGGER = LogManager.getLogger(CacheSynchServiceImpl.class);

	@Autowired
	PersonRepository personRepository;

	/**
	 * Receive message from source and publish onto destination
	 * in this case persisting data into DB
	 */
	@Override
	public Result publish(PersonBean bean) {

		LOGGER.info(CacheSynchConstants.ENTER);

		//convert bean to entity representation
		PersonEntity entity = bean.createEntity();
		
		//Update the status to received
		entity.setStatus(CacheSynchConstants.RECEIVED);
		
		//Save the data into DB
		personRepository.saveAndFlush(entity);

		LOGGER.info("Entity saved successfully!! [{}]", entity);

		LOGGER.info(CacheSynchConstants.EXIT);

		return new Result(CacheSynchConstants.SUCCESS);
	}

	
	/**
	 * Consume message from the source (in this case DB) and
	 * return it to caller
	 */
	@Override
	public List<PersonBean> consume() {

		LOGGER.info(CacheSynchConstants.ENTER);

		//Fetch all messages which are not in SENT status
		List<PersonEntity> entities = personRepository.findByStatusNot(CacheSynchConstants.SENT);

		//If no record found return empty list
		if (entities.isEmpty()) {
			return new ArrayList<>();
		}

		//If record(s) found convert Arraylist to thread-safe synchronized list
		List<PersonEntity> synchEntities = Collections.synchronizedList(entities);

		try {

			//Added for thread safety
			synchronized (synchEntities) {

				LOGGER.info("Found following list of values");
				synchEntities.stream().forEach(LOGGER::info);

				//Change the status of each record to SENT 
				//so that same record wont be fetched in the next call
				synchEntities.stream().forEach(e -> e.setStatus(CacheSynchConstants.SENT));
				
				//Persist modified records into DB
				personRepository.saveAll(entities);

				LOGGER.info(CacheSynchConstants.EXIT);

				//Convert the entities into Person Bean object by calling valueOf method
				//and return the list of PersonBean objects
				return synchEntities.stream().map(PersonBean::valueOf).collect(Collectors.toList());
			}
		} catch (OptimisticEntityLockException e) {
			//This exception will be thrown when a 
			//process tries to modify the record 
			//which is already being modified by another process
			LOGGER.error("Unhadled error occured", e);
			return new ArrayList<>();
		}
	}
}
