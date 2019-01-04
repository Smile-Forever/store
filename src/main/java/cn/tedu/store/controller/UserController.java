package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user){
		userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/login.do")
	public ResponseResult<Void> handleLogin(@RequestParam("username") String username ,
			@RequestParam("password") String password , HttpSession session){
		User user = userService.login(username, password);
		session.setAttribute("uid", user.getId());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/password.do")
	public ResponseResult<Void> changePassword(@RequestParam("old_password")String oldPassword,
						@RequestParam("new_password")String newPassword , HttpSession session){
		//获取当前登录用户的id
		Integer uid = getUidFromSession(session);
		//执行修改 密码
		userService.changePassword(uid , oldPassword, newPassword);
		//返回
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("/info.do")
	public ResponseResult<User>  getInfo(HttpSession session){
		Integer id = getUidFromSession(session);
		User user = userService.getById(id);
		return new ResponseResult<User>(SUCCESS , user);
	}
	
	@PostMapping("/change_info.do")
	public ResponseResult<Void>  changeInfo(User user , HttpSession session){
		Integer id = getUidFromSession(session);
		user.setId(id);
		userService.changeInfo(user);
		return new ResponseResult<>(SUCCESS);
	}
}
