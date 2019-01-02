package com.client.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.client.entity.Comment;
import com.client.entity.CommentAndUser;
import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.OrderGoods;
import com.client.entity.OrderUser;
import com.client.entity.Orders;
import com.client.entity.PageBean;
import com.client.entity.Shop;
import com.client.entity.Userinfo;
import com.client.service.SimpleGoodsService;

@Controller
public class SimpleGoodsController {
	
	@Autowired
	private SimpleGoodsService simpleGoodsService;
	
	//商品信息
	@RequestMapping(value="/client/simpleGoods/goodsDetail.action")
	public String getGoodsDetail(Model model,String id) {
		Integer goodsId=Integer.parseInt(id);
		int count=0;
		//定义用户评价表用于后面的分页
		List<CommentAndUser> CommentAndUserList=new ArrayList<>();
		//获取商品信息
		List<Goods> goodsList=simpleGoodsService.getGoodsById(goodsId);
		//获取商品类型
		Goodstype type=simpleGoodsService.getGoodsType(goodsList.get(0).getGoodsType());
		
		//获取推荐的商品
		List<Goods> maxList=simpleGoodsService.MaxSaleVolume();
		//获取他们的类型
		List<String> recommendType=new ArrayList<>();
		for(int i=0;i<maxList.size();i++) {
			Goodstype typeList=simpleGoodsService.getGoodsType(maxList.get(i).getGoodsType());
			recommendType.add(typeList.getGtName());
			
		}
		
		//获取商品的评价表
		List<OrderGoods> orderGoodsList=simpleGoodsService.getOrderGoodsByGoodsId(goodsId);
		if(orderGoodsList.size()==0) {
			model.addAttribute("goods",goodsList.get(0));
			model.addAttribute("type",type.getGtName());
			model.addAttribute("size",0);
			model.addAttribute("maxList",maxList);
			model.addAttribute("maxListSize",maxList.size());
			model.addAttribute("recommendType",recommendType);
			return "client/simpleGoods/goodsDetail";
		}else {
			List<Orders> ordersList=new ArrayList<>();
			for(int i=0;i<orderGoodsList.size();i++) {
				List<Orders> getOrdersList=simpleGoodsService.getOrdersByOrderGoodsId(orderGoodsList.get(i).getOrderGoodsId());
				for(int j=0;j<getOrdersList.size();j++) {
					ordersList.add(getOrdersList.get(j));
				}
			}
			List<Comment> commentList=new ArrayList<>();	
			for(int i=0;i<ordersList.size();i++) {
				//判断是否状态为已完成
				OrderUser getOrderUser=simpleGoodsService.getOrderUserById(ordersList.get(i).getOrderUserId());
				if(getOrderUser.getOrderStatus()==7) {
				List<Comment> getCommentList=simpleGoodsService.getCommentByOrderUserId(ordersList.get(i).getOrderUserId());
				commentList.add(getCommentList.get(0));
				}
			}
			
			//获取5星好评率
			for(int i=0;i<commentList.size();i++) {
				CommentAndUser commentAndUser=new CommentAndUser();
				if(commentList.get(i).getCommentLevel()==5) {
					count++;
				}
				//用户评价表
				commentAndUser.setComment(commentList.get(i));
				//获取用户信息
				Userinfo userinfo=simpleGoodsService.getUserInfoById(commentList.get(i).getUserId());
				commentAndUser.setPhone(userinfo.getUserPhone());
				CommentAndUserList.add(commentAndUser);
			}
			
			int praiseRate=0;
			if(commentList.size()!=0) {
				praiseRate=(count/commentList.size())*100;
			}
			//获取评价表
			PageBean pageBean=new PageBean(1,7,commentList.size());
			pageBean.setCommentAndUser(CommentAndUserList);
			
			
			
			
			model.addAttribute("goods",goodsList.get(0));
			model.addAttribute("type",type.getGtName());
			model.addAttribute("commentList",commentList);
			model.addAttribute("size",commentList.size());
			
			model.addAttribute("praiseRate",praiseRate);
			model.addAttribute("maxList",maxList);
			model.addAttribute("maxListSize",maxList.size());
			model.addAttribute("recommendType",recommendType);
			model.addAttribute("pageBean",pageBean);
			return "client/simpleGoods/goodsDetail";
		}
	}
	
