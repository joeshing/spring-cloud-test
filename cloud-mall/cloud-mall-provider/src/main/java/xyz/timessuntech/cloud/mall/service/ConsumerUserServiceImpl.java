package xyz.timessuntech.cloud.mall.service;

import java.net.SocketException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import xyz.timessuntech.cloud.mall.core.bo.UserBO;

@Service
public class ConsumerUserServiceImpl {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "helloFallback", commandKey = "MALL-PROVIDER")
	public UserBO create(String name){
		UserBO user = new UserBO(name);
		return restTemplate.postForObject("http://CLOUD-MALL-PROVIDER/user/create", user, UserBO.class);
	}

	@HystrixCommand(fallbackMethod = "helloFallback", commandKey = "MALL-PROVIDER")
	public UserBO getUserBOByName(String name) {
		return restTemplate.getForObject("http://CLOUD-MALL-PROVIDER/user/get?name={1}", UserBO.class, name);
	}

	@HystrixCommand(fallbackMethod = "helloFallback", commandKey = "MALL-PROVIDER")
	public boolean delete(String name){
		try {
			restTemplate.delete("http://CLOUD-MALL-PROVIDER/user/delete?name={1}", name);
			return true;
		} catch (Exception e) {
			logger.error("delete user " + name + " error .", e);
			return false;
		}
	}
	
	public UserBO helloFallback(String name) {
		return null;
	}
}
