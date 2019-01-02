package com.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.dao.CommentMapper;
import com.client.dao.GoodsMapper;
import com.client.dao.GoodstypeMapper;
import com.client.dao.IntegralCommentMapper;
import com.client.dao.IntegralOrderMapper;
import com.client.dao.OrderUserMapper;
import com.client.dao.RecommendMapper;
import com.client.dao.ShopMapper;
import com.client.entity.Comment;
import com.client.entity.CommentExample;
import com.client.entity.Goods;
import com.client.entity.GoodsExample;
import com.client.entity.Goodstype;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralCommentExample;
import com.client.entity.IntegralOrder;
import com.client.entity.IntegralOrderExample;
import com.client.entity.OrderUser;
import com.client.entity.OrderUserExample;
import com.client.entity.Recommend;
import com.client.entity.RecommendExample;
import com.client.entity.Shop;
import com.client.entity.ShopExample;
import com.management.service.GoodsSettingService;
@Service
public class GoodsSettingServiceImpl implements GoodsSettingService{
	@Autowired
	private OrderUserMapper orderUserMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodstypeMapper goodsTypeMapper;
	@Autowired
	private RecommendMapper recommendMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private IntegralOrderMapper integralOrderMapper;
	@Autowired
	private IntegralCommentMapper integralCommentMapper;
	
