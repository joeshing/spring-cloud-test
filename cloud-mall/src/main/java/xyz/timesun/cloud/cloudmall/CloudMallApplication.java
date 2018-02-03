package xyz.timesun.cloud.cloudmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMallApplication.class, args);
	}
}
