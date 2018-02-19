package xyz.timessun.cloud.cloudmall.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import xyz.timessun.cloud.cloudmall.core.bo.UserBO;

@Component
public class UserServiceImpl implements UserService {

	private Map<String, UserBO> cache = new HashMap<>();
	private AtomicLong userNumber = new AtomicLong(10000);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xyz.timesun.cloud.cloudmall.service.UserService#getUserBOById(java.lang.
	 * String)
	 */
	@Override
	public UserBO getUserBOById(String id) {
		return cache.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xyz.timesun.cloud.cloudmall.service.UserService#create(java.lang.String)
	 */
	@Override
	public UserBO create(String name) {
		if (cache.containsKey(name)) {
			return getUserBOByName(name);
		}
		UserBO user = new UserBO(UUID.randomUUID().toString(), userNumber.incrementAndGet(), name, true);
		cache.put(user.getId(), user);
		cache.put(name, user);
		return user;
	}

	@Override
	public UserBO getUserBOByName(String name) {
		return cache.get(name);
	}

	@Override
	public boolean delete(String name) {
		if(cache.containsKey(name)==false) {
			throw new RuntimeException("undefine: name " + name);
		}
		UserBO user = cache.remove(name);
		cache.remove(user.getId());
		return true;
	}
}
