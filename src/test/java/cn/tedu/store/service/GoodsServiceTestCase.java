package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Goods;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTestCase {
	
	@Autowired
	private IGoodsService goodsService;
	
	/*@Test
	public void test() {
		String id = "10000001";
		List<Goods> all = goodsService.getAll(id);
		for (Goods goods : all) {
			System.out.println(goods);
		}
	}*/
	
	@Test
	public void test1() {
		Long categoryId = 163l;
		Integer offset = 0;
		Integer count = 10;
		List<Goods> all = goodsService.getByCategory(categoryId, offset, count);
		for (Goods goods : all) {
			System.err.println(goods);
		}
	}
	
	@Test
	public void test2() {
		Long id = 10000001l;
		 Goods goods = goodsService.getById(id);
		
			System.err.println(goods);
		
	}
	
	@Test
	public void test3() {
		Integer count = 5;
		List<Goods> all = goodsService.getByPriority(count);
		for (Goods goods : all) {
			System.err.println(goods);
		}
	}
}
