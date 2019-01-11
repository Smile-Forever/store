package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.GoodsCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTestCase {
	
	@Autowired
	private IGoodsCategoryService categoryService;
	
	@Test
	public void test() {
		
		Long parentId = 89l;
		List<GoodsCategory> list = categoryService.getByParentId(parentId);
		for (GoodsCategory category : list) {
			System.out.println(category);
		}
	}
}



