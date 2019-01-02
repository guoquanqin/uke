package com.client.service;

import java.util.List;

import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.Shop;

public interface ProductService {

	//获取小吃产品
	List<Goods> getSnackGoods();

	//获取套餐产品
	List<Goods> getMenuGoods();

	//饮料系列
	List<Goods> getJdncGoods();
	List<Goods> getTtgzGoods();
	List<Goods> getNgxccGoods();
	List<Goods> getGwncGoods();
	List<Goods> getBfcyGoods();
	List<Goods> getYldGoods();
	List<Goods> getXnnxGoods();
	List<Goods> getSbGoods();
	List<Goods> getXzgzGoods();
	List<Goods> getNqkfGoods();
	List<Goods> getshgnGoods();
	List<Goods> getTstpGoods();

	//获取商品类型
	Goodstype getType(Integer goodsType);

	//获得积分商城对应的商品表
	List<Goods> getGoods();
	//获取积分商城表
	List<Shop> getShopGoods();
	
	//获得推荐商品表
	List<Goods> getRecommendGoods();

	//获得所有商品
	List<Goods> getAllGoods();

}
