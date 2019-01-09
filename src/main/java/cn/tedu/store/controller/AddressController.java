package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.ResponseResult;
/**
 * 处理收货地址请求相关的控制器
 * @author Smile
 *
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{
	
	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/create")
	public ResponseResult<Void> handleCreate(Address address , HttpSession session){
		String username = session.getAttribute("username").toString();
		Integer uid = getUidFromSession(session);
		address.setUid(uid);
		addressService.create(address, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/list")
	public ResponseResult<List<Address>> handleAddress(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<Address> list = addressService.getListUid(uid);
		return new ResponseResult<List<Address>>(SUCCESS , list);
	}
}
