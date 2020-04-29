package com.cachesynch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.cachesynch.bean.PersonBean;

/**
 * Test class for producer consumer testing on single port
 * @author Vijay Dhang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheSynchApplicationTests {
	
	private String url = "http://localhost:8080/cache-synch";
	
	private PersonBean bean = new PersonBean();

	/**
	 * Pre-populate the bean object
	 */
	@Before
	public void populateBean() {
		
		bean.setUserName("TEST");
		bean.setRole("TestRole");
		
		
	}
	
	/**
	 * Call POST operation on service end-point to publish message
	 * and validate the HTTP response code and the response
	 */
	@Test
	public void producer() {
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<String> response = template.postForEntity(url, bean, String.class);
		
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertTrue(response.getBody().toLowerCase().contains("success"));
	}
	
	/**
	 * Call GET operation on service end-point to consumer previously published message
	 * and validate HTTP response code and the result should match 
	 * with message published in the previous test case
	 */
	@Test
	public void consumer() {
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertTrue(response.getBody().contains(bean.getUserName()));
	}
	

}
