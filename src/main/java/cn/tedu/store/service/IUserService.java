package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.UserNotFoundException;

public interface IUserService {
	/**
	 * 用户注册
	 * @param user 用户的注册信息
	 * @return 成功注册的用户数据
	 * @throws DuplicateKeyException
	 * @throws InsertException
	 */
	User reg(User user) throws DuplicateKeyException,InsertException;
	
	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登录的用户数据，包括用户的id等。。。
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username , String password) 
			throws UserNotFoundException ,PasswordNotMatchException;
}
