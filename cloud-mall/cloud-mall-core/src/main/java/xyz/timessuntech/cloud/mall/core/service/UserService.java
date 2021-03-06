package xyz.timessuntech.cloud.mall.core.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.timessuntech.cloud.mall.core.bo.UserBO;

public interface UserService {

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	UserBO get(String name);

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	UserBO create(@RequestBody UserBO user);

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	boolean delete(String name);

}