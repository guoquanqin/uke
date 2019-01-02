package com.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.dao.BasketMapper;
import com.client.dao.CollectMapper;
import com.client.dao.CommentMapper;
import com.client.dao.FeedbackCommentMapper;
import com.client.dao.IntegralCommentMapper;
import com.client.dao.IntegralFeedbackCommentMapper;
import com.client.dao.IntegralOrderMapper;
import com.client.dao.OrderGoodsMapper;
import com.client.dao.OrderUserMapper;
import com.client.dao.OrdersMapper;
import com.client.dao.RecommendMapper;
import com.client.dao.ShopMapper;
import com.client.dao.SigninMapper;
import com.client.dao.UserinfoMapper;
import com.client.entity.Basket;
import com.client.entity.BasketExample;
import com.client.entity.Collect;
import com.client.entity.CollectExample;
import com.client.entity.Comment;
import com.client.entity.CommentExample;
import com.client.entity.FeedbackComment;
import com.client.entity.FeedbackCommentExample;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralCommentExample;
import com.client.entity.IntegralFeedbackComment;
import com.client.entity.IntegralFeedbackCommentExample;
import com.client.entity.IntegralOrder;
import com.client.entity.IntegralOrderExample;
import com.client.entity.OrderGoods;
import com.client.entity.OrderGoodsExample;
import com.client.entity.OrderUser;
import com.client.entity.OrderUserExample;
import com.client.entity.Orders;
import com.client.entity.OrdersExample;
import com.client.entity.Recommend;
import com.client.entity.RecommendExample;
import com.client.entity.Shop;
import com.client.entity.ShopExample;
import com.client.entity.Signin;
import com.client.entity.SigninExample;
import com.client.entity.Userinfo;
import com.client.service.UserCenterService;
import com.md5Util.MD5Util;

@Service
public class UserCenterServiceImpl implements UserCenterService {
	@Autowired
	private SigninMapper signInMapper;
	@Autowired
	private UserinfoMapper UserinfoMapper;
	@Autowired
	private OrderUserMapper orderUserMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	@Autowired
	private CollectMapper collectMapper;
	@Autowired
	private RecommendMapper recommendMapper;
	@Autowired
	private BasketMapper basketMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private FeedbackCommentMapper feedbackCommentMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private IntegralOrderMapper integralOrderMapper;
	@Autowired
	private UserinfoMapper userInfoMapper;
	@Autowired
	private IntegralCommentMapper integralCommentMapper;
	@Autowired
	private IntegralFeedbackCommentMapper integralFeedbackCommentMapper;
	
	//获取签到日期信息
	@Override
	public Signin getSignInByUserId(Integer userId) {
		SigninExample signInExample=new SigninExample();
		SigninExample.Criteria criteria=signInExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<Signin> list = signInMapper.selectByExample(signInExample);
		return list.get(0);
	}

	//保存签到数据
	@Override
	public void saveSignIn(Signin signin) {
		signInMapper.updateByPrimaryKey(signin);
	}

	//保存用户
	@Override
	public void saveUserInfo(Userinfo userInfo) {
		UserinfoMapper.updateByPrimaryKey(userInfo);
	}

	//获取用户订单表
	@Override
	public List<OrderUser> getOrderUserByUserId(Integer userId) {
		OrderUserExample orderUserExample=new OrderUserExample();
		//降序排序
		orderUserExample.setOrderByClause("order_user_id desc");
		OrderUserExample.Criteria criteria=orderUserExample.createCriteria();
		criteria.andUserIdEqualTo(userId).andIsExitEqualTo("1");
		return orderUserMapper.selectByExample(orderUserExample);
	}

	//获取订单表
	@Override
	public List<Orders> getOrderByOrderUserId(Integer orderUserId) {
		OrdersExample orderExample=new OrdersExample();
		OrdersExample.Criteria criteria = orderExample.createCriteria();
		criteria.andOrderUserIdEqualTo(orderUserId);
		return ordersMapper.selectByExample(orderExample);
	}

	//通过订单表获取商品订单表
	@Override
	public OrderGoods getOrderGoodsByOrderId(Integer orderGoodsId) {
		
		return orderGoodsMapper.selectByPrimaryKey(orderGoodsId);
	}

