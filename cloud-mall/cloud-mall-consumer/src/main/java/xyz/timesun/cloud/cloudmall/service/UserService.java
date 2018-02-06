package xyz.timesun.cloud.cloudmall.service;

import xyz.timesun.cloud.cloudmall.core.bo.UserBO;

public interface UserService {

	UserBO getUserBOById(String id);

	UserBO getUserBOByName(String name);

	UserBO create(String name);
	
	boolean delete(String name) ;

}