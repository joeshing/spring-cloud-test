package xyz.timessuntech.cloud.mall.core.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import xyz.timessuntech.cloud.mall.core.service.UserService;

@FeignClient(name = "cloud-mall-provider", fallback = UserServiceFallback.class, path = "user")
public interface UserServiceProxy extends UserService{

}
