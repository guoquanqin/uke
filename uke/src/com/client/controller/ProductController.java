package com.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.Shop;
import com.client.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value="/client/snack.action")
	public String getSnack(Model model) throws Exception{
		//获取小吃产品
		List<Goods> snackGoods=productService.getSnackGoods();
		model.addAttribute("snack",snackGoods);
		return "client/snack";
	}
	
	@RequestMapping(value="/client/menu.action")
	public String getMenu(Model model) throws Exception{
		//获取套餐产品
		List<Goods> menuGoods=productService.getMenuGoods();
		model.addAttribute("snack",menuGoods);
		return "client/menu";
	}
	
	@RequestMapping(value="/client/drink.action")
	public String getDrink(Model model) throws Exception{
		//获取饮料产品
		
		List<Goods> jdncGoods=productService.getJdncGoods();
		List<Goods> ttgzGoods=productService.getTtgzGoods();
		List<Goods> ngxccGoods=productService.getNgxccGoods();
		List<Goods> gwncGoods=productService.getGwncGoods();
		List<Goods> bfcyGoods=productService.getBfcyGoods();
		List<Goods> yldGoods=productService.getYldGoods();
		List<Goods> xnnxGoods=productService.getXnnxGoods();
		List<Goods> sbGoods=productService.getSbGoods();
		List<Goods> xzgzGoods=productService.getXzgzGoods();
		List<Goods> nqkfGoods=productService.getNqkfGoods();
		List<Goods> shgnGoods=productService.getshgnGoods();
		List<Goods> tstpGoods=productService.getTstpGoods();
		
		Goodstype type1=productService.getType(jdncGoods.get(0).getGoodsType());
		System.out.println(jdncGoods.get(0).getGoodsType());	
		Goodstype type2=productService.getType(ttgzGoods.get(0).getGoodsType());
		Goodstype type3=productService.getType(ngxccGoods.get(0).getGoodsType());
		Goodstype type4=productService.getType(gwncGoods.get(0).getGoodsType());
		Goodstype type5=productService.getType(bfcyGoods.get(0).getGoodsType());
		Goodstype type6=productService.getType(yldGoods.get(0).getGoodsType());
		Goodstype type7=productService.getType(xnnxGoods.get(0).getGoodsType());
		Goodstype type8=productService.getType(sbGoods.get(0).getGoodsType());
		Goodstype type9=productService.getType(xzgzGoods.get(0).getGoodsType());
		Goodstype type10=productService.getType(nqkfGoods.get(0).getGoodsType());
		Goodstype type11=productService.getType(shgnGoods.get(0).getGoodsType());
		Goodstype type12=productService.getType(tstpGoods.get(0).getGoodsType());
		
		model.addAttribute("jdncGoods", jdncGoods);
		model.addAttribute("ttgzGoods", ttgzGoods);
		model.addAttribute("ngxccGoods", ngxccGoods);
		model.addAttribute("gwncGoods", gwncGoods);
		model.addAttribute("bfcyGoods", bfcyGoods);
		model.addAttribute("yldGoods", yldGoods);
		model.addAttribute("xnnxGoods", xnnxGoods);
		model.addAttribute("sbGoods", sbGoods);
		model.addAttribute("xzgzGoods", xzgzGoods);
		model.addAttribute("nqkfGoods", nqkfGoods);
		model.addAttribute("shgnGoods", shgnGoods);
		model.addAttribute("tstpGoods", tstpGoods);
		
		model.addAttribute("type1", type1.getGtName());
		model.addAttribute("type2", type2.getGtName());
		model.addAttribute("type3", type3.getGtName());
		model.addAttribute("type4", type4.getGtName());
		model.addAttribute("type5", type5.getGtName());
		model.addAttribute("type6", type6.getGtName());
		model.addAttribute("type7", type7.getGtName());
		model.addAttribute("type8", type8.getGtName());
		model.addAttribute("type9", type9.getGtName());
		model.addAttribute("type10", type10.getGtName());
		model.addAttribute("type11", type11.getGtName());
		model.addAttribute("type12", type12.getGtName());
		return "client/drink";
	}
	
	//积分商城
	@RequestMapping(value="/client/shop.action")
	public String getShop(Model model) throws Exception{
		//获取积分产品
		List<Goods> Goods=productService.getGoods();
		//获取shop表
		List<Shop> shopGoods=productService.getShopGoods();
		
		//定义list存放类型
		List<String> type=new ArrayList<>();
		for(int i=0;i<Goods.size();i++) {
			Goodstype goodsType=productService.getType(Goods.get(i).getGoodsType());
			type.add(goodsType.getGtName());
		}
		//获取积分商城表
		model.addAttribute("shopGoods",shopGoods);
		//获取类型表
		model.addAttribute("type",type);
		//商城对应的商品表
		model.addAttribute("Goods",Goods);
		
		return "client/shop";
	}
	
	//店长推荐
	@RequestMapping(value="/client/recommend.action")
	public String getRecommend(Model model) throws Exception{
		//获取推荐产品
		List<Goods> Goods=productService.getRecommendGoods();
		
		//定义list存放类型
		List<String> type=new ArrayList<>();
		for(int i=0;i<Goods.size();i++) {
			Goodstype goodsType=productService.getType(Goods.get(i).getGoodsType());
			type.add(goodsType.getGtName());
		}
		//获取类型表
		model.addAttribute("type",type);
		//商城对应的商品表
		model.addAttribute("Goods",Goods);
		
		return "client/recommend";
	}
	
	//搜索框
	@RequestMapping(value="/client/search.action")
	public String getSearch(Model model,String search) throws Exception{
		List<Goods> goodsList=productService.getAllGoods();
		
		List<Goods> searchList=new ArrayList<>();
		for(int i=0;i<goodsList.size();i++) {
			if((goodsList.get(i).getGoodsName()).contains(search)!=false) {
				searchList.add(goodsList.get(i));
			}
		}
		
		List<String> type=new ArrayList<>();
		if(searchList.size()==0) {
			return "client/searchFail";
			
		}
		else {
			for(int i=0;i<searchList.size();i++) {
				Goodstype goodstype = productService.getType(searchList.get(i).getGoodsType());
				type.add(goodstype.getGtName());
			}
			
			model.addAttribute("searchList", searchList);
			model.addAttribute("type", type);
			model.addAttribute("searchCount", searchList.size());
			return "client/searchSuccess";
		}
	}
	
	
}
