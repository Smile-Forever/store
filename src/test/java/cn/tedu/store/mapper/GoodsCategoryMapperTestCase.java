package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.GoodsCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsCategoryMapperTestCase {
	@Autowired
	private GoodsCategoryMapper categoryMapper;
	
	@Test
	public void test() {
		Long parentId = 0l;
		List<GoodsCategory> list = categoryMapper.findByParent(parentId);
		
		for (GoodsCategory category : list) {
			System.err.println(category);
		}
	}
}
