package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	 * 修改密码
	 * @param id 用户id
	 * @param password 密码
	 * @param modified_user 修改人
	 * @param modified_time 修改时间
	 * @return
	 */
	Integer updatePassword(@Param("uid")Integer uid,
						   @Param("password")String password,
						   @Param("modifiedUser")String modifiedUser,
						   @Param("modifiedTime")Date modifiedTime);
	
	/**
	 *  修改头像
	 * @param uid 用户id
	 * @param avatar 头像
	 * @param modified_user 修改人
	 * @param modified_time 修改时间
	 * @return
	 */
	Integer  updateAvatar(@Param("uid")Integer uid,
			@Param("avatar")String avatar,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	
	/**
	 *  修改用户资料(不含用户名，密码和头像)
	 * @param user 用户资料
	 * @return  受影响的行数
	 */
	Integer updateInfo(User user);
	
	
	/**
	 * 根据用户名 查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUsername(String username);
	
	/**
	 * 
	 * @param id
	 * @return 
	 */
	User findById(Integer id);
	
	
			
}
