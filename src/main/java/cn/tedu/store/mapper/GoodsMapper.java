package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;
/**
 * 商品数据的持久层接口
 * @author Smile
 *
 */
public interface GoodsMapper {
	
	/**
	 * 根据商品分类，查询商品列表
	 * @param categoryId 商品分类的id
	 * @param offset 偏移量
	 * @param count 获取的数据的最大数量
	 * @return 商品列表
	 */
	List<Goods> findByCategory(@Param("categoryId")Long categoryId ,
								@Param("offset")Integer offset,
								@Param("count")Integer count);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Goods findById(Long id);
	
	/**
	 * 根据优先级获取商品数据的列表
	 * @param count 
	 * @return 优先级最高的几个商品的列表
	 */
	List<Goods> findByPriority(Integer count);
	
	
	
}
