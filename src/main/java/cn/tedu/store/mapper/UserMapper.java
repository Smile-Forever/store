package cn.tedu.store.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.tedu.store.entity.User;

/**
 * 
 * @author Smile
 *
 */
@Mapper
public interface UserMapper {
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer addnew(User user);
	/**
	 * 根据用户名 查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUsername(String username);
}