	//获取收藏内容
	@Override
	public List<Collect> getCollect(Integer userId) {
		CollectExample collectExample=new CollectExample();
		CollectExample.Criteria criteria=collectExample.createCriteria();
		criteria.andUserIdEqualTo(userId).andIsExitEqualTo("1");
		return collectMapper.selectByExample(collectExample);
	}

	//获取新品推荐内容
	@Override
	public List<Recommend> getRecomment() {
		RecommendExample recommendExample=new RecommendExample();
		RecommendExample.Criteria criteria=recommendExample.createCriteria();
	    criteria.andIsSaleEqualTo("1");
	    return recommendMapper.selectByExample(recommendExample);
	}

	//获取待支付内容
	@Override
	public List<OrderUser> getOrderUserByOrderIdAndStatus(Integer orderGoodsId, int i) {
		OrderUserExample orderUserExample=new OrderUserExample();
		//降序排序
		orderUserExample.setOrderByClause("order_user_id desc");
		OrderUserExample.Criteria criteria=orderUserExample.createCriteria();
		criteria.andUserIdEqualTo(orderGoodsId).andOrderStatusEqualTo((short)i).andIsExitEqualTo("1");
		return orderUserMapper.selectByExample(orderUserExample);
	}

	//根据订单号查询
	@Override
	public List<OrderUser> getOrderUserByUserIdAndOrderId(Integer userId, Integer orderId) {
		OrderUserExample orderUserExample=new OrderUserExample();
		//降序排序
		orderUserExample.setOrderByClause("order_user_id desc");
		//去重
		orderUserExample.setDistinct(false);
		OrderUserExample.Criteria criteria=orderUserExample.createCriteria();
		criteria.andUserIdEqualTo(userId).andOrderUserIdEqualTo(orderId).andIsExitEqualTo("1");
		return orderUserMapper.selectByExample(orderUserExample);
	}

	//获取所有orderGoods
	@Override
	public List<OrderGoods> getAllOrderGoods() {
		OrderGoodsExample orderGoodsExample=new OrderGoodsExample();
		OrderGoodsExample.Criteria criteria=orderGoodsExample.createCriteria();
		criteria.andOrderGoodsIdIsNotNull();
		return orderGoodsMapper.selectByExample(orderGoodsExample);
	}

	//通过OrderUserId获取到订单
	@Override
	public List<Orders> getOrderByOrderGoodsId(Integer goodsId) {
		OrdersExample orderExample=new OrdersExample();
		OrdersExample.Criteria criteria = orderExample.createCriteria();
		criteria.andOrderGoodsIdEqualTo(goodsId);
		return ordersMapper.selectByExample(orderExample);
	}

	//获取购物车信息
	@Override
	public List<Basket> getBasketList(Integer userId) {
		BasketExample basketExample=new BasketExample();
		BasketExample.Criteria criteria=basketExample.createCriteria();
		criteria.andUserIdEqualTo(userId).andBasketStatusEqualTo((short)1);
		return basketMapper.selectByExample(basketExample);
	}

	//获取comment表
	@Override
	public List<Comment> getCommentByOrderUserId(Integer orderUserId) {
		CommentExample commentExample=new CommentExample();
		CommentExample.Criteria criteria=commentExample.createCriteria();
		criteria.andOrderUserIdEqualTo(orderUserId);
		return commentMapper.selectByExample(commentExample);
	}

	//获取评价反馈表
	@Override
	public List<FeedbackComment> getFeedbackCommentByUserId(Integer userId) {
		FeedbackCommentExample example=new FeedbackCommentExample();
		FeedbackCommentExample.Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return feedbackCommentMapper.selectByExample(example);
	}

	//获取积分商城的商品
	@Override
	public List<Shop> getAllIntegral(String c) {
		ShopExample shopExample=new ShopExample();
		ShopExample.Criteria criteria=shopExample.createCriteria();
		criteria.andIsSaleEqualTo(c);
		return shopMapper.selectByExample(shopExample);
	}

	//获取已完成的积分订单
	@Override
	public List<IntegralOrder> getIntegralOrderByStatus(short s,Integer userId) {
		IntegralOrderExample example =new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("integral_order_id desc");
		criteria.andOrderStatusEqualTo(s).andUserIdEqualTo(userId);
		return integralOrderMapper.selectByExample(example);
	}

