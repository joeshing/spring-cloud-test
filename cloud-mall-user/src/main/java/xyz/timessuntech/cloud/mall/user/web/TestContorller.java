package xyz.timessuntech.cloud.mall.user.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestContorller {

	@Value("${form}")
	private String form;
	
	@RequestMapping("/form")
	public String form() {
		return this.form;
	}
}
