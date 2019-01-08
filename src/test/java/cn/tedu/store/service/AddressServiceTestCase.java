package cn.tedu.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {
	
	@Autowired
	private IAddressService addressService;
	
	@Test
	public void test() {
		String username="Admin";
		Address address = new Address();
		address.setUid(3);
		address.setName(username);
		address.setProvince("");
		Address result = addressService.create(address, username);
		System.err.println(result);
	}
	
	@Test
	public void create() {
		String username = "Admin";
		Address address = new Address();
		address.setUid(3);
		address.setName("小马同学");
		address.setProvince("440000");
		address.setCity("440300");
		address.setArea("440305");
	
		Address result = addressService.create(address, username);
		System.err.println("result=" + result);
	}
}
