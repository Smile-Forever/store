package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.UpdateException;
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
	
	
	void changePassword(
		    Integer uid, 
		    String oldPassword, 
		    String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException;
	/**
	 * 修改用户个人资料
	 * @param user
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(User user)
			throws UserNotFoundException, 
				UpdateException;
	/**
	 * 根据id获取用户数据
	 * @param id 用户 id
	 * @return
	 */
	User getById(Integer id);
	
}