	//获取积分商品信息
	@Override
	public Shop getShopById(Integer shopId) {
		return shopMapper.selectByPrimaryKey(shopId);
	}

	//获取全部积分订单
	@Override
	public List<IntegralOrder> getAllIntegralOrderByUserId(Integer userId) {
		IntegralOrderExample example =new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("integral_order_id desc");
		criteria.andUserIdEqualTo(userId).andIsExitEqualTo("1");
		return integralOrderMapper.selectByExample(example);
	}

	//根据id找订单
	@Override
	public List<IntegralOrder> getIntegralOrderById(Integer orderId) {
		IntegralOrderExample example =new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		criteria.andIntegralOrderIdEqualTo(orderId).andIsExitEqualTo("1");
		return integralOrderMapper.selectByExample(example);
	}

	//通过商品id获取积分商品信息
	@Override
	public List<Shop> getShopByGoodsId(Integer goodsId) {
		ShopExample example =new ShopExample();
		ShopExample.Criteria criteria=example.createCriteria();
		example.setDistinct(false);
		criteria.andGoodsIdEqualTo(goodsId);
		return shopMapper.selectByExample(example);
	}

	//获取查询出来的积分订单
	@Override
	public List<IntegralOrder> getIntegralOrderByShopId(Integer shopId, Integer userId) {
		IntegralOrderExample example =new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		example.setDistinct(false);
		criteria.andShopIdEqualTo(shopId).andUserIdEqualTo(userId);
		return integralOrderMapper.selectByExample(example);
	}

	//保存地址
	@Override
	public void updateAddress(Userinfo user) {
		Userinfo userinfo = userInfoMapper.selectByPrimaryKey(user.getUserId());
		userinfo.setUserAddress(user.getUserAddress());
		userinfo.setUserAddress2(user.getUserAddress2());
		userinfo.setUserAddress3(user.getUserAddress3());
		userInfoMapper.updateByPrimaryKey(userinfo);
	}

	//保存信息
	@Override
	public void updateInfo(Userinfo user) {
		Userinfo userinfo = userInfoMapper.selectByPrimaryKey(user.getUserId());
		userinfo.setUserName(user.getUserName());
		userinfo.setUserEmail(user.getUserEmail());
		userinfo.setUserSex(user.getUserSex());
		userInfoMapper.updateByPrimaryKey(userinfo);
		
	}

	//保存密码
	@Override
	public void updatePass(String newPass,Integer userId) {
		//加密比较
		MD5Util md5=new MD5Util();
		String md5Password=md5.string2MD5(newPass);
		Userinfo userinfo = userInfoMapper.selectByPrimaryKey(userId);
		userinfo.setUserPassword(md5Password);
		userInfoMapper.updateByPrimaryKey(userinfo);
	}

	//保存手机号
	@Override
	public void updatePhone(Integer userId, String phone) {
		Userinfo userinfo = userInfoMapper.selectByPrimaryKey(userId);
		userinfo.setUserPhone(phone);
		userInfoMapper.updateByPrimaryKey(userinfo);
	}

	//保存图片信息
	@Override
	public void savePic(String picName, Integer userId) {
		Userinfo userinfo = userInfoMapper.selectByPrimaryKey(userId);
		userinfo.setUserPhoto(picName);
		userInfoMapper.updateByPrimaryKey(userinfo);		
	}

	//获取status为已完成的integral表
	@Override
	public List<IntegralOrder> getIntegralOrderByUserIdAndStatus(Integer userId, int i) {
		IntegralOrderExample example=new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("integral_order_id desc");
		criteria.andUserIdEqualTo(userId).andOrderStatusEqualTo((short)i);
		return integralOrderMapper.selectByExample(example);
	}

	//获取积分评价表
	@Override
	public List<IntegralComment> getIntegralCommentById(Integer integralOrderId) {
		IntegralCommentExample  example=new IntegralCommentExample();
		IntegralCommentExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("integral_order_id desc");
		criteria.andIntegralOrderIdEqualTo(integralOrderId);
		return integralCommentMapper.selectByExample(example);
	}

	@Override
	public List<IntegralFeedbackComment> getIntegralFeedbackCommentByUserId(Integer userId) {
		IntegralFeedbackCommentExample example=new IntegralFeedbackCommentExample();
		IntegralFeedbackCommentExample.Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return integralFeedbackCommentMapper.selectByExample(example);
	}


}
