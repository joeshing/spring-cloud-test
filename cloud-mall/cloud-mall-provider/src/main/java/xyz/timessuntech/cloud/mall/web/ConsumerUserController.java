package xyz.timessuntech.cloud.cloudmall.web;

import java.net.SocketException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import xyz.timessuntech.cloud.cloudmall.core.bo.UserBO;

@RestController
@RequestMapping(value = "/ribbon-consumer")
public class ConsumerUserController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public UserBO create(String name) throws SocketException {
		UserBO user = new UserBO(name);
		return restTemplate.postForObject("http://CLOUD-MALL-PROVIDER/user/create", user, UserBO.class);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public UserBO get(String name) throws SocketException {
		return restTemplate.getForObject("http://CLOUD-MALL-PROVIDER/user/get?name={1}", UserBO.class, name);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public boolean delete(String name) throws SocketException {
		try {
			restTemplate.delete("http://CLOUD-MALL-PROVIDER/user/delete?name={1}", name);
			return true;
		} catch (Exception e) {
			logger.error("delete user " + name + " error .", e);
			return false;
		}
	}

}
