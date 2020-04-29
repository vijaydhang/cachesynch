package com.cachesynch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * This project is intended to implement Pub-Sub model using DB 
 * without third party tools, and it can used used to publish/consume messages
 * from multiple instances of the service without affecting the synchronization
 * 
 * Starting point of Spring boot application
 * 
 * @author Vijay_Dhang
 *
 */
@SpringBootApplication
public class CacheSynchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheSynchApplication.class, args);
	}

}
