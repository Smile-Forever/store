package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.vo.OrderVO;

public interface OrderMapper {
	
	/**
	 * 插入订单数据
	 * @param order
	 * @return
	 */
	Integer insertOrder(Order order);
	/**
	 * 插入订单商品数据
	 * @param orderItem
	 * @return
	 */
	Integer insertOrderItem(OrderItem orderItem);
	
	/**
	 * 根据id查询订单详情
	 * @param id
	 * @return 匹配订单详情
	 */
	OrderVO findById(Integer id);
}
