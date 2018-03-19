package xyz.timessuntech.cloud.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudMallProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMallProviderApplication.class, args);
	}
}
