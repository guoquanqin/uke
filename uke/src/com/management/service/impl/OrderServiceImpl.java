package com.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.dao.GoodsMapper;
import com.client.dao.IntegralOrderMapper;
import com.client.dao.OrderUserMapper;
import com.client.dao.ShopMapper;
import com.client.entity.Goods;
import com.client.entity.IntegralOrder;
import com.client.entity.IntegralOrderExample;
import com.client.entity.OrderUser;
import com.client.entity.OrderUserExample;
import com.client.entity.Shop;
import com.management.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderUserMapper orderUserMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private IntegralOrderMapper integralOrderMapper;
	
	//设置订单状态
	@Override
	public void setOrderUserStatusByOrderUserId(Integer orderUserId,short status) {
		OrderUser orderUser = orderUserMapper.selectByPrimaryKey(orderUserId);
		orderUser.setOrderStatus(status);
		orderUserMapper.updateByPrimaryKey(orderUser);
	}

	//查询所有订单（不管删没删）
	@Override
	public List<OrderUser> getAllOrderUserForHistory() {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderUserIdIsNotNull();
		example.setOrderByClause("order_time desc");
		return orderUserMapper.selectByExample(example);
	}

	//设置积分订单状态
	@Override
	public void setOrderStatusByIntegralOrderId(Integer integralOrderId, short status) {
		IntegralOrder integralOrder=integralOrderMapper.selectByPrimaryKey(integralOrderId);
		integralOrder.setOrderStatus(status);
		integralOrderMapper.updateByPrimaryKey(integralOrder);
	}

	@Override
	public Goods getGoodsById(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.selectByPrimaryKey(goodsId);
	}

	//查询所有的积分订单（不管删没删）
	@Override
	public List<IntegralOrder> getAllIntegralOrderForHistory() {
		IntegralOrderExample example=new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		criteria.andIntegralOrderIdIsNotNull();
		example.setOrderByClause("order_time desc");
		return integralOrderMapper.selectByExample(example);
	}

	@Override
	public Shop getShopById(Integer shopId) {
		// TODO Auto-generated method stub
		return shopMapper.selectByPrimaryKey(shopId);
	}
	
	//获取订单的条数
	public int getOrderCount() {
		IntegralOrderExample example1 =new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria1=example1.createCriteria();
		criteria1.andIntegralOrderIdIsNotNull();
		List<IntegralOrder> list1 = integralOrderMapper.selectByExample(example1);
		
		OrderUserExample example2 =new OrderUserExample();
		OrderUserExample.Criteria criteria2=example2.createCriteria();
		criteria2.andOrderUserIdIsNotNull();
		List<OrderUser> list2 = orderUserMapper.selectByExample(example2);
		return list1.size()+list2.size();
	}
}
