package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Goods;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTestCase {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Test
	public void test() {
		Long categoryId = 163l;
		Integer offset = 0;
		Integer count = 10;
		List<Goods> findAll = goodsMapper.findByCategory(categoryId, offset, count);
		for (Goods goods : findAll) {
			System.out.println(goods);
		}
	}
	
	@Test
	public void test1() {
		Long id = 10000001l;
		 Goods goods = goodsMapper.findById(id);
		
			System.err.println(goods);
		
	}
	
	@Test
	public void test2() {
		
		Integer count = 5;
		List<Goods> findAll = goodsMapper.findByPriority(count);
		for (Goods goods : findAll) {
			System.out.println(goods);
		}
	}
}
