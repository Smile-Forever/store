package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.exception.InsertException;

public interface IAddressService {
	
	/**
	 *  用户增加新的地址
	 * @param address 新的地址
	 * @param username 用户名
	 * @return 添加之后得到的新地址
	 * @throws InsertException 增加异常
	 */
	Address create(Address address ,String username) throws InsertException;
	
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	List<Address> getListUid(Integer uid);
	
	/**
	 * 
	 * @param uid
	 * @param id
	 */
	void updateNonDefault(Integer uid , Integer id);
	
}
