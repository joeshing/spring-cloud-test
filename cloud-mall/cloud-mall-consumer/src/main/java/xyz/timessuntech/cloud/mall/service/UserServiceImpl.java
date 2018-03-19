package xyz.timessuntech.cloud.mall.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import xyz.timessuntech.cloud.mall.core.bo.UserBO;

@Component
public class UserServiceImpl{

	private Map<String, UserBO> cache = new HashMap<>();
	private AtomicLong userNumber = new AtomicLong(10000);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xyz.timessuntech.cloud.cloudmall.service.UserService#create(java.lang.String)
	 */
	
	public UserBO create(String name) {
		if (cache.containsKey(name)) {
			return get(name);
		}
		UserBO user = new UserBO(UUID.randomUUID().toString(), userNumber.incrementAndGet(), name, true);
		cache.put(user.getId(), user);
		cache.put(name, user);
		return user;
	}

	
	public UserBO get(String name) {
		return cache.get(name);
	}

	
	public boolean delete(String name) {
		if(cache.containsKey(name)==false) {
			throw new RuntimeException("undefine: name " + name);
		}
		UserBO user = cache.remove(name);
		cache.remove(user.getId());
		return true;
	}
}
