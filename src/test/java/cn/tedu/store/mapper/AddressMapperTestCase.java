package cn.tedu.store.mapper;

import java.util.Date;

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
}
