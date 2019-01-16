package cn.tedu.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestCase {
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * id INT AUTO_INCREMENT COMMENT 'id',
    uid INT COMMENT '数据归属用户',
    recv_name VARCHAR(20) COMMENT '收货人姓名',
    recv_phone VARCHAR(20) COMMENT '收货人电话',
    recv_district VARCHAR(30) COMMENT '收货的省市区',
    recv_address VARCHAR(50) COMMENT '收货详细地址',
	 */
	@Test
	public void test() {
		Order o = new Order();
		o.setUid(2);
		o.setRecvName("Tom");
		o.setRecvPhone("11122255335");
		o.setRecvDistrict("shanghai");
		o.setRecvAddress("longyang");
		Integer rows = orderMapper.insertOrder(o);
		System.out.println(rows);
	}
	
}
