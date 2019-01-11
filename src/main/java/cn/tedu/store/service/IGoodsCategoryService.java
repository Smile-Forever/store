package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;


public interface IGoodsCategoryService {
	
	List<GoodsCategory> getByParentId(Long parentId);
}
