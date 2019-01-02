package com.management.service;

import java.util.List;

import com.client.entity.Goods;
import com.client.entity.IntegralOrder;
import com.client.entity.OrderUser;
import com.client.entity.Shop;

public interface OrderService {

	//设置订单状态
	void setOrderUserStatusByOrderUserId(Integer orderUserId, short status);

	//查询所有订单（不管删没删）
	List<OrderUser> getAllOrderUserForHistory();

	//设置积分订单状态
	void setOrderStatusByIntegralOrderId(Integer integralOrderId, short status);

	//获取订单
	Goods getGoodsById(Integer goodsId);

	//查询所有的积分订单（不管删没删）
	List<IntegralOrder> getAllIntegralOrderForHistory();

	//查询积分商品
	Shop getShopById(Integer shopId);
	
	//获订单的条数
	public int getOrderCount();

}
