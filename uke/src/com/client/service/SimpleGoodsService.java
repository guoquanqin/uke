package com.client.service;

import java.util.List;

import com.client.entity.Comment;
import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.OrderGoods;
import com.client.entity.OrderUser;
import com.client.entity.Orders;
import com.client.entity.Shop;
import com.client.entity.Userinfo;

public interface SimpleGoodsService {

	//通过id获取商品信息
	List<Goods> getGoodsById(int id);

	//获取商品类型
	Goodstype getGoodsType(Integer goodsType);

	//获取商品的评价表
	//List<Comment> getGoodsCommentByGoodId(int i);

	//获取高销量商品
	List<Goods> MaxSaleVolume();

	//获取用户信息
	Userinfo getUserInfoById(Integer userId);

	//获取积分商城的商品信息
	Shop getShopGoodsById(Integer goodsId);

	//获取orderGoods
	List<OrderGoods> getOrderGoodsByGoodsId(Integer goodsId);

	//获取Orders
	List<Orders> getOrdersByOrderGoodsId(Integer orderGoodsId);

	//获取评价表
	List<Comment> getCommentByOrderUserId(Integer orderUserId);

	//orderUser表
	OrderUser getOrderUserById(Integer orderUserId);
}
