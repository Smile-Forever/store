package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTestCase {

	@Autowired
	private CartMapper cartMapper;
	
	@Test
	public void test1() {
		Cart cart = new Cart();
		Date now = new Date();
		cart.setGid(1l);
		cart.setUid(1);
		cart.setPrice(2000l);
		cart.setCount(10);
		cart.setCreatedTime(now);
		cart.setModifiedTime(now);
		Integer rows = cartMapper.addnew(cart);
		System.out.println(rows);
	}
	
	@Test
	public void test2() {
		Integer uid = 1;
		Long goodsId = 1l;
		Cart data = cartMapper.findByUidAndGid(uid, goodsId);
		System.out.println(data);
	}
	
	@Test
	public void test3() {
		Integer id = 1;
		Integer count = 8;
		Integer rows = cartMapper.updateCount(id, count);
		System.out.println(rows);
	}
	
	@Test
	public void test4() {
		Integer uid = 4;
		List<CartVO> list = cartMapper.findByUid(uid);
		for (CartVO cartVO : list) {
			System.out.println(cartVO);
		}
	}
	
	@Test
	public void test5() {
		Integer id = 4;
		Cart cart = cartMapper.findById(id);
		System.out.println(cart);
	}
	
	@Test
	public void test6() {
		Integer[] id = new Integer[] {1,3,4};
		List<CartVO> ids = cartMapper.findByIds(id);
		for (CartVO cartVO : ids) {
			System.out.println(cartVO);
		}
	}
}
