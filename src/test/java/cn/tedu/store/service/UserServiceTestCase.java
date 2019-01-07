package cn.tedu.store.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.ServiceException;
import cn.tedu.store.service.exception.UserNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {

	@Autowired
	private IUserService userService;

	@Test
	public void addnew() {
		try {
			User user = new User();
			user.setUsername("java");
			user.setPassword("666");
			user.setGender(1);
			user.setPhone("16666666666");
			user.setEmail("SpringMVC@tedu.cn");

			User result = userService.reg(user);
			System.out.println(result);
		} catch (ServiceException e) {
			System.out.println("错误类型：" + e.getClass().getName());
			System.out.println("错误信息：" + e.getMessage());
		}
	}

	@Test
	public void login() {
		try {
			String username = "JAVA";
			String password = "123";

			User user = userService.login(username, password);
			System.out.println(user);
		} catch (ServiceException e) {
			System.out.println("错误类型：" + e.getClass().getName());
			System.out.println("错误描述：" + e.getMessage());
		}
	}

	@Test
	public void changePassword() {
		try {
			Integer uid = 4;
			String oldPassword = "66";
			String newPassword = "666";
			userService.changePassword(uid, oldPassword, newPassword);
			System.out.println("OK");
		} catch (ServiceException e) {
			System.out.println("错误类型：" + e.getClass().getName());
			System.out.println("错误描述：" + e.getMessage());
		}
	}

	@Test
	public void updateInfo() {
		try {
			User user = new User();
			user.setId(3);
			user.setGender(0);
			user.setPhone("19966677889");
			user.setEmail("chengh@tedu.cn");
			userService.changeInfo(user);
			System.out.println("OK");
		} catch (ServiceException e) {
			System.out.println("错误类型：" + e.getClass().getName());
			System.out.println("错误描述：" + e.getMessage());
		}
	}
	
	@Test
	public void info() {
		Integer id = 3;
		User user = userService.getById(id);
		System.out.println(user);
	}
	
	@Test
	public void changeAvatar() {
		try {
			Integer uid = 4;
			String avatar = "upload/121.jpg";
			userService.changeAvatar(uid, avatar);
			System.out.println("OK");
		} catch (ServiceException e) {
			System.out.println("错误类型：" + e.getClass().getName());
			System.out.println("错误描述：" + e.getMessage());
		}
	}
}
