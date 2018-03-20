package xyz.timessuntech.cloud.mall.core.service;

import org.springframework.stereotype.Component;

import xyz.timessuntech.cloud.mall.core.bo.UserBO;

@Component
public class UserServiceFallback implements UserServiceProxy{

	@Override
	public UserBO get(String name) {
		// TODO Auto-generated method stub
		return new UserBO("Nil");
	}

	@Override
	public UserBO create(UserBO user) {
		// TODO Auto-generated method stub
		return new UserBO("Nil");
	}

	@Override
	public boolean delete(String name) {
		// TODO Auto-generated method stub
		return false;
	}

}
