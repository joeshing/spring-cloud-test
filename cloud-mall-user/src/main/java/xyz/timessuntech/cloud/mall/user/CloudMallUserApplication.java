package xyz.timessuntech.cloud.mall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudMallUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMallUserApplication.class, args);
	}
}
