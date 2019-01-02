package com.client.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.dao.BasketMapper;
import com.client.dao.CollectMapper;
import com.client.dao.CommentMapper;
import com.client.dao.IntegralCommentMapper;
import com.client.dao.IntegralOrderMapper;
import com.client.dao.OrderUserMapper;
import com.client.dao.ShopMapper;
import com.client.dao.UserinfoMapper;
import com.client.entity.Basket;
import com.client.entity.Collect;
import com.client.entity.CollectExample;
import com.client.entity.Comment;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralOrder;
import com.client.entity.IntegralOrderExample;
import com.client.entity.OrderUser;
import com.client.entity.Shop;
import com.client.entity.Userinfo;
import com.client.service.DetailService;

@Service
public class DetailServiceImpl implements DetailService {
	@Autowired
	private BasketMapper basketMapper;
	@Autowired
	private OrderUserMapper orderUserMapper;
	@Autowired
	private IntegralOrderMapper integralOrderMapper;
	@Autowired
	private CollectMapper collectMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private IntegralCommentMapper integralCommentMapper;
	@Autowired
	private UserinfoMapper userinfoMapper;

	// 获取购物车
	@Override
	public Basket getBasketById(String string) {
		Integer id = Integer.parseInt(string);

		return basketMapper.selectByPrimaryKey(id);
	}

	// 设置状态为0
	@Override
	public void setOrderStatus(Integer orderUserId) {
		OrderUser orderUser = orderUserMapper.selectByPrimaryKey(orderUserId);
		orderUser.setIsExit("0");
		orderUserMapper.updateByPrimaryKeySelective(orderUser);
	}

	// 设置积分订单状态为0
	@Override
	public void setIntegralOrderStatus(Integer id) {
		IntegralOrder order = integralOrderMapper.selectByPrimaryKey(id);
		order.setIsExit("0");
		integralOrderMapper.updateByPrimaryKey(order);
	}

	// 将购物车状态置为0
	@Override
	public void setBasketStatus(Integer id) {
		Basket basket = basketMapper.selectByPrimaryKey(id);
		basket.setBasketStatus((short) 0);
		basketMapper.updateByPrimaryKey(basket);
	}

	// 删除一个收藏夹
	@Override
	public void deleteCollect(Integer id) {
		Collect collect = collectMapper.selectByPrimaryKey(id);
		collect.setIsExit("0");
		collectMapper.updateByPrimaryKey(collect);
	}

	// 删除所有收藏夹内容
	@Override
	public void deleteAllCollect(Integer userId) {
		CollectExample example = new CollectExample();
		CollectExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId).andIsExitEqualTo("1");
		List<Collect> collectList = collectMapper.selectByExample(example);
		for (int i = 0; i < collectList.size(); i++) {
			Collect collect = collectMapper.selectByPrimaryKey(collectList.get(i).getCollectId());
			collect.setIsExit("0");
			collectMapper.updateByPrimaryKey(collect);
		}
	}

	// 通过id获取积分商品信息
	@Override
	public Shop getShopGoodsById(Integer id) {
		return shopMapper.selectByPrimaryKey(id);
	}

	// 保存积分订单
	@Override
	public void saveIntegralOrder(IntegralOrder order) {
		integralOrderMapper.insert(order);
	}

	// 通过下单时间获取当前订单
	@Override
	public IntegralOrder getNowOrderByOrderTime(String date) {
		IntegralOrderExample example = new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria = example.createCriteria();
		criteria.andOrderTimeEqualTo(date);
		List<IntegralOrder> list = integralOrderMapper.selectByExample(example);
		return list.get(0);
	}

	// 保存评价
	@Override
	public void saveGoodsOrderComment(Integer userId, Integer orderUserId, Short countNum, String comment,
			String commentPhoto) {
		Comment commentBean = new Comment();
		commentBean.setUserId(userId);
		commentBean.setCommentContents(comment);
		commentBean.setCommentLevel(countNum);

		// 设置评价时间
		commentBean.setCommentTime(new Date());
		commentBean.setCommentPhoto(commentPhoto);
		commentBean.setOrderUserId(orderUserId);
		commentBean.setIsShow("1");

		// 保存评价
		commentMapper.insert(commentBean);
		// 设置订单状态为已完成

		OrderUser orderUser = orderUserMapper.selectByPrimaryKey(orderUserId);
		orderUser.setOrderStatus((short) 7);
		orderUserMapper.updateByPrimaryKey(orderUser);
	}

	// 保存积分评价
	@Override
	public void saveIntegralOrderComment(Integer userId, Integer integralOrderId, Short countNum, String comment,
			String commentPhoto) {
		IntegralComment integralComment = new IntegralComment();
		integralComment.setUserId(userId);
		integralComment.setCommentContents(comment);
		integralComment.setCommentLevel(countNum);
		integralComment.setCommentTime(new Date());
		integralComment.setCommentPhoto(commentPhoto);
		integralComment.setIntegralOrderId(integralOrderId);
		integralComment.setIsShow("1");
		integralCommentMapper.insert(integralComment);
		// 设置订单状态为已完成

		IntegralOrder integralOrder = integralOrderMapper.selectByPrimaryKey(integralOrderId);
		integralOrder.setOrderStatus((short) 7);
		integralOrderMapper.updateByPrimaryKey(integralOrder);
	}

	// 保存收藏
	@Override
	public void saveCollectGoods(Integer userId, int parseInt) {
		Collect collect = new Collect();
		collect.setUserId(userId);
		collect.setGoodsId(parseInt);
		collect.setCollectTime(new Date());
		collect.setIsExit("1");
		collectMapper.insert(collect);
	}

	// 获取收藏商品
	@Override
	public List<Collect> selectCollect(Integer userId, Integer goodId) {
		CollectExample example = new CollectExample();
		CollectExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId).andGoodsIdEqualTo(goodId);
		return collectMapper.selectByExample(example);
	}

	// is_exit设置为0
	@Override
	public void setCollectIsExit(Integer userId, Integer goodId, String isExit) {
		CollectExample example = new CollectExample();
		CollectExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId).andGoodsIdEqualTo(goodId);
		List<Collect> collect = collectMapper.selectByExample(example);
		collect.get(0).setIsExit(isExit);
		collectMapper.updateByPrimaryKey(collect.get(0));
	}

	// 保存购物车
	@Override
	public void saveBasketNoSize(Basket basket) {
		basketMapper.insert(basket);
	}

	//更新用户信息
	@Override
	public void updateUserInfo(Userinfo user) {
		// TODO Auto-generated method stub
		userinfoMapper.updateByPrimaryKey(user);
	}

	//获取用户信息
	@Override
	public Userinfo getUserById(Integer userId) {
		Userinfo userinfo = userinfoMapper.selectByPrimaryKey(userId);
		return userinfo;
	}

}
