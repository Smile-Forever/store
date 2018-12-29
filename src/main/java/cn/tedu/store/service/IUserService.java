package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;

public interface IUserService {
	/**
	 * 用户注册
	 * @param user 用户的注册信息
	 * @return 成功注册的用户数据
	 * @throws DuplicateKeyException
	 * @throws InsertException
	 */
	User reg(User user) throws DuplicateKeyException,InsertException;
}
