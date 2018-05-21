package xyz.timessuntech.cloud.mall.user.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConsumerUserService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "helloFallback", commandKey = "getUserByName", groupKey= "UserService", threadPoolKey="getUserByNameThread")
	public String getUserByName(String name){
		Map<String, String> params = new HashMap<>();
		params.put("name", name);
		return restTemplate.getForEntity("http://CLOUD-MALL-PROVIDER/user/get?name={name}", String.class, params).getBody();
	}
	
	public String helloFallback(String name) {
		return "error";
	}
}
