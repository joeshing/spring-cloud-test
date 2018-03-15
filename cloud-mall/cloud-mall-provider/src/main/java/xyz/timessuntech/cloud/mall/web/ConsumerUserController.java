package xyz.timessuntech.cloud.mall.web;

import java.net.SocketException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import xyz.timessuntech.cloud.cloudmall.core.bo.UserBO;
import xyz.timessuntech.cloud.mall.service.ConsumerUserService;

@RestController
@RequestMapping(value = "/ribbon-consumer")
public class ConsumerUserController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ConsumerUserService userService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public UserBO create(String name) {
		return userService.create(name);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public UserBO get(String name) {
		return userService.get(name);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public boolean delete(String name) {
		return userService.delete(name);
	}
}
