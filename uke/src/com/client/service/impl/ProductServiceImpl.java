package com.client.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.dao.GoodsMapper;
import com.client.dao.GoodstypeMapper;
import com.client.dao.RecommendMapper;
import com.client.dao.ShopMapper;
import com.client.entity.Goods;
import com.client.entity.GoodsExample;
import com.client.entity.Goodstype;
import com.client.entity.Recommend;
import com.client.entity.RecommendExample;
import com.client.entity.Shop;
import com.client.entity.ShopExample;
import com.client.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodstypeMapper goodstypeMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private RecommendMapper recommendMapper;
	
	//获取小吃产品
	@Override
	public List<Goods> getSnackGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(13).andGoodsIsSaleEqualTo("1");
		List<Goods> list = goodsMapper.selectByExample(goodsExample);
		return list;
	}

	//获取套餐产品
	@Override
	public List<Goods> getMenuGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(14).andGoodsIsSaleEqualTo("1");
		List<Goods> list = goodsMapper.selectByExample(goodsExample);
		return list;
	}

	//饮料系列
	@Override
	public List<Goods> getJdncGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(1).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getTtgzGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(2).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getNgxccGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(3).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getGwncGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(4).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getBfcyGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(5).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getYldGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(6).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getXnnxGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(7).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getSbGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(8).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getXzgzGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(9).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getNqkfGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(10).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getshgnGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(11).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	@Override
	public List<Goods> getTstpGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsTypeEqualTo(12).andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}

	//商品类型
	@Override
	public Goodstype getType(Integer goodsType) {
		Goodstype type = goodstypeMapper.selectByPrimaryKey(goodsType);
		return type;
	}

	//获得积分商城商品
	@Override
	public List<Goods> getGoods() {
		ShopExample shopExample=new ShopExample();
		ShopExample.Criteria criteria=shopExample.createCriteria();
		criteria.andIsSaleEqualTo("1");
		List<Shop> list=shopMapper.selectByExample(shopExample);
		
		//定义list存放goods对象
		List<Goods> goodsList=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			Goods goods = goodsMapper.selectByPrimaryKey(list.get(i).getGoodsId());
			goodsList.add(goods);
		}
		return goodsList;
	}

	//获取积分商城表
	@Override
	public List<Shop> getShopGoods() {
		ShopExample shopExample=new ShopExample();
		ShopExample.Criteria criteria=shopExample.createCriteria();
		criteria.andIsSaleEqualTo("1");
		List<Shop> list=shopMapper.selectByExample(shopExample);
		return list;
	}

	@Override
	public List<Goods> getRecommendGoods() {
		RecommendExample recommendExample=new RecommendExample();
		RecommendExample.Criteria criteria=recommendExample.createCriteria();
		criteria.andIsSaleEqualTo("1");
		List<Recommend> list=recommendMapper.selectByExample(recommendExample);
		
		//定义list存放goods对象
		List<Goods> goodsList=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			Goods goods = goodsMapper.selectByPrimaryKey(list.get(i).getGoodsId());
			goodsList.add(goods);
		}
		return goodsList;
	}

	//获得所有商品
	@Override
	public List<Goods> getAllGoods() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsIsSaleEqualTo("1");
		return goodsMapper.selectByExample(goodsExample);
	}
	

}
