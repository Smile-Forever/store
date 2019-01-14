package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.exception.AccessDeniedException;
import cn.tedu.store.service.exception.CartNotFoundException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;
import cn.tedu.store.vo.CartVO;

/**
 * 购物车业务层实现类
 */
@Service
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public void addToCart(
		String username, Cart cart) 
			throws InsertException, UpdateException {
		// 获取当前操作时间
		Date now = new Date();
		// 根据参数cart中的uid和gid查询数据
		Integer uid = cart.getUid();
		Long goodsId = cart.getGid();
		Cart data = findByUidAndGid(uid, goodsId);
		// 判断查询结果是否为null
		if (data == null) {
			// 是：该用户尚未在购物车中添加该商品，则执行新增
			cart.setCreatedUser(username);
			cart.setCreatedTime(now);
			cart.setModifiedUser(username);
			cart.setModifiedTime(now);
			addnew(cart);
		} else {
			// 否：该用户已经在购物车中添加该商品，则取出此前查询到的数据中的id和count
			Integer dataId = data.getId();
			Integer oldCount = data.getCount();
			// 根据上一步取出的count与参数cart中的count（此次用户提交的count），相加得到新的count
			Integer newCount = oldCount + cart.getCount();
			// 执行更新
			updateCount(dataId, newCount);
		}
	}

	/**
	 * 根据用户id和商品id查询购物车数据
	 * @param uid 用户id
	 * @param goodsId 商品id
	 * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
	 */
	private Cart findByUidAndGid(
		Integer uid, Long goodsId) {
		return cartMapper
			.findByUidAndGid(uid, goodsId);
	}

	/**
	 * 新增购物车数据
	 * @param cart 购物车数据
	 */
	private void addnew(Cart cart) {
		Integer rows = cartMapper.addnew(cart);
		if (rows != 1) {
			throw new InsertException(
				"创建购物车数据时发生未知错误！");
		}
	}
	
	/**
	 * 根据用户id查询该用户的购物车数据列表
	 * @param uid
	 * @return
	 */
	private List<CartVO> findByUid(Integer uid){
		return cartMapper.findByUid(uid);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	private Cart findById(Integer id) {
		return cartMapper.findById(id);
	}

	/**
	 * 更新购物车中商品的数量
	 * @param id 购物车数据的id
	 * @param count 新的数量
	 */
	private void updateCount(
		Integer id, Integer count) {
		Integer rows = cartMapper
				.updateCount(id, count);
		if (rows != 1) {
			throw new UpdateException(
				"修改购物车商品数量时发生未知错误！");
		}
	}
	
	private List<CartVO> findByIds(Integer[] id){
		return cartMapper.findByIds(id);
	}
	
	@Override
	public List<CartVO> getByIds(Integer[] ids) {
		return findByIds(ids);
	}
	

	@Override
	public List<CartVO> getindById(Integer uid) {
		return findByUid(uid);
	}

	@Override
	public void addCount(Integer id, Integer uid) throws AccessDeniedException, UpdateException, CartNotFoundException {
		Cart data = findById(id);
		if(data == null) {
			throw new CartNotFoundException("尝试访问的购物车数据不存在!");
		}
		
		if(!data.getUid().equals(uid)) {
			throw new AccessDeniedException("修改商品数量失败!访问数据权限验证不通过!");
		}
		
		Integer count = data.getCount();
		count++;
		updateCount(id, count);
	}

	
}






