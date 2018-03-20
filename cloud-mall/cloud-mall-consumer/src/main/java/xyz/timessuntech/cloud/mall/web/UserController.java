package xyz.timessuntech.cloud.mall.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.timessuntech.cloud.mall.core.bo.UserBO;
import xyz.timessuntech.cloud.mall.core.service.UserService;
import xyz.timessuntech.cloud.mall.service.UserServiceImpl;

@RestController
@RequestMapping(value = "/user")
public class UserController implements UserService{

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserServiceImpl userService;

	@Override
	public UserBO create(@RequestBody UserBO user) {
		logger.info("create user name: " + user.getName());
		return userService.create(user.getName());
	}

	@Override
	public UserBO get(String name) {
		logger.info("get user name: " + name);
		return userService.get(name);
	}

	@Override
	public boolean delete(String name) {
		logger.info("delete user name: " + name);
		return userService.delete(name);
	}

}
