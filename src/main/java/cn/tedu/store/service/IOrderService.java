package cn.tedu.store.service;

import cn.tedu.store.entity.Order;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.vo.OrderVO;
/**
 * 订单与订单商品详情
 * @author Smile
 *
 */
public interface IOrderService {
	/**
	 * 创建订单
	 * @param uid
	 * @param addressId
	 * @param cartIds
	 * @param username
	 * @return
	 */
	Order createOrder(Integer uid , String username ,
			Integer addressId ,Integer[] cartIds ) throws InsertException;
	
	
	OrderVO getById(Integer id);
}
