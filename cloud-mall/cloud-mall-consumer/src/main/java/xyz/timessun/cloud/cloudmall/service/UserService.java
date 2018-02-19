package xyz.timessun.cloud.cloudmall.service;

import xyz.timessun.cloud.cloudmall.core.bo.UserBO;

public interface UserService {

	UserBO getUserBOById(String id);

	UserBO getUserBOByName(String name);

	UserBO create(String name);
	
	boolean delete(String name) ;

}