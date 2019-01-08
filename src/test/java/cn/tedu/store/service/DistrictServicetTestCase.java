package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServicetTestCase {

	
	@Autowired
	private IDistrictService districtService;
	
	@Test
	public void findByParent() {
//		District district = new District();
//		district.setParent("110100");
		String parent = "110100";
		List<District> ll = districtService.getListByParent(parent);
		for (District dis : ll) {
			System.out.println(dis);
		}
	}
	

	@Test
	public void findByCode() {
		String code = "620500";
		District dd = districtService.getByCode(code);
		System.out.println(dd);
		}
}
