package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.exception.ServiceException;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTestCase {

	@Autowired
	private ICartService cartService;

	@Test
	public void addToCart() {
		try {
			String username = "liucs";
			Cart cart = new Cart();
			cart.setUid(9);
			cart.setGid(9528L);
			cart.setCount(2);
			cart.setPrice(800L);
			cartService.addToCart(username, cart);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println("错误类型：" + e.getClass().getName());
			System.err.println("错误描述：" + e.getMessage());
		}
	}

	@Test
	public void test() {
		try {
			Integer id = 4;
			Integer uid = 9;
			cartService.addCount(id, uid);
			System.out.println("ok");

		} catch (ServiceException e) {
			System.err.println("错误类型：" + e.getClass().getName());
			System.err.println("错误描述：" + e.getMessage());
		}
	}
	
	@Test
	public void test6() {
		Integer[] ids = new Integer[] {1,3,4};
		List<CartVO> ll = cartService.getByIds(ids);
		for (CartVO cartVO : ll) {
			System.out.println(cartVO);
		}
	}
}
