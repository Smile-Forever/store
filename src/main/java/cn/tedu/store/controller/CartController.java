package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.util.ResponseResult;
/**
 * 	购物车数据的控制器类
 * @author Smile
 *
 */
import cn.tedu.store.vo.CartVO;
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController{
	
	@Autowired 
	private ICartService cartService;
	
	@PostMapping("/add_to_cart")
	public ResponseResult<Void> addToCart(HttpSession session , Cart cart){
		String username =  session.getAttribute("username").toString();
		Integer uid = getUidFromSession(session);
		cart.setUid(uid);
		cartService.addToCart(username, cart);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/list")
	public ResponseResult<List<CartVO>> getById(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<CartVO> data = cartService.getindById(uid);
		return new ResponseResult<List<CartVO>>(SUCCESS , data);
	}
	
	@GetMapping("/add_count")
	public ResponseResult<Void> addCount(@RequestParam("id")Integer id , HttpSession session){
		Integer uid = getUidFromSession(session);
		cartService.addCount(id, uid);;
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/get_by_ids")
	public ResponseResult<List<CartVO>> getByIds(@RequestParam("cart_id")Integer[] ids){
		List<CartVO> data = cartService.getByIds(ids);
		return new ResponseResult<List<CartVO>>(SUCCESS , data);
	}
}
