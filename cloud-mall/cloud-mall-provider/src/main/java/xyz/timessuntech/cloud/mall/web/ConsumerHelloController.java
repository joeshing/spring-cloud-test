package xyz.timessuntech.cloud.mall.web;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerHelloController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
	public String index() throws SocketException {
		Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
		while (enumeration.hasMoreElements()) {
		    NetworkInterface networkInterface = enumeration.nextElement();

		    if (networkInterface.isUp()) {
		        System.out.println(networkInterface.getDisplayName());
		        Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();

		        while (addressEnumeration.hasMoreElements()) {
		            System.out.println("\t" + addressEnumeration.nextElement());
		        }
		    }
		}
		
		return restTemplate.getForEntity("http://CLOUD-MALL-PROVIDER/hello", String.class).getBody();
	}

}
