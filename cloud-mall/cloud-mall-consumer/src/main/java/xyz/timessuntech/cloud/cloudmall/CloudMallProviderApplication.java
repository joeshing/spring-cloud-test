package xyz.timessuntech.cloud.cloudmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudMallProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMallProviderApplication.class, args);
	}
}
