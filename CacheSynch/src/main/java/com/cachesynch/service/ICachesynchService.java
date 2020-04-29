package com.cachesynch.service;

import java.util.List;

import com.cachesynch.bean.PersonBean;
import com.cachesynch.bean.Result;

/**
 * 
 * @author Vijay Dhang
 *
 */
public interface ICachesynchService {
	
	Result publish(PersonBean bean);
	
	List<PersonBean> consume();

}
