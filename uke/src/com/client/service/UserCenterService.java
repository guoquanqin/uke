package com.client.service;

import java.util.List;

import com.client.entity.Basket;
import com.client.entity.Collect;
import com.client.entity.Comment;
import com.client.entity.FeedbackComment;
import com.client.entity.Goods;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralFeedbackComment;
import com.client.entity.IntegralOrder;
import com.client.entity.OrderGoods;
import com.client.entity.OrderUser;
import com.client.entity.Orders;
import com.client.entity.Recommend;
import com.client.entity.Shop;
import com.client.entity.Signin;
import com.client.entity.Userinfo;

public interface UserCenterService {

	//获取签到日期信息
	Signin getSignInByUserId(Integer userId);

	//保存签到数据
	void saveSignIn(Signin signin);

	//保存用户
	void saveUserInfo(Userinfo userInfo);

	//获取用户订单表
	List<OrderUser> getOrderUserByUserId(Integer userId);

	//获取订单表
	List<Orders> getOrderByOrderUserId(Integer orderUserId);

	//通过订单表获取商品订单表
	OrderGoods getOrderGoodsByOrderId(Integer orderGoodsId);

	//获取收藏内容
	List<Collect> getCollect(Integer userId);

	//获取新品推荐内容
	List<Recommend> getRecomment();

	//获取待支付内容
	List<OrderUser> getOrderUserByOrderIdAndStatus(Integer orderGoodsId, int i);

	//根据订单号查询
	List<OrderUser> getOrderUserByUserIdAndOrderId(Integer userId, Integer orderId);

	//获取所有orderGoods
	List<OrderGoods> getAllOrderGoods();

	//通过OrderUserId获取到订单
	List<Orders> getOrderByOrderGoodsId(Integer goodsId);

	//获取购物车信息
	List<Basket> getBasketList(Integer userId);

	//获取comment表
	List<Comment> getCommentByOrderUserId(Integer orderUserId);

	//获取评价反馈表
	List<FeedbackComment> getFeedbackCommentByUserId(Integer userId);

	//获取积分商城的商品
	List<Shop> getAllIntegral(String c);

	//获取已完成的积分订单
	List<IntegralOrder> getIntegralOrderByStatus(short s,Integer userId);

	//获取积分商品信息
	Shop getShopById(Integer shopId);

	//获取全部积分订单
	List<IntegralOrder> getAllIntegralOrderByUserId(Integer userId);

	//根据id找订单
	List<IntegralOrder> getIntegralOrderById(Integer orderId);

	//通过商品id获取积分商品信息
	List<Shop> getShopByGoodsId(Integer goodsId);
	
	//获取查询出来的积分订单
	List<IntegralOrder> getIntegralOrderByShopId(Integer shopId,Integer userId);

	//保存地址
	void updateAddress(Userinfo user);

	//保存信息
	void updateInfo(Userinfo user);

	//保存密码
	void updatePass(String newPass, Integer userId);

	//保存手机号
	void updatePhone(Integer userId, String phone);

	//保存图片信息
	void savePic(String picName, Integer userId);

	//获取status为已完成的integral表
	List<IntegralOrder> getIntegralOrderByUserIdAndStatus(Integer userId, int i);

	//获取积分评价表
	List<IntegralComment> getIntegralCommentById(Integer integralOrderId);

	//获取积分评价反馈表
	List<IntegralFeedbackComment> getIntegralFeedbackCommentByUserId(Integer userId);


	


}
