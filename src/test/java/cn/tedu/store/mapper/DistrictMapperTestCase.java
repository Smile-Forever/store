package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTestCase {
	@Autowired
	private DistrictMapper disyrictMapper;
	
	@Test
	public void findByParent() {
		String parent = "620000";
		List<District> ll = disyrictMapper.findByParent(parent);
		for (District dis : ll) {
			System.err.println(dis);
		}
	}
	
	@Test
	public void findByCode() {
		String code = "620500";
		District dd = disyrictMapper.findByCode(code);
		System.out.println(dd);
		}
	
}
