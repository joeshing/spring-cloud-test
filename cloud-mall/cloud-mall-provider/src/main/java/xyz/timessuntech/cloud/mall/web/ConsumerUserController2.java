package xyz.timessuntech.cloud.mall.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.timessuntech.cloud.mall.core.bo.UserBO;
import xyz.timessuntech.cloud.mall.service.ConsumerUserServiceImpl;

@RestController
@RequestMapping(value = "/ribbon-consumer2")
public class ConsumerUserController2 {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ConsumerUserServiceImpl userService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public UserBO create(String name) {
		return userService.create(name);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public UserBO get(String name) {
		return userService.getUserBOByName(name);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public boolean delete(String name) {
		return userService.delete(name);
	}
}
