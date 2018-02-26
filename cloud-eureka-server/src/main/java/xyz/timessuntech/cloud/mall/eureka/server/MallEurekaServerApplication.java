package xyz.timessuntech.cloud.mall.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MallEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallEurekaServerApplication.class, args);
	}
}
