package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.exception.AccessDeniedException;
import cn.tedu.store.service.exception.AddressNotFoundException;
import cn.tedu.store.service.exception.DeleteException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;

@Service
public class AddressServiceImpl implements IAddressService{
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private IDistrictService districtService;
	
	String provinceName = null;
	String cityName = null;
	String areaName = null;
	 /**  用户增加新的地址
	 * @param address 新的地址
	 */
	private void addnew(Address address) {
		Integer rows = addressMapper.addnew(address);
		if(rows != 1) {
			throw new InsertException("添加地址时出现未知错误!");
		}
	}
	
	
	private void updateNonDefault(Integer uid) {
		Integer rows= addressMapper.updateNonDefault(uid);
		if(rows < 1) {
			throw new UpdateException("修改默认地址时出现未知错误");
		}
	}
	
	private void updateDefault(Integer id) {
		Integer rows = addressMapper.updateDefault(id);
		if(rows < 1) {
			throw new UpdateException("修改默认地址时出现未知错误");
		}
	}
	
	/**
	 * 
	 * @param uid
	 * @return 
	 */
	private Integer getCountByUid(Integer uid) {
		return addressMapper.getCountByUid(uid);
	}
	
	
	private List<Address> findByUid(Integer uid){
		return addressMapper.findByUid(uid);
	}
	
	/**
	 * 根据用户id获取收货地址
	 * @param id 收货地址id
	 * @return 返回获取到的收货地址 ，如果没有匹配的数据返回null
	 * 
	 */
	private Address findById(Integer id) {
		
		return addressMapper.findById(id);
	}
	
	/**
	 * 最后修改收货地址的信息
	 * @param id
	 * @return 匹配的数据
	 */
	private Address findLastModified(Integer uid) {
		return addressMapper.findLastModified(uid);
	}
	
	/**
	 * 根据id删除收货地址
	 * @param id
	 * @return 受影响的行数
	 */
	private void deleteById(Integer id) {
		Integer rows = addressMapper.deleteById(id);
		if(rows != 1){
			throw new DeleteException("删除收货地址出现错误!");
		}
	}
	
	
	@Override
	@Transactional
	public void delete(Integer uid, Integer id) throws DeleteException {
	    // 根据id查询收货地址数据：findById(id)
	    Address data = findById(id);
	    // 检查数据是否为null
	    if (data == null) {
	        // 是：抛出AddressNotFoundException
	        throw new AddressNotFoundException("删除收货地址失败！尝试删除的数据不存在！");
	    }

	    // 检查数据归属是否有误
	    if (data.getUid() != uid) {
	        // 是：抛出AccessDeniedException
	        throw new AccessDeniedException("删除收货地址失败！访问数据权限验证不通过！");
	    }

	    // 执行删除
	    deleteById(id);

	    // 检查还有没有收货地址数据：getCountByUid(uid)
	    if (getCountByUid(uid) > 0) {
	        // 是：判断刚才判断的是否是默认收货地址
	        if (data.getIsDefault() == 1) {
	            // -- 是：获取最后修改的收货地址：findLastModified(uid)
	            Integer lastModifiedId
	                = findLastModified(uid).getId();
	            // -- 将最后修改的收货地址设置为默认收货地址
	            setDefault(uid, lastModifiedId);
	        }
	    }
	}
	
	@Override
	@Transactional
	public void setDefault(Integer uid, Integer id) {
		Address data = findById(id);
		
		if(data == null) {
			throw new AddressNotFoundException("设置默认收货地址失败!尝试访问的收货地址不存在");
		}
		if(data.getUid() != uid) {
			throw new AccessDeniedException("设置默认收货地址失败!访问数据权限不通过");
		}
		updateNonDefault(uid);
		updateDefault(id);
	}
	
	/**
	 * 根据省市区代号获取名称
	 * @param province 省的代号
	 * @param city 市的代号
	 * @param area 区的代号
	 * @return 省市区的名称
	 */
	private String getDistrict(String province , String city , String area) {
		District p = districtService.getByCode(province);
		District c = districtService.getByCode(city);
		District a = districtService.getByCode(area);
		if(p != null) {
			provinceName = p.getName();
		}
		if(c != null) {
			cityName = c.getName();
		}
		if(a != null) {
			areaName = a.getName();
		}
		return provinceName + cityName + areaName;
	}
	
	@Override
	public List<Address> getListUid(Integer uid) {
		return findByUid(uid);
	}
	
	@Override
	public Address create(Address address , String username)  throws InsertException{
		// 通过address.getUid()得到用户id，并以此查询该用户的收货地址数量
		Integer count =  getCountByUid(address.getUid());
	    // 判断数量是否为0
//		if(count == 0) {
//			// 是：当前用户首次创建地址，则该地址默认：address.setIsDefault(1);
//			address.setIsDefault(1);
//		}else {
//			// 否：当前用户非首次创建地址，则该地址非默认：address.setIsDefault(0);
//			address.setIsDefault(0);
//		}
		address.setIsDefault(count == 0 ? 1 : 0);
	    // 处理district  根据省市区的代号获取District
		String district = getDistrict(address.getProvince() , address.getCity() , address.getArea());
		address.setDistrict(district);
		// 封装日志
		address.setCreatedUser(username);
		address.setCreatedTime(new Date());
		address.setModifiedUser(username);
		address.setModifiedTime(new Date());
	    // 执行创建新地址
		addnew(address);
		System.out.println("44"+address);
		return address;
	}

	@Override
	public Address getById(Integer id) {
		return findById(id);
	}
}
