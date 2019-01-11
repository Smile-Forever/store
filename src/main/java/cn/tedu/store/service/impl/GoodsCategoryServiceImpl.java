package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;
import cn.tedu.store.service.IGoodsCategoryService;

@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService{
	@Autowired
	private GoodsCategoryMapper goodscategoryMapper;
	@Override
	public List<GoodsCategory> getByParentId(Long parentId) {
		
		return goodscategoryMapper.findByParent(parentId);
	}
	
	
	/**
	 * 
	 * @param parentId
	 * @return
	 */
	private List<GoodsCategory> findByParent(Long parentId){
		return goodscategoryMapper.findByParent(parentId);
	}

}
