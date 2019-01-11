package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {
	
	/**
	 * 根据商品分类，查询商品列表
	 * @param categoryId 商品分类的id
	 * @param offset 偏移量
	 * @param count 获取的数据的最大数量
	 * @return 商品列表
	 */
	List<Goods> getByCategory(Long categoryId ,Integer offset , Integer count);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Goods getById(Long id);
	
	
	/**
	 * 根据优先级获取商品数据的列表
	 * @param count 
	 * @return 优先级最高的几个商品的列表
	 */
	List<Goods> getByPriority(Integer count);
}
