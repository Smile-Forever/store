package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;

public interface IDistrictService {

	/**
	 * 根据父级代号查询子集列表
	 * 
	 * @param parent 父级代号
	 * @return 省市区列表
	 */
	List<District> getListByParent(String parent);

	/**
	 * 根据code代号查询子集
	 * 
	 * @param code
	 * @return
	 */
	District getByCode(String code);

}
