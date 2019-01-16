package cn.tedu.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemMapperTestCase {
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 id INT AUTO_INCREMENT COMMENT 'id',
    oid INT COMMENT '归属订单的id',
    goods_id BIGINT COMMENT '商品id',
    goods_image VARCHAR(500) COMMENT '商品图片',
    goods_title VARCHAR(100) COMMENT '商品标题',
    goods_count INT  COMMENT '商品数量',
    goods_price BIGINT COMMENT '商品价格',
	 */
	@Test
	public void test() {
		OrderItem oi = new OrderItem();
		oi.setOid(2);
		oi.setGoodsId(2l);
		oi.setGoodsImage("header");
		oi.setGoodsTitle("hai");
		oi.setGoodsCount(2);
		oi.setGoodsPrice(2000l);
		Integer rows = orderMapper.insertOrderItem(oi);
		System.out.println(rows);
	}
	
}
