package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;

/**
 * 处理用户数据的业务类
 * 
 * @author Smile
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 插入用户数据
	 * 
	 * @param user 用户数据
	 * @throws InsertException
	 */
	private void addnew(User user) {
		Integer rows = userMapper.addnew(user);
		if (rows != 1) {
			throw new InsertException("增加用户时出现未知错误!");
		}
	}
	
	/**
	 * 获取Md5版的密码
	 * @param srcPassword 原密码
	 * @param salt 盐值
	 * @return 
	 */
	private String getMd5Password(String srcPassword , String salt) {
		//【注意】一下加密规则自由设定
		// 盐值 拼接  原密码  拼接 盐值
		String str = salt + srcPassword + salt;
		
		//循环执行10次摘要运算
		for (int i = 0; i < 10; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		//返回摘要结果
		return str;
	}

	/**
	 * @param username 用户名
	 * @return  
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	
	@Override
	public User reg(User user) throws DuplicateKeyException, InsertException {
		// 根据尝试注册的用户名查询数据库
		User data = userMapper.findByUsername(user.getUsername());
		// 判断查询到的数据是否为null
		if (data != null) {
			// 否：非null 用户名已被占用，抛出异常，DuplicateKeyException
			throw new DuplicateKeyException("注册失败!尝试注册的用户名(" + user.getUsername() + ")被占用!");
		}
		
		// 是：用户名不存在，允许注册，则处理密码加密
		//补充非用户提交的数据
		user.setIsDelete(0);//是否已经删除 ：否
		//4项日志
		Date date = new Date();
		user.setCreatedUser(user.getUsername());
		user.setCreatedTime(date);
		user.setModifiedUser(user.getUsername());
		user.setModifiedTime(date);
		//加密-1：获取随机的UUID座位盐值
		String salt = UUID.randomUUID().toString().toUpperCase();
		//加密-2：获取用户提交的原始密码
		String srcPassword = user.getPassword();
		//加密-3：
		String md5Password = getMd5Password(srcPassword, salt);
		//加密-4：
		user.setPassword(md5Password);
		//加密-5：将盐值封装回对象
		user.setSalt(salt);
		//执行注册 返回注册对象
		addnew(user);
		return user;
	}

}
