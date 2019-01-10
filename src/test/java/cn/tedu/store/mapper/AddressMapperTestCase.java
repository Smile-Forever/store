package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {
	@Autowired
	private AddressMapper addressMapper;
	
	@Test
	public void addnew() {
		Address address = new Address();
		address.setUid(1);
		address.setName("tom");
		address.setProvince("110000");
		address.setCity("11111");
		address.setArea("22222");
		address.setDistrict("lyl");
		address.setZip("1111"); 
		address.setAddress("xxxx");
		address.setPhone("1253333555");
		address.setTel("010-44444");
		address.setTag("222");
		address.setIsDefault(0);
		address.setCreatedUser("tom");
		address.setCreatedTime(new Date());
		address.setModifiedUser("tom");
		address.setModifiedTime(new Date());
		Integer rows = addressMapper.addnew(address);
		System.out.println(rows);
	}
	
	@Test
	public void find() {
		Integer count = addressMapper.getCountByUid(2);
		System.out.println(count);
	}
	
	@Test
	public void test() {
		Integer uid = 4;
		List<Address> list = addressMapper.findByUid(uid);
		
		for (Address add : list) {
			System.out.println(add);
		}
	}
	
	@Test
	public void update() {
		Integer uid = 4;
		Integer u = addressMapper.updateNonDefault(uid);
		System.out.println(u);
	}
	
	@Test
	public void update2() {
		Integer id = 1;
		Integer u = addressMapper.updateDefault(id);
		System.out.println(u);
	}
	
	@Test
	public void test2() {
		Integer id = 5;
		Address info = addressMapper.findById(id);
		System.out.println(info);
	}
	
	@Test
	public void findModified() {
		Integer uid = 4;
		Address rows = addressMapper.findLastModified(uid);
		System.out.println(rows);
	}
	
	@Test
	public void delete() {
		Integer id = 6;
		Integer rows = addressMapper.deleteById(id);
		System.out.println(rows);
	}
	
}
