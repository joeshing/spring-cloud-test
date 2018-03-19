package xyz.timessuntech.cloud.cloudmall.service;

import xyz.timessuntech.cloud.cloudmall.core.bo.UserBO;

public interface UserService {

	UserBO getUserBOById(String id);

	UserBO getUserBOByName(String name);

	UserBO create(String name);
	
	boolean delete(String name) ;

}