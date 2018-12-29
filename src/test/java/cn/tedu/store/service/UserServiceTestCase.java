package cn.tedu.store.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

import cn.tedu.store.service.exception.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	
	@Autowired
	 private IUserService userService;

	    @Test
	    public void addnew() {
	        try {
				Date now = new Date();
				User user = new User();
				user.setUsername("SpringMVC");
				user.setPassword("HELLO");
				user.setGender(1);
				user.setPhone("1999999999");
				user.setEmail("SpringMVC@tedu.cn");
				
				User result = userService.reg(user);
				System.out.println(result);
			} catch (ServiceException e) {
				System.out.println("错误类型：" + e.getClass().getName());
				System.out.println("错误信息：" + e.getMessage());
			} 
	    }
}
