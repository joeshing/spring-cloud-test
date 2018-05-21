package xyz.timessuntech.cloud.mall.service;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xyz.timessuntech.cloud.mall.core.bo.UserBO;
import xyz.timessuntech.cloud.mall.dao.MallUserDao;
import xyz.timessuntech.cloud.mall.dao.entity.MallUser;

@Component
public class UserServiceImpl implements InitializingBean {

	@Resource
	private MallUserDao mallUserDao;
	private AtomicLong userNumber = new AtomicLong(10000);

	@Override
	public void afterPropertiesSet() throws Exception {
		Long max = mallUserDao.queryMaxNumber();
		if (max!=null && max > 0) {
			userNumber = new AtomicLong(max);
		}
	}

	@Transactional
	public UserBO create(String email) {
		if (mallUserDao.findByEmail(email) != null) {
			return get(email);
		}
		MallUser user = new MallUser();
		user.setNumber(userNumber.incrementAndGet());
		user.setEmail(email);
		mallUserDao.save(user);
		return new UserBO(user.getId(), user.getNumber(), user.getEmail(), user.getOperation().isEnable());
	}

	public UserBO get(String email) {
		MallUser user = mallUserDao.findByEmail(email);
		if (user != null) {
			return new UserBO(user.getId(), user.getNumber(), user.getEmail(), user.getOperation().isEnable());
		} else {
			return null;
		}
	}

	@Transactional
	public boolean delete(String id) {
		try {
			mallUserDao.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
