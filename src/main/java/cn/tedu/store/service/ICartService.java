package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.exception.AccessDeniedException;
import cn.tedu.store.service.exception.CartNotFoundException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;
import cn.tedu.store.vo.CartVO;

/**
 * 购物车业务层接口
 */
public interface ICartService {

	/**
	 * 将商品添加到购物车
	 * @param username 当前操作的执行人
	 * @param cart 购物车数据
	 * @throws InsertException
	 * @throws UpdateException
	 */
	void addToCart(String username, Cart cart) 
			throws InsertException, UpdateException;
	
	/**
	 *  根据用户id获取购物车列表
	 * @param uid
	 * @return
	 */
	List<CartVO> getByUid(Integer uid);
	
	/**
	 * 增加购物车中商品的数量
	 * @param id
	 * @param uid
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 * @throws CartNotFoundException
	 */
	void addCount(Integer id , Integer uid) throws AccessDeniedException ,UpdateException ,
	CartNotFoundException;
	
	/**
	 * 
	 * @param ids
	 * @return
	 */
	List<CartVO> getByIds(Integer[] ids);
}
