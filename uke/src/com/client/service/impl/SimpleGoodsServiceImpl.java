package com.client.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.dao.CommentMapper;
import com.client.dao.GoodsMapper;
import com.client.dao.GoodstypeMapper;
import com.client.dao.OrderGoodsMapper;
import com.client.dao.OrderUserMapper;
import com.client.dao.OrdersMapper;
import com.client.dao.ShopMapper;
import com.client.dao.UserinfoMapper;
import com.client.entity.Comment;
import com.client.entity.CommentExample;
import com.client.entity.Goods;
import com.client.entity.GoodsExample;
import com.client.entity.Goodstype;
import com.client.entity.OrderGoods;
import com.client.entity.OrderGoodsExample;
import com.client.entity.OrderUser;
import com.client.entity.Orders;
import com.client.entity.OrdersExample;
import com.client.entity.Shop;
import com.client.entity.ShopExample;
import com.client.entity.Userinfo;
import com.client.service.SimpleGoodsService;

@Service
public class SimpleGoodsServiceImpl implements SimpleGoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodstypeMapper goodsTypeMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderUserMapper orderUserMapper;

	//通过id获取商品信息
	@Override
	public List<Goods> getGoodsById(int id) {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsIdEqualTo(id);
		return goodsMapper.selectByExample(goodsExample);
	}

	//获取商品类型
	@Override
	public Goodstype getGoodsType(Integer goodsType) {
		return goodsTypeMapper.selectByPrimaryKey(goodsType);
	}

	//获取高销量商品
	@Override
	public List<Goods> MaxSaleVolume() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsIsSaleEqualTo("1");
		//获取全部的商品
		List<Goods> list=goodsMapper.selectByExample(goodsExample);
		//商品数量
		long l = goodsMapper.countByExample(goodsExample);
		//定义一个list用来存储数据
		List<Goods> maxList=new ArrayList<>();
		
		Collections.sort(list,new Comparator<Goods>(){
            public int compare(Goods arg0, Goods arg1) {
                return arg0.getGoodsSalesVolume().compareTo(arg1.getGoodsSalesVolume());
            }
        });
		
		//取前面九个对象
		for(int i=(int) l-3;i<l;i++) {
			maxList.add(list.get(i));
		}
		
		return maxList;
	}

	//获取用户信息
	@Override
	public Userinfo getUserInfoById(Integer userId) {
		return userinfoMapper.selectByPrimaryKey(userId);
	}

	//获取积分商城的商品信息
	@Override
	public Shop getShopGoodsById(Integer goodsId) {
		// TODO Auto-generated method stub
		ShopExample example=new ShopExample();
		ShopExample.Criteria criteria=example.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId);
		return shopMapper.selectByExample(example).get(0);
	}

	//获取orderGoods
	@Override
	public List<OrderGoods> getOrderGoodsByGoodsId(Integer goodsId) {
		OrderGoodsExample orderGoodsExample=new OrderGoodsExample();
		OrderGoodsExample.Criteria criteria=orderGoodsExample.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId);
		return orderGoodsMapper.selectByExample(orderGoodsExample);
	}

	//获取ordes
	@Override
	public List<Orders> getOrdersByOrderGoodsId(Integer orderGoodsId) {
		OrdersExample ordersExample=new OrdersExample();
		OrdersExample.Criteria criteria=ordersExample.createCriteria();
		criteria.andOrderGoodsIdEqualTo(orderGoodsId);
		return ordersMapper.selectByExample(ordersExample);
	}

	//获取评价表
	@Override
	public List<Comment> getCommentByOrderUserId(Integer orderUserId) {
		CommentExample commentExample=new CommentExample();
		CommentExample.Criteria criteria=commentExample.createCriteria();
		//去重
		commentExample.setDistinct(false);
		criteria.andOrderUserIdEqualTo(orderUserId).andIsShowEqualTo("1");
		return commentMapper.selectByExample(commentExample);
	}

	//获取orderUser表
	@Override
	public OrderUser getOrderUserById(Integer orderUserId) {
		return orderUserMapper.selectByPrimaryKey(orderUserId);
	}
	
}