	//获取当天已支付和正在配送的商品订单详情
	@Override
	public List<OrderUser> getTodayOrder(String date) {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderStatusBetween((short)2, (short)3);
		List<OrderUser> orderUser= orderUserMapper.selectByExample(example);
		
		List<OrderUser> list=new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<orderUser.size();i++) {
			
			String orderTime=formatter.format(orderUser.get(i).getOrderTime());
			System.out.println(orderTime);
			if(date.equals(orderTime)) {
				list.add(orderUser.get(i));
			}
		}
		return list;
	}

	//每天的订单数
	@Override
	public Integer getOrderCountByDate(String date) {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderUserIdIsNotNull();
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(example);
		
		int count=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getOrderTime());
			if(date.equals(orderTime)) {
				count++;
			}
		}
		return count;
	}

	//每天的交易数额
	@Override
	public Integer getOrderMoneyByDate(String date) {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderUserIdIsNotNull();
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(example);
		int money=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getOrderTime());
			if(date.equals(orderTime)) {
				money+=selectByExample.get(i).getOrderPrice();
			}
		}
		return money;
	}

	//获取成功订单数,状态为4到7为成功订单
	@Override
	public Integer getSuccessOrderByDate(String date) {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderStatusBetween((short)4,(short)7);
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(example);
		
		int count=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getOrderTime());
			if(date.equals(orderTime)) {
				count++;
			}
		}
		return count;
	}

	//获取失败订单数，状态为1到3和9为未成功订单
	@Override
	public Integer getFailOrderByDate(String date) {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderStatusBetween((short)1,(short)3);
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(example);
		
		int count=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getOrderTime());
			if(date.equals(orderTime)) {
				count++;
			}
		}
		return count;
	}

	//获取当天所有订单
	@Override
	public List<OrderUser> getAllOrderToday(String date) {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderUserIdIsNotNull();
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(example);
		
		List<OrderUser> list=new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getOrderTime());
			System.out.println("allToday:"+orderTime);
			if(date.equals(orderTime)) {
				list.add(selectByExample.get(i));
			}
		}
		return list;
	}
	
	//获取当天不是已收货和正在配送的订单
	@Override
	public List<OrderUser> getAllOtherToday(String date) {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderStatusNotEqualTo((short)2).andOrderStatusNotEqualTo((short)3);
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(example);
		
		List<OrderUser> list=new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getOrderTime());
			System.out.println("allToday:"+orderTime);
			if(date.equals(orderTime)) {
				list.add(selectByExample.get(i));
			}
		}
		return list;
	}

	//获取所有订单
	@Override
	public List<OrderUser> getAllOrderUser() {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("order_time desc");
		criteria.andIsExitEqualTo("1");
		return orderUserMapper.selectByExample(example);
	}

	//通过orderUserID找评论
	@Override
	public Comment getCommentByOrderUserId(Integer orderUserId) {	
		CommentExample commentExample=new CommentExample();
		CommentExample.Criteria criteria=commentExample.createCriteria();
		criteria.andOrderUserIdEqualTo(orderUserId);
		if(commentMapper.selectByExample(commentExample).size()==0) {
			return null;
		}
		return commentMapper.selectByExample(commentExample).get(0);
	}

	// 获取当天已支付订单
	@Override
	public Integer getTodayOrderPayment(String date) {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andIsExitEqualTo("1").andOrderStatusEqualTo((short)2);
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(example);
		
		int count=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getOrderTime());
			if(date.equals(orderTime)) {
				count++;
			}
		}
		return count;
	}
	
	// 获取当天已兑换的积分订单数量
	@Override
	public Integer getTodayIntegralOrderPayment(String date) {
		IntegralOrderExample example=new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		criteria.andIsExitEqualTo("1").andOrderStatusEqualTo((short)2);
		List<IntegralOrder> selectByExample = integralOrderMapper.selectByExample(example);
		
		int count=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today=formatter.format(new Date());
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=selectByExample.get(i).getOrderTime().substring(0, 10);
			if(date.equals(orderTime)) {
				count++;
			}
		}
		return count;
	}

	//通过类型查商品
	@Override
	public List<Goods> findGoodsByGoodsType(Integer type) {
		GoodsExample example=new GoodsExample();
		GoodsExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsTypeEqualTo(type);
		return goodsMapper.selectByExample(example);
	}

	//获取所有商品类型
	@Override
	public List<Goodstype> getAllGoodsType() {
		List<Goodstype> typeList=new ArrayList<>();
		for(int i=1;i<=14;i++) {
			Goodstype goodstype = goodsTypeMapper.selectByPrimaryKey(i);
			typeList.add(goodstype);
		}
		return typeList;
	}

	//保存设置
	@Override
	public void saveGoodsSetting(Goods goods) {
		goodsMapper.updateByPrimaryKey(goods);
	}

	//获取所有goods
	@Override
	public List<Goods> getAllGoods() {
		GoodsExample example=new GoodsExample();
		GoodsExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsNameIsNotNull();
		return goodsMapper.selectByExample(example);
	}

	//通过类型获取商品
	@Override
	public List<Goods> getGoodsByGoodsType(int i,List<Integer> goodsIdList) {
		GoodsExample example=new GoodsExample();
		GoodsExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsTypeEqualTo(i).andGoodsInventoryNotEqualTo(0).andGoodsIdNotIn(goodsIdList);
		return goodsMapper.selectByExample(example);
	}

	//通过goodTypeId和商品名查找商品
	@Override
	public Goods findGoodsByGoodsTypeAndGoodsName(Integer id, String name) {
		GoodsExample example=new GoodsExample();
		GoodsExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsTypeEqualTo(id).andGoodsNameEqualTo(name);
		return goodsMapper.selectByExample(example).get(0);
	}

	//保存推荐商品
	@Override
	public void saveRecommend(Recommend recommend) {
		recommendMapper.insert(recommend);
	}
	
	//查看是否存在推荐商品
	@Override
	public List<Recommend> findRecommendByGoodsId(Integer goodsId) {
		RecommendExample example=new RecommendExample();
		RecommendExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId);
		return recommendMapper.selectByExample(example);
	}

	//设置is_sale为1
	@Override
	public void setRecommendIsSale(Integer recommendId) {
		Recommend recommend = recommendMapper.selectByPrimaryKey(recommendId);
		recommend.setIsSale("1");
		recommendMapper.updateByPrimaryKey(recommend);
	}

	//设置推荐商品为下架
	@Override
	public void removeRecommend(Integer id) {
		RecommendExample example=new RecommendExample();
		RecommendExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsIdEqualTo(id);
		List<Recommend> list= recommendMapper.selectByExample(example);
		Recommend recommend=list.get(0);
		recommend.setIsSale("0");
		recommendMapper.updateByPrimaryKey(recommend);
		
	}

	//设置积分商品为下架
	@Override
	public void removeIntegralGoods(Integer id) {
		ShopExample example=new ShopExample();
		ShopExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsIdEqualTo(id);
		List<Shop> list= shopMapper.selectByExample(example);
		Shop shop=list.get(0);
		shop.setIsSale("0");
		shopMapper.updateByPrimaryKey(shop);
		
	}

	//查看积分商品是否已存在
	@Override
	public List<Shop> findShopGoodsByGoodsId(Integer goodsId) {
		ShopExample example=new ShopExample();
		ShopExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId);
		return shopMapper.selectByExample(example);
	}

	//保存积分商品
	@Override
	public void saveShopGoods(Shop shop) {
		shopMapper.insert(shop);
	}

	//设置积分商品is_sale为1
	@Override
	public void setShopGoodsIsSale(Integer shopId) {
		Shop shop = shopMapper.selectByPrimaryKey(shopId);
		shop.setIsSale("1");
		shopMapper.updateByPrimaryKey(shop);
		
	}

	//修改积分商品
	@Override
	public void alterIntegral(Integer id, Integer integralNum) {
		ShopExample example=new ShopExample();
		ShopExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsIdEqualTo(id);
		List<Shop> shopList=shopMapper.selectByExample(example);
		Shop shop=shopList.get(0);
		shop.setIntegral(integralNum);
		shopMapper.updateByPrimaryKey(shop);
	}

	//通过id查商品信息(包括下架)
	@Override
	public Goods getGoodsById(Integer goodsId) {
		
		return goodsMapper.selectByPrimaryKey(goodsId);
	}

	//设置商品的库存量和出售情况
	@Override
	public void setGoodsByGoodsId(Integer goodsId, Integer goodsInventory, String goodsIsSale) {
		GoodsExample example=new GoodsExample();
		GoodsExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId);
		Goods goods = goodsMapper.selectByExample(example).get(0);
		goods.setGoodsInventory(goodsInventory);
		goods.setGoodsIsSale(goodsIsSale);
		goodsMapper.updateByPrimaryKey(goods);
	}

	//根据订单状态找订单（当天）
	@Override
	public List<OrderUser> getOrderUserByOrderStatus(Short status) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today=formatter.format(new Date());
		
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderStatusEqualTo(status);
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(example);
		
		List<OrderUser> orderUser=new ArrayList<>();
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getOrderTime());
			if(today.equals(orderTime)) {
				orderUser.add(selectByExample.get(i));
			}
		}
		return orderUser;
	}

	//根据订单状态获取积分订单
	@Override
	public List<IntegralOrder> getIntegralOrderByOrderStatus(Short status) {
		IntegralOrderExample example=new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		criteria.andOrderStatusEqualTo(status);
		List<IntegralOrder> integralOrder=integralOrderMapper.selectByExample(example);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<IntegralOrder> list=new ArrayList<>();
		String today=formatter.format(new Date());
		for(int i=0;i<integralOrder.size();i++) {
			String orderTime=integralOrder.get(i).getOrderTime().substring(0, 10);
			if(today.equals(orderTime)) {
				list.add(integralOrder.get(i));
			}
		}
		return list;
	}

	//获取每天的订单
	@Override
	public List<OrderUser> getTodayOrderUser(String dateNext) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		criteria.andOrderUserIdIsNotNull();
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(example);
		
		List<OrderUser> orderUser=new ArrayList<>();
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getOrderTime());
			if(dateNext.equals(orderTime)) {
				orderUser.add(selectByExample.get(i));
			}
		}
		return orderUser;
	}

	//获取每天的积分订单
	@Override
	public List<IntegralOrder> getTodayIntegralOrder(String dateNext) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		IntegralOrderExample example=new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		criteria.andIntegralOrderIdIsNotNull();
		List<IntegralOrder> integralOrder=integralOrderMapper.selectByExample(example);
		
		List<IntegralOrder> orderUser=new ArrayList<>();
		for(int i=0;i<integralOrder.size();i++) {
			String orderTime=integralOrder.get(i).getOrderTime().substring(0, 10);
			if(dateNext.equals(orderTime)) {
				orderUser.add(integralOrder.get(i));
			}
		}
		return orderUser;
	}

	//获取积分订单评论表
	@Override
	public IntegralComment getIntegralCommentByOrderUserId(Integer integralOrderId) {
		IntegralCommentExample example=new IntegralCommentExample();
		IntegralCommentExample.Criteria criteria=example.createCriteria();
		criteria.andIntegralOrderIdEqualTo(integralOrderId);
		if(integralCommentMapper.selectByExample(example).size()==0) {
			return null;
		}else
		return integralCommentMapper.selectByExample(example).get(0);
	}

	//添加图片
	@Override
	public void addGoods(Goods goods) {
		goodsMapper.insert(goods);
		
	}

	//查询商品名称是否重复
	@Override
	public List<Goods> getGoodsByName(String goodsName) {
		GoodsExample example=new GoodsExample();
		GoodsExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsNameEqualTo(goodsName);
		return goodsMapper.selectByExample(example);
	}

	
	
}
