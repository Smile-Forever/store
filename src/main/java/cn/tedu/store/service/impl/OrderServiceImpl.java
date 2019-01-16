package cn.tedu.store.service.impl;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.service.exception.AddressNotFoundException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.vo.CartVO;
import cn.tedu.store.vo.OrderVO;

/**
 * 
 * @author Smile
 *
 */
@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private ICartService cartService;
	
	
	private void insertOrder(Order order) {
		Integer rows = orderMapper.insertOrder(order);
		if(rows != 1) {
			throw new InsertException("插入订单数据时发生未知错误！");
		}
	}
	/**
	 * 插入订单商品数据
	 * @param orderItem
	 * @return
	 */
	private void insertOrderItem(OrderItem orderItem) {
		Integer rows = orderMapper.insertOrderItem(orderItem);
		if(rows != 1) {
			throw new InsertException("插入订单商品数据时发生未知错误！");
		}
	}
	/**
	 * 插入订单数据
	 * @param order
	 * @return
	 */
	@Override
	@Transactional
	public Order createOrder(Integer uid, String username, Integer addressId, Integer[] cartIds)
			throws InsertException {
		// 创建Date对象
		Date now = new Date();
	    // 声明pay变量
		Long pay = 0l;
	    // List<CartVO> cartService.getByIds(ids)
		List<CartVO> carts = cartService.getByIds(cartIds);
		// 创建List<OrderItem> orderItems
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
	    // 遍历集合，过程中，计算总价pay
		for (CartVO cartVO : carts) {
			pay += cartVO.getNewPrice() *cartVO.getCount();
			
			// -- 创建OrderItem
			OrderItem item = new OrderItem();
			// -- item属性：goods_5，OK
			item.setGoodsId(cartVO.getGid());
			item.setGoodsTitle(cartVO.getTitle());
			item.setGoodsImage(cartVO.getImage());
			item.setGoodsPrice(cartVO.getOldPrice());
			item.setGoodsCount(cartVO.getCount());
			// -- item属性：4个日志，OK
			item.setCreatedUser(username);
			item.setCreatedTime(now);
			item.setModifiedUser(username);
			item.setModifiedTime(now);
			
			orderItems.add(item);
			
		}
	    // 创建Order对象
		Order order = new Order();
	    // order属性：uid，OK
		order.setUid(uid);
	    // order属性：pay，OK
		order.setPay(pay);
	    // 通过addressService.getById()得到收货地址数据
		Address address = addressService.getById(addressId);
		if(address == null) {
			throw new AddressNotFoundException("创建订单失败，收货地址有误!刷新后重试!");
		}
	    // order属性：recv_4，OK
		order.setRecvName(address.getName());
		order.setRecvPhone(address.getPhone());
		order.setRecvDistrict(address.getDistrict());
		order.setRecvAddress(address.getAddress());
	    // order属性：order_time，OK
		order.setOrderTime(now);
	    // order属性：status，OK，值为0
		order.setStatus(0);
	    // order属性：4个日志，OK
		order.setCreatedUser(username);
		order.setCreatedTime(now);
		order.setModifiedUser(username);
		order.setModifiedTime(now);
	    // 插入订单数据并获取oid：insertOrder(order)
		insertOrder(order);
	    // 遍历orderItems
		for (OrderItem orderItem : orderItems) {
			// item属性：oid
			orderItem.setOid(order.getId());
			// 插入订单商品数据
			insertOrderItem(orderItem);
		}
	
		return order;
	}	
	
	private OrderVO findById(Integer id) {
		return orderMapper.findById(id);
	}
	
	@Override
	public OrderVO getById(Integer id) {
		return findById(id);
	}
}
