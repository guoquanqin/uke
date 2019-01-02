package com.client.service;

import java.util.List;

import com.client.entity.Basket;
import com.client.entity.Collect;
import com.client.entity.IntegralOrder;
import com.client.entity.Shop;
import com.client.entity.Userinfo;

public interface DetailService {

	//获取购物车
	Basket getBasketById(String string);

	//设置状态为0
	void setOrderStatus(Integer orderUserId);

	//设置积分订单状态为0
	void setIntegralOrderStatus(Integer id);

	//将购物车状态置为0
	void setBasketStatus(Integer id);

	//删除一个收藏夹
	void deleteCollect(Integer id);

	//删除所有收藏夹内容
	void deleteAllCollect(Integer userId);

	//通过id获取积分商品信息
	Shop getShopGoodsById(Integer id);

	//保存积分订单
	void saveIntegralOrder(IntegralOrder order);

	//通过下单时间获取当前订单
	IntegralOrder getNowOrderByOrderTime(String date);

	//保存评价
	void saveGoodsOrderComment(Integer userId,Integer orderUserId, Short countNum, String comment, String commentPhoto);

	//保存积分评价
	void saveIntegralOrderComment(Integer userId, Integer integralOrderId, Short countNum, String comment,
			String commentPhoto);

	//保存收藏
	void saveCollectGoods(Integer userId, int parseInt);

	//获取收藏商品
	List<Collect> selectCollect(Integer userId, Integer goodId);

	//is_exit设置为0
	void setCollectIsExit(Integer userId, Integer goodId, String isExit);

	//保存购物车
	void saveBasketNoSize(Basket basket);

	//更新用户信息
	void updateUserInfo(Userinfo user);

	//获取用户信息
	Userinfo getUserById(Integer userId);

}
