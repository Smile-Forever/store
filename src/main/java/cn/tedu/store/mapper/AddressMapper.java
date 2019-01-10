package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.tedu.store.entity.Address;
@Mapper
public interface AddressMapper {
	
	/**
	 * 增加新的收货地址数据
	 * @param address
	 * @return 受影响的行数
	 */
	Integer addnew(Address address);
	/**
	 * 根据用户id获取该用户收货地址数据的数量
	 * @param uid 用户id
	 * @return 受影响的行数 
	 */
	Integer getCountByUid(Integer uid);
	
	/**
	 * 根据某用户 获取用户地址
	 * @param uid
	 * @return 用户填写的地址
	 */
	List<Address> findByUid(Integer uid);
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	Integer updateNonDefault(Integer uid);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Integer updateDefault(Integer id);
	
	/**
	 * 根据用户id获取收货地址
	 * @param id 收货地址id
	 * @return 返回获取到的收货地址 ，如果没有匹配的数据返回null
	 * 
	 */
	Address findById(Integer id);
	
	/**
	 * 最后修改收货地址的信息
	 * @param id
	 * @return 匹配的数据
	 */
	Address findLastModified(Integer uid);
	
	/**
	 * 根据id删除收货地址
	 * @param id
	 * @return 受影响的行数
	 */
	Integer deleteById(Integer id);
	
}