	//分页信息
	@RequestMapping(value="/client/simpleGoods/findPage.action",consumes = "application/json")
	public @ResponseBody 
	PageBean getPageBean(@RequestBody String pageNum,String id,HttpServletRequest request) throws Exception{
		Integer page=Integer.parseInt(pageNum);
		Integer goodsId=Integer.parseInt(id);
		
		List<CommentAndUser> CommentAndUserList=new ArrayList<>();
		List<CommentAndUser> CommentAndUserListByIndex=new ArrayList<>();
		//获取商品的评价表
		List<OrderGoods> orderGoodsList=simpleGoodsService.getOrderGoodsByGoodsId(goodsId);
		List<Orders> ordersList=new ArrayList<>();
		for(int i=0;i<orderGoodsList.size();i++) {
			List<Orders> getOrdersList=simpleGoodsService.getOrdersByOrderGoodsId(orderGoodsList.get(i).getOrderGoodsId());
			for(int j=0;j<getOrdersList.size();j++) {
				ordersList.add(getOrdersList.get(j));
			}
		}
		List<Comment> commentList=new ArrayList<>();	
		for(int i=0;i<ordersList.size();i++) {
			//判断是否状态为已完成
			OrderUser getOrderUser=simpleGoodsService.getOrderUserById(ordersList.get(i).getOrderUserId());
			if(getOrderUser.getOrderStatus()==7) {
			List<Comment> getCommentList=simpleGoodsService.getCommentByOrderUserId(ordersList.get(i).getOrderUserId());
			commentList.add(getCommentList.get(0));
			}
		}
		for(int i=0;i<commentList.size();i++) {
			CommentAndUser commentAndUser=new CommentAndUser();
			//用户评价表
			commentAndUser.setComment(commentList.get(i));
			//获取用户信息
			Userinfo userinfo=simpleGoodsService.getUserInfoById(commentList.get(i).getUserId());
			commentAndUser.setPhone(userinfo.getUserPhone());
			
			String[] arrPhoto= {};
			arrPhoto=commentAndUser.getComment().getCommentPhoto().split("/");
			for(int j=0;j<arrPhoto.length;j++) {
				arrPhoto[j]=request.getContextPath()+"/images/评价图片/"+arrPhoto[j];
			}
			List<String> photoList=new ArrayList<>();
			Collections.addAll(photoList,arrPhoto);
			commentAndUser.setPhotoList(photoList);
			
			CommentAndUserList.add(commentAndUser);
			
		}
		PageBean pageBean=new PageBean(page,7,commentList.size());
		
		int endPageIndex=pageBean.getStartIndex()+pageBean.getPageSize();
		int startPageIndex=pageBean.getStartIndex();
		if(startPageIndex+pageBean.getPageSize()>=pageBean.getTotalRecord()) {
			endPageIndex=pageBean.getTotalRecord();
		}
		for(int i=startPageIndex;i<endPageIndex;i++) {
			CommentAndUserListByIndex.add(CommentAndUserList.get(i));
		}
		pageBean.setCommentAndUser(CommentAndUserListByIndex);
		return pageBean;
	}
	
	//积分商城
	@RequestMapping(value="/client/simpleGoods/goodsShopDetail.action")
	public String getShopGoodsDetail(Model model,String id) {
		Integer goodsId=Integer.parseInt(id);
		int count=0;
		//定义用户评价表用于后面的分页
		List<CommentAndUser> CommentAndUserList=new ArrayList<>();
		//获取商品信息
		List<Goods> goodsList=simpleGoodsService.getGoodsById(goodsId);
		//获取商品类型
		Goodstype type=simpleGoodsService.getGoodsType(goodsList.get(0).getGoodsType());
		
		//获取积分商城的商品信息
		Shop shopList=simpleGoodsService.getShopGoodsById(goodsId);
		//获取推荐的商品
		List<Goods> maxList=simpleGoodsService.MaxSaleVolume();
		//获取他们的类型
		List<String> recommendType=new ArrayList<>();
		for(int i=0;i<maxList.size();i++) {
			Goodstype typeList=simpleGoodsService.getGoodsType(maxList.get(i).getGoodsType());
			recommendType.add(typeList.getGtName());
			
		}
		//获取商品的评价表
		List<OrderGoods> orderGoodsList=simpleGoodsService.getOrderGoodsByGoodsId(goodsId);
		if(orderGoodsList.size()==0) {
			model.addAttribute("goods",goodsList.get(0));
			model.addAttribute("type",type.getGtName());
			model.addAttribute("size",0);
			model.addAttribute("maxList",maxList);
			model.addAttribute("maxListSize",maxList.size());
			model.addAttribute("recommendType",recommendType);
			model.addAttribute("shopList",shopList);
			return "client/simpleGoods/shopGoodsDetail";
		}else {
		List<Orders> ordersList=new ArrayList<>();
		for(int i=0;i<orderGoodsList.size();i++) {
			List<Orders> getOrdersList=simpleGoodsService.getOrdersByOrderGoodsId(orderGoodsList.get(i).getOrderGoodsId());
			for(int j=0;j<getOrdersList.size();j++) {
				ordersList.add(getOrdersList.get(j));
			}
		}
		List<Comment> commentList=new ArrayList<>();	
		for(int i=0;i<ordersList.size();i++) {
			//判断是否状态为已完成
			OrderUser getOrderUser=simpleGoodsService.getOrderUserById(ordersList.get(i).getOrderUserId());
			if(getOrderUser.getOrderStatus()==7) {
			List<Comment> getCommentList=simpleGoodsService.getCommentByOrderUserId(ordersList.get(i).getOrderUserId());
			commentList.add(getCommentList.get(0));
			}
		}
		//获取5星好评率
		for(int i=0;i<commentList.size();i++) {
			CommentAndUser commentAndUser=new CommentAndUser();
			if(commentList.get(i).getCommentLevel()==5) {
				count++;
			}
			//用户评价表
			commentAndUser.setComment(commentList.get(i));
			//获取用户信息
			Userinfo userinfo=simpleGoodsService.getUserInfoById(commentList.get(i).getUserId());
			commentAndUser.setPhone(userinfo.getUserPhone());
			CommentAndUserList.add(commentAndUser);
			
			
		}
		int praiseRate=(count/commentList.size())*100;
		//获取评价表
		PageBean pageBean=new PageBean(1,7,commentList.size());
		pageBean.setCommentAndUser(CommentAndUserList);
		
		
		model.addAttribute("goods",goodsList.get(0));
		model.addAttribute("type",type.getGtName());
		model.addAttribute("commentList",commentList);
		model.addAttribute("size",commentList.size());
		model.addAttribute("praiseRate",praiseRate);
		model.addAttribute("maxList",maxList);
		model.addAttribute("maxListSize",maxList.size());
		model.addAttribute("recommendType",recommendType);
		model.addAttribute("pageBean",pageBean);
		model.addAttribute("shopList",shopList);
		return "client/simpleGoods/shopGoodsDetail";
		}
	}
}
