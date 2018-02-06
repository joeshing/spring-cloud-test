package xyz.timesun.cloud.cloudmall.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
	public String index() {
		return restTemplate.getForEntity("http://CLOUD-MALL-PROVIDER/hello", String.class).getBody();
	}

}
