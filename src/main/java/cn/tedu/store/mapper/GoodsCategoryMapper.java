package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;

public interface GoodsCategoryMapper {
	
	/**
	 * 
	 * @param parentId
	 * @return
	 */
	List<GoodsCategory> findByParent(Long parentId);
}
