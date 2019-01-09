package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService{
	
	@Autowired
	private DistrictMapper districtMapper;
	/**
	 * 根据父级代号查询子集列表
	 * @param parent 父级代号
	 * @return 
	 */
	private List<District> findByParent(String parent) {
	    return districtMapper.findByParent(parent);
	}
	
	
	
	/**
	 * 根据code代号查询子集
	 * @param code
	 * @return
	 */
	private District findByCode(String code) {
		return districtMapper.findByCode(code);
	}
	
	@Override
	public List<District> getListByParent(String parent) {
		return findByParent(parent);
	}

	@Override
	public District getByCode(String code) {
	    return  findByCode(code);
	}
	
}
