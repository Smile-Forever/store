package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.District;

public interface DistrictMapper {
	
	/**
	 * 根据 父级代号查询子集的省、市区的列表
	 * @param parent 父级代号
	 * @return 省、市、区的列表
	 */
	List<District> findByParent(String parent);
	
	/**
	 * 根据code查询子集的省、市区的详情
	 * @param code
	 * @return
	 */
	District findByCode(String code);
}
