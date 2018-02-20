package xyz.timessuntech.cloud.cloudmall.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.timessuntech.cloud.cloudmall.core.bo.UserBO;
import xyz.timessuntech.cloud.cloudmall.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public UserBO create(@RequestBody UserBO user) {
		logger.info("create user name: " + user.getName());
		return userService.create(user.getName());
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public UserBO get(String name) {
		logger.info("get user name: " + name);
		return userService.getUserBOByName(name);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public boolean delete(String name) {
		logger.info("delete user name: " + name);
		return userService.delete(name);
	}

}
