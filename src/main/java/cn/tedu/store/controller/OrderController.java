package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Order;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{
	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping("/create")
	public ResponseResult<Order> createOrder(HttpSession session,
			@RequestParam("address")Integer addressId , @RequestParam("cart_id")Integer[] cartIds){
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		Order data = orderService.createOrder(uid, username, addressId, cartIds);
		return new ResponseResult<Order>(SUCCESS , data);
		
	}
}
