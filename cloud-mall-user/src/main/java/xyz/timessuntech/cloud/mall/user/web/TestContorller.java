package xyz.timessuntech.cloud.mall.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.timessuntech.cloud.mall.user.service.ConsumerUserService;

@RefreshScope
@RestController
public class TestContorller {

	@Value("${form}")
	private String form;
	@Autowired
	private ConsumerUserService userService;
	
	@RequestMapping("/form")
	public String form() {
		return this.form;
	}
	
	@RequestMapping("/get")
	public String get(String name) {
		return userService.getUserByName(name);
	}
}
