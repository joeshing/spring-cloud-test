package xyz.timessuntech.cloud.mall;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import xyz.timessuntech.cloud.mall.dao.MallUserDao;
import xyz.timessuntech.cloud.mall.dao.entity.MallUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudMallApplicationTests {
	
	@Resource
	private MallUserDao mallUserDao;

	@Test
	public void test1() {
		System.out.println(mallUserDao.count());
	}

	@Test
	@Transactional
	public void test2() {
		MallUser user = new MallUser();
		user.setNumber(100);
		mallUserDao.save(user);
		System.out.println(mallUserDao.count());
	}

}
