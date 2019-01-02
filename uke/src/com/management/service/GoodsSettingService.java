package com.management.service;

import java.util.List;

import com.client.entity.Comment;
import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralOrder;
import com.client.entity.OrderUser;
import com.client.entity.Recommend;
import com.client.entity.Shop;

public interface GoodsSettingService {

	// 获取已支付和正在配送的商品订单详情
	List<OrderUser> getTodayOrder(String date);

	// 获取当天所有订单
	List<OrderUser> getAllOrderToday(String date);

	// 每天的订单数
	Integer getOrderCountByDate(String date);

	// 每天的交易数额
	Integer getOrderMoneyByDate(String date);

	// 获取成功订单数,状态为4到7为成功订单
	Integer getSuccessOrderByDate(String date);

	// 获取失败订单数，状态为1到3为失败订单
	Integer getFailOrderByDate(String date);

	// 获取所有订单
	List<OrderUser> getAllOrderUser();

	// 通过orderUserID找评论
	Comment getCommentByOrderUserId(Integer orderUserId);

	// 获取当天已支付订单
	Integer getTodayOrderPayment(String date);

	// 通过类型查商品
	List<Goods> findGoodsByGoodsType(Integer type);

	// 获取所有商品类型
	List<Goodstype> getAllGoodsType();

	// 保存设置
	void saveGoodsSetting(Goods goods);

	//获取所有goods
	List<Goods> getAllGoods();

	//通过类型获取商品
	List<Goods> getGoodsByGoodsType(int i, List<Integer> goodsIdList);

	//通过goodTypeId和商品名查找商品
	Goods findGoodsByGoodsTypeAndGoodsName(Integer id, String name);

	//保存推荐商品
	void saveRecommend(Recommend recommend);

	//查看是否存在推荐商品
	List<Recommend> findRecommendByGoodsId(Integer goodsId);

	//设置推荐商品is_sale为1
	void setRecommendIsSale(Integer recommendId);

	//设置推荐商品为下架
	void removeRecommend(Integer id);

	//设置积分商品为下架
	void removeIntegralGoods(Integer id);

	//查看积分商品是否已存在
	List<Shop> findShopGoodsByGoodsId(Integer goodsId);

	//保存积分商品
	void saveShopGoods(Shop shop);

	//设置积分商品is_sale为1
	void setShopGoodsIsSale(Integer shopId);

	//修改积分商品
	void alterIntegral(Integer id, Integer integralNum);

	//通过id查商品信息(包括下架)
	Goods getGoodsById(Integer goodsId);

	//设置商品的库存量和出售情况
	void setGoodsByGoodsId(Integer goodsId, Integer goodsInventory, String goodsIsSale);

	//根据订单状态找订单（当天）
	List<OrderUser> getOrderUserByOrderStatus(Short status);

	//根据订单状态获取积分订单
	List<IntegralOrder> getIntegralOrderByOrderStatus(Short status);

	//获取每天的订单
	List<OrderUser> getTodayOrderUser(String dateNext);

	//获取每天的积分订单
	List<IntegralOrder> getTodayIntegralOrder(String dateNext);

	//获取积分订单评论表
	IntegralComment getIntegralCommentByOrderUserId(Integer integralOrderId);

	// 获取当天已兑换的积分订单数量
	Integer getTodayIntegralOrderPayment(String date);

	//获取当天不是已收货和正在配送的订单
	List<OrderUser> getAllOtherToday(String date);

	//添加图片
	void addGoods(Goods goods);

	//查询商品名称是否重复
	List<Goods> getGoodsByName(String goodsName);
}
