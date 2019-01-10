package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.exception.DeleteException;
import cn.tedu.store.service.exception.ServiceException;

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
	
	@Test
	public void findByUid() {
		Integer uid = 4;
		List<Address> listUid = addressService.getListUid(uid);
		for (Address address : listUid) {
			System.out.println(address);
		}
	}
	
	@Test
	public void test1() {
		try {
			Integer uid = 4;
			Integer id = 1;
			addressService.setDefault(uid, id);
			System.out.println("ok");
		} catch (ServiceException e) {
			System.out.println("错误类型：" + e.getClass().getName());
			System.out.println("错误信息：" + e.getMessage());
		}
	}
	
	@Test
	public void delete() {
		try {
			Integer uid = 4;
			Integer id = 8;
			addressService.delete(id, uid);
			System.out.println("ok");
		} catch (ServiceException e) {
			System.out.println("错误类型：" + e.getClass().getName());
			System.out.println("错误信息：" + e.getMessage());
		}
	}
}
