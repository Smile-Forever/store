package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;
@Service
public class GoodsServiceImpl implements IGoodsService{
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	/**
	 * 根据商品分类，查询商品列表
	 * @param categoryId 商品分类的id
	 * @param offset 偏移量
	 * @param count 获取的数据的最大数量
	 * @return 商品列表
	 */
	private List<Goods> findByCategory(Long categoryId ,Integer offset,Integer count){
		return findByCategory(categoryId, offset, count);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private Goods findById(Long id){
		
		return goodsMapper.findById(id);
	}
	
	/**
	 * 根据优先级获取商品数据的列表
	 * @param count 
	 * @return 优先级最高的几个商品的列表
	 */
	private List<Goods> findByPriority(Integer count){
		return goodsMapper.findByPriority(count);
	}
	
	
	@Override
	public List<Goods> getByCategory(Long categoryId, Integer offset, Integer count) {
		return findByCategory(categoryId, offset, count);
	}
	
	@Override
	public Goods getById(Long id) {
		return findById(id);
	}
	
	@Override
	public List<Goods> getByPriority(Integer count) {
		return findByPriority(count);
	}
}
