package com.client.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.client.entity.Basket;
import com.client.entity.Collect;
import com.client.entity.Comment;
import com.client.entity.FeedbackComment;
import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.IntegralOrder;
import com.client.entity.OrderDetail;
import com.client.entity.OrderGoods;
import com.client.entity.OrderUser;
import com.client.entity.Orders;
import com.client.entity.PageBeanInOrder;
import com.client.entity.Recommend;
import com.client.entity.Shop;
import com.client.entity.Signin;
import com.client.entity.Userinfo;
import com.client.service.SimpleGoodsService;
import com.client.service.UserCenterService;
import com.client.service.UserValidateService;

@Controller
public class UserCenterController {
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private UserValidateService userValidateService;
	@Autowired
	private SimpleGoodsService simpleGoodsService;

	// 用户中心主页
	@RequestMapping(value = "/client/userCenter/userHome.action")
	public String userHome(Model model, HttpSession session) {
		// 待付款
		int count1 = 0;
		// 待收货
		int count2 = 0;
		// 待评价
		int count3 = 0;
		// 定义orderDetail表用于存储list集合
		OrderDetail orderDetail = new OrderDetail();

		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);

		// 获取签到日期信息
		Signin signin = userCenterService.getSignInByUserId(userId);
		//sessoin
		session.setAttribute("signin", signin);

		// 获取待支付待收货待评价 数量
		List<OrderUser> orderUserList = userCenterService.getOrderUserByUserId(userId);
		orderDetail.setOrderUserList(orderUserList);
		//获取积分订单情况
		List<IntegralOrder> integralOrder=userCenterService.getAllIntegralOrderByUserId(userId);
		for(int i=0;i<integralOrder.size();i++) {
			if(integralOrder.get(i).getOrderStatus()==1) {
				count1++;
			}
			if(integralOrder.get(i).getOrderStatus()==3) {
				count2++;
			}
			if(integralOrder.get(i).getOrderStatus()==5) {
				count3++;
			}
		}
		for (int i = 0; i < orderUserList.size(); i++) {
			List<OrderGoods> orderGoodsList = new ArrayList<>();
			if (orderUserList.get(i).getOrderStatus() == 1) {
				count1++;
			}
			if (orderUserList.get(i).getOrderStatus() == 3) {
				count2++;
			}
			if (orderUserList.get(i).getOrderStatus() == 5) {
				count3++;
			}

			// 获取用户订单 第一次获取三个
			List<Orders> order = userCenterService.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
			for (int j = 0; j < order.size(); j++) {
				OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
				orderGoodsList.add(orderGoods);
				// 获取商品信息
				List<Goods> goods = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
				orderGoodsList.get(j).setGoods(goods.get(0));

				Goodstype type = simpleGoodsService.getGoodsType(goods.get(0).getGoodsType());
				orderGoodsList.get(j).setGoodsType(type);
			}

			orderDetail.getOrderUserList().get(i).setOrderGoodsList(orderGoodsList);
		};

		// 获取收藏内容
		List<Collect> collectList = userCenterService.getCollect(userId);
		List<Goods> goodsInCollectList = new ArrayList<>();
		List<Goodstype> typeInCollectGoods = new ArrayList<>();
		for (int k = 0; k < collectList.size(); k++) {
			List<Goods> goodsInCollect = simpleGoodsService.getGoodsById(collectList.get(k).getGoodsId());
			goodsInCollectList.add(goodsInCollect.get(0));
			Goodstype goodsType = simpleGoodsService.getGoodsType(goodsInCollect.get(0).getGoodsType());
			typeInCollectGoods.add(goodsType);
		}

		// 获取新品推荐
		List<Recommend> recommend = userCenterService.getRecomment();
		System.out.println("size" + recommend.get(1).getGoodsId());
		List<Goods> goodsInRecommend = new ArrayList<>();
		List<Goodstype> typeInReommendGoods = new ArrayList<>();
		for (int q = 0; q < recommend.size(); q++) {
			List<Goods> goodsById = simpleGoodsService.getGoodsById(recommend.get(q).getGoodsId());
			goodsInRecommend.add(goodsById.get(0));
			Goodstype goodsTypeInRecomment = simpleGoodsService.getGoodsType(goodsById.get(0).getGoodsType());
			typeInReommendGoods.add(goodsTypeInRecomment);
		}

		model.addAttribute("userinfo", userinfo);
		model.addAttribute("count1", count1);
		model.addAttribute("count2", count2);
		model.addAttribute("count3", count3);
		model.addAttribute("orderDetail", orderDetail);
		model.addAttribute("goodsInCollectList", goodsInCollectList);
		model.addAttribute("typeInCollectGoods", typeInCollectGoods);
		model.addAttribute("goodsInRecommend", goodsInRecommend);
		model.addAttribute("typeInReommendGoods", typeInReommendGoods);

		return "client/userHome";
	}

	// 签到
	@RequestMapping(value = "/client/userCenter/sign.action")
	public @ResponseBody String userHome(HttpSession httpSession,@RequestBody String today) {
		Userinfo user=(Userinfo)httpSession.getAttribute("USER");
		Integer id = user.getUserId();
		// 获取签到日期信息
		Signin signin = userCenterService.getSignInByUserId(id);
		
		/*1表示 今日已签到
		 * 2表示 本月已达标，获得50积分
		 * 3表示 已签到20天，签到无效
		 * 4表示 签到成功
		 * */
		
		//本月月份
		Date date=new Date();
		String month=(date.getMonth()+1)<10?"0"+(date.getMonth()+1):String.valueOf((date.getMonth()+1));
		
		//如果为空
		if(signin.getSignDate()=="") {
			System.out.println("为空");
			signin.setSignDate(month+":" + today + "/");
			signin.setSignDayNums(signin.getSignDayNums() + 1);
			// 保存数据
			userCenterService.saveSignIn(signin);
			
			return "4";
		}else {
			String signMonth=signin.getSignDate().substring(0,2);
			
			String signDate = signin.getSignDate().substring(3);
			
			String[] list = signDate.split("/");
			
			
			//如果存储的月份跟当前月份不一致，删除并重新签到
			if(!month.equals(signMonth)) {
				signin.setSignDate(month+":" + today + "/");
				signin.setSignDayNums(1);
				// 保存数据
				userCenterService.saveSignIn(signin);
				return "4";
			}else {
				for (int i = 0; i < list.length; i++) {
					if (list[i].equals(today)) {
						return "1";
					}
				}
				// 签到20天
				if (list.length == 20) {
					Userinfo userInfo = simpleGoodsService.getUserInfoById(id);
					userInfo.setUserIntegral(userInfo.getUserIntegral() + 100);
					userCenterService.saveUserInfo(userInfo);
					return "2";
				}
				// 超过20天签到无效
				if (list.length > 20) {
					return "3";
				}else {
					signin.setSignDate(signin.getSignDate() + today + "/");
					signin.setSignDayNums(signin.getSignDayNums() + 1);
					// 保存数据
					userCenterService.saveSignIn(signin);
					return "4";
				}
			}
			
		}
		
	}

	// 所有订单信息
	@RequestMapping(value = "/client/userCenter/userAllOrder.action")
	public String userAllOrder(Model model, HttpSession session, Integer pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		// 定义orderDetail表用于存储订单全部信息
		OrderDetail orderDetail = new OrderDetail();

		// 获取所有订单信息
		List<OrderUser> orderUserList = userCenterService.getOrderUserByUserId(userId);
		orderDetail.setOrderUserList(orderUserList);
		for (int i = 0; i < orderUserList.size(); i++) {
			List<OrderGoods> orderGoodsList = new ArrayList<>();

			// 获取用户订单 第一次获取三个
			List<Orders> order = userCenterService.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
			for (int j = 0; j < order.size(); j++) {
				OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
				orderGoodsList.add(orderGoods);
				// 获取商品信息
				List<Goods> goods = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
				orderGoodsList.get(j).setGoods(goods.get(0));

				Goodstype type = simpleGoodsService.getGoodsType(goods.get(0).getGoodsType());
				orderGoodsList.get(j).setGoodsType(type);
			}

			orderDetail.getOrderUserList().get(i).setOrderGoodsList(orderGoodsList);
		}
		;

		int size = orderDetail.getOrderUserList().size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

		List<OrderUser> orderUserListForPage = new ArrayList<>();
		// 获取分页信息
		PageBeanInOrder pageBean = new PageBeanInOrder(pageNum, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			orderUserListForPage.add(orderDetail.getOrderUserList().get(i));
		}

		model.addAttribute("orderUserListForPage", orderUserListForPage);
		model.addAttribute("size", size);
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBean", pageBean);
		return "client/userAllOrder";
	}

	// 所有待付款信息
	@RequestMapping(value = "/client/userCenter/userOrderWaitForPayment.action")
	public String userOrderWaitForPayment(Model model, HttpSession session, Integer pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		// 定义orderDetail表用于存储订单全部信息
		OrderDetail orderDetail = new OrderDetail();

		// 获取所有订单信息
		List<OrderUser> orderUserList = userCenterService.getOrderUserByOrderIdAndStatus(userId, 1);
		orderDetail.setOrderUserList(orderUserList);
		for (int i = 0; i < orderUserList.size(); i++) {
			List<OrderGoods> orderGoodsList = new ArrayList<>();

			// 获取用户订单 第一次获取三个
			List<Orders> order = userCenterService.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
			for (int j = 0; j < order.size(); j++) {
				OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
				orderGoodsList.add(orderGoods);
				// 获取商品信息
				List<Goods> goods = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
				orderGoodsList.get(j).setGoods(goods.get(0));

				Goodstype type = simpleGoodsService.getGoodsType(goods.get(0).getGoodsType());
				orderGoodsList.get(j).setGoodsType(type);
			}

			orderDetail.getOrderUserList().get(i).setOrderGoodsList(orderGoodsList);
		};

		int size = orderDetail.getOrderUserList().size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

		List<OrderUser> orderUserListForPage = new ArrayList<>();
		// 获取分页信息
		PageBeanInOrder pageBean = new PageBeanInOrder(pageNum, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			orderUserListForPage.add(orderDetail.getOrderUserList().get(i));
		}

		model.addAttribute("orderUserListForPage", orderUserListForPage);
		model.addAttribute("size", size);
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBean", pageBean);
		return "client/userOrderWaitForPayment";
	}

	// 所有待收货信息
	@RequestMapping(value = "/client/userCenter/userOrderWaitForGet.action")
	public String userOrderWaitForGet(Model model, HttpSession session, Integer pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		// 定义orderDetail表用于存储订单全部信息
		OrderDetail orderDetail = new OrderDetail();

		// 获取所有订单信息
		List<OrderUser> orderUserList = userCenterService.getOrderUserByOrderIdAndStatus(userId, 3);
		orderDetail.setOrderUserList(orderUserList);
		for (int i = 0; i < orderUserList.size(); i++) {
			List<OrderGoods> orderGoodsList = new ArrayList<>();

			// 获取用户订单 第一次获取三个
			List<Orders> order = userCenterService.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
			for (int j = 0; j < order.size(); j++) {
				OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
				orderGoodsList.add(orderGoods);
				// 获取商品信息
				List<Goods> goods = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
				orderGoodsList.get(j).setGoods(goods.get(0));

				Goodstype type = simpleGoodsService.getGoodsType(goods.get(0).getGoodsType());
				orderGoodsList.get(j).setGoodsType(type);
			}

			orderDetail.getOrderUserList().get(i).setOrderGoodsList(orderGoodsList);
		}
		;

		int size = orderDetail.getOrderUserList().size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

		List<OrderUser> orderUserListForPage = new ArrayList<>();
		// 获取分页信息
		PageBeanInOrder pageBean = new PageBeanInOrder(pageNum, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			orderUserListForPage.add(orderDetail.getOrderUserList().get(i));
		}

		model.addAttribute("orderUserListForPage", orderUserListForPage);
		model.addAttribute("size", size);
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBean", pageBean);
		return "client/userOrderWaitForGet";
	}

	// 所有待评价信息
	@RequestMapping(value = "/client/userCenter/userOrderWaitForComment.action")
	public String userOrderWaitForComment(Model model, HttpSession session, Integer pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		// 定义orderDetail表用于存储订单全部信息
		OrderDetail orderDetail = new OrderDetail();

		// 获取订单信息
		List<OrderUser> orderUserList = userCenterService.getOrderUserByOrderIdAndStatus(userId, 5);
		orderDetail.setOrderUserList(orderUserList);
		for (int i = 0; i < orderUserList.size(); i++) {
			List<OrderGoods> orderGoodsList = new ArrayList<>();

			// 获取用户订单 第一次获取三个
			List<Orders> order = userCenterService.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
			for (int j = 0; j < order.size(); j++) {
				OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
				orderGoodsList.add(orderGoods);
				// 获取商品信息
				List<Goods> goods = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
				orderGoodsList.get(j).setGoods(goods.get(0));

				Goodstype type = simpleGoodsService.getGoodsType(goods.get(0).getGoodsType());
				orderGoodsList.get(j).setGoodsType(type);
			}

			orderDetail.getOrderUserList().get(i).setOrderGoodsList(orderGoodsList);
		};

		int size = orderDetail.getOrderUserList().size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

		List<OrderUser> orderUserListForPage = new ArrayList<>();
		// 获取分页信息
		PageBeanInOrder pageBean = new PageBeanInOrder(pageNum, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			orderUserListForPage.add(orderDetail.getOrderUserList().get(i));
		}

		model.addAttribute("orderUserListForPage", orderUserListForPage);
		model.addAttribute("size", size);
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBean", pageBean);
		return "client/userOrderWaitForComment";
	}

	// 搜索框搜索
	@RequestMapping(value = "/client/userCenter/userOrderBySearch.action")
	public String userOrderBySearch(Model model, HttpSession session, String search, Integer pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		// 定义orderDetail表用于存储订单全部信息
		OrderDetail orderDetailForOrderSearch = new OrderDetail();
		List<OrderUser> orderUserListForPage = new ArrayList<>();

		// 如果输入为空
		if (search == "") {
			model.addAttribute("size", 0);
			model.addAttribute("userinfo", userinfo);

			return "client/userAllOrder";
		} else {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(search);
			// 根据订单号查询
			if (isNum.matches()) {
				Integer orderId = Integer.parseInt(search);
				// 判断订单号是否存在
				List<OrderUser> orderUserListByOrderId = userCenterService.getOrderUserByUserIdAndOrderId(userId,
						orderId);
				if (orderUserListByOrderId.size() == 0) {
					model.addAttribute("size", 0);
					model.addAttribute("userinfo", userinfo);

					return "client/userAllOrder";
				} else {
					orderDetailForOrderSearch.setOrderUserList(orderUserListByOrderId);
					List<Orders> orderListByOrderId = userCenterService
							.getOrderByOrderUserId(orderUserListByOrderId.get(0).getOrderUserId());
					List<OrderGoods> orderGoodsList = new ArrayList<>();
					for (int i = 0; i < orderListByOrderId.size(); i++) {
						OrderGoods orderGoods = userCenterService
								.getOrderGoodsByOrderId(orderListByOrderId.get(i).getOrderGoodsId());
						orderGoodsList.add(orderGoods);
						// 获取商品信息
						List<Goods> goods = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
						orderGoodsList.get(i).setGoods(goods.get(0));

						Goodstype type = simpleGoodsService.getGoodsType(goods.get(0).getGoodsType());
						orderGoodsList.get(i).setGoodsType(type);
						orderDetailForOrderSearch.getOrderUserList().get(0).setOrderGoodsList(orderGoodsList);
					}

					orderUserListForPage.add(orderUserListByOrderId.get(0));
					System.out.println("按订单号搜索");
				}
			}

			// 根据商品名称模糊查询
			else {
				// 获取所有orderGoods
				List<OrderGoods> allOrderGoodsList = userCenterService.getAllOrderGoods();
				List<OrderGoods> searchOrderGoods = new ArrayList<>();
				List<Goods> searchGoodsList = new ArrayList<>();
				for (int i = 0; i < allOrderGoodsList.size(); i++) {
					List<Goods> goodsList = simpleGoodsService.getGoodsById(allOrderGoodsList.get(i).getGoodsId());
					if ((goodsList.get(0).getGoodsName()).contains(search) != false) {
						searchOrderGoods.add(allOrderGoodsList.get(i));
						searchGoodsList.add(goodsList.get(0));
					}
				}
				// 如果找不到就返回
				if (searchOrderGoods.size() == 0) {
					model.addAttribute("size", 0);
					model.addAttribute("userinfo", userinfo);

					return "client/userAllOrder";
				} else {
					// 通过OrderUserId获取到订单
					List<Orders> searchOrderList = new ArrayList<>();
					for (int i = 0; i < searchOrderGoods.size(); i++) {
						List<Orders> searchOrder = userCenterService
								.getOrderByOrderGoodsId(searchOrderGoods.get(i).getOrderGoodsId());
						searchOrderList.add(searchOrder.get(0));
					}
					// 通过Order查询OrderUser订单表
					List<OrderUser> searchOrderUseList = new ArrayList<>();
					for (int i = 0; i < searchOrderList.size(); i++) {
						List<OrderUser> searchOrderUser = userCenterService.getOrderUserByUserIdAndOrderId(userId,
								searchOrderList.get(i).getOrderUserId());
						searchOrderUseList.add(searchOrderUser.get(0));
					}

					orderDetailForOrderSearch.setOrderUserList(searchOrderUseList);

					for (int i = 0; i < searchOrderUseList.size(); i++) {
						List<OrderGoods> searchOrderGoodsList = new ArrayList<>();

						// 获取用户订单 第一次获取三个
						List<Orders> order = userCenterService
								.getOrderByOrderUserId(searchOrderUseList.get(i).getOrderUserId());
						for (int j = 0; j < order.size(); j++) {
							OrderGoods orderGoods = userCenterService
									.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
							searchOrderGoodsList.add(orderGoods);
							// 获取商品信息
							List<Goods> goods = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
							searchOrderGoodsList.get(j).setGoods(goods.get(0));

							Goodstype type = simpleGoodsService.getGoodsType(goods.get(0).getGoodsType());
							searchOrderGoodsList.get(j).setGoodsType(type);
						}

						orderDetailForOrderSearch.getOrderUserList().get(i).setOrderGoodsList(searchOrderGoodsList);
					}

					int size = searchOrderUseList.size();
					// 页数
					int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

					// 获取分页信息
					PageBeanInOrder pageBean = new PageBeanInOrder(pageNum, 7, size);
					// 结束索引
					int end = pageBean.getStartIndex() + 7;
					if (end > size) {
						end = size;
					}
					for (int i = pageBean.getStartIndex(); i < end; i++) {
						orderUserListForPage.add(orderDetailForOrderSearch.getOrderUserList().get(i));
					}

					model.addAttribute("pageSize", pageSize);
					model.addAttribute("pageBean", pageBean);
				}
			}

			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("size", orderUserListForPage.size());
			model.addAttribute("userinfo", userinfo);

			return "client/userAllOrder";
		}
	}

	// 购物车信息
	@RequestMapping(value = "/client/userCenter/userBasket.action")
	public String userBasket(Model model, HttpSession session) {
			Userinfo user = (Userinfo) session.getAttribute("USER");
			Integer userId=user.getUserId();
			// 获取购物车信息
			List<Basket> basketList = userCenterService.getBasketList(userId);
			// 定义goods用于获取购物车里面的商品信息
			List<Goods> goods = new ArrayList<>();
			List<Goodstype> goodsType = new ArrayList<>();
			for (int i = 0; i < basketList.size(); i++) {
				List<Goods> goodsById = simpleGoodsService.getGoodsById(basketList.get(i).getGoodsId());
				goods.add(goodsById.get(0));
			}
			for (int i = 0; i < goods.size(); i++) {
				Goodstype type = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
				goodsType.add(type);
			}

		model.addAttribute("basketList", basketList);
		model.addAttribute("goods", goods);
		model.addAttribute("size", goods.size());
		model.addAttribute("goodsType", goodsType);

		return "client/userBasket";
	}

	// 收藏夹信息
	@RequestMapping(value = "/client/userCenter/userCollect.action")
	public String userCollect(Model model, HttpSession session) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();

		// 获取收藏夹内容
		List<Collect> collectList = userCenterService.getCollect(userId);
		// 定义goods用于获取购物车里面的商品信息
		List<Goods> goods = new ArrayList<>();
		List<Goodstype> goodsType = new ArrayList<>();
		for (int i = 0; i < collectList.size(); i++) {
			List<Goods> goodsById = simpleGoodsService.getGoodsById(collectList.get(i).getGoodsId());
			goods.add(goodsById.get(0));
		}
		for (int i = 0; i < goods.size(); i++) {
			Goodstype type = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
			goodsType.add(type);
		}

		model.addAttribute("goods", goods);
		model.addAttribute("collect", collectList);
		model.addAttribute("size", goods.size());
		model.addAttribute("goodsType", goodsType);
		return "client/userCollect";
	}

	// 收藏夹搜索框搜索
	@RequestMapping(value = "/client/userCenter/userCollectBySearch.action")
	public String userCollectBySearch(Model model, HttpSession session, String search) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();

		if(search=="") {
			model.addAttribute("size", 0);
			return "client/userCollect";
		}else {
		// 获取收藏夹内容
		List<Collect> collect = userCenterService.getCollect(userId);
		// 定义goods用于获取购物车里面的商品信息
		List<Goods> goods = new ArrayList<>();
		List<Goodstype> goodsType = new ArrayList<>();
		
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(search);
		// 根据关键字查询
		if (!isNum.matches()) {
			for (int i = 0; i < collect.size(); i++) {
				List<Goods> goodsById = simpleGoodsService.getGoodsById(collect.get(i).getGoodsId());
				if (goodsById.get(0).getGoodsName().contains(search)!= false) {
					goods.add(goodsById.get(0));
				}
			}
			
			for (int i = 0; i < goods.size(); i++) {
				Goodstype type = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
				goodsType.add(type);
			}
		}
		else {
			Integer searchId=Integer.parseInt(search);
			for (int i = 0; i < collect.size(); i++) {
				//如果订单号匹配
				if(searchId.equals(collect.get(i).getCollectId())){
					List<Goods> goodsById = simpleGoodsService.getGoodsById(collect.get(i).getGoodsId());
					goods.add(goodsById.get(0));
				}
			}
			for (int i = 0; i < goods.size(); i++) {
				Goodstype type = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
				goodsType.add(type);
			}
		}

		model.addAttribute("goods", goods);
		model.addAttribute("size", goods.size());
		model.addAttribute("goodsType", goodsType);
		return "client/userCollect";
		}
	}

	//评价表
	@RequestMapping(value="/client/userCenter/userComment.action")
	public String userComment(Model model,HttpSession session,String pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		
		//获取status为已完成的orderUser表
		List<OrderUser> orderUserList=userCenterService.getOrderUserByOrderIdAndStatus(userId, 7);
		List<Comment> commentList=new ArrayList<>();
		for(int i=0;i<orderUserList.size();i++) {
			List<Comment> getComment=userCenterService.getCommentByOrderUserId(orderUserList.get(i).getOrderUserId());
			commentList.add(getComment.get(0));
		}
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderUserList(orderUserList);
		for (int i = 0; i < orderUserList.size(); i++) {
			List<OrderGoods> orderGoodsList = new ArrayList<>();

			// 获取用户订单 第一次获取三个
			List<Orders> order = userCenterService.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
			for (int j = 0; j < order.size(); j++) {
				OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
				orderGoodsList.add(orderGoods);
				// 获取商品信息
				List<Goods> goods = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
				orderGoodsList.get(j).setGoods(goods.get(0));

				Goodstype type = simpleGoodsService.getGoodsType(goods.get(0).getGoodsType());
				orderGoodsList.get(j).setGoodsType(type);
			}

			orderDetail.getOrderUserList().get(i).setOrderGoodsList(orderGoodsList);
		};
		
		//获取评价反馈表
		List<FeedbackComment> fbComment=userCenterService.getFeedbackCommentByUserId(userId);
		List<FeedbackComment> fbCommentList=new ArrayList<>();
		for(int i=0;i<commentList.size();i++) {
			FeedbackComment fb=new FeedbackComment();
			fb.setFeenbackComment(null);
			fbCommentList.add(fb);
		}
		for(int i=0;i<commentList.size();i++) {
			for(int j=0;j<fbComment.size();j++) {
				if(commentList.get(i).getOrderUserId()==fbComment.get(j).getOrderUserId()) {
					fbCommentList.get(i).setFeenbackComment(fbComment.get(j));
				}
			}
		}
		System.out.println(fbCommentList.size());
		//分页
		int size = orderUserList.size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;
		Integer page=Integer.parseInt(pageNum);
		// 获取分页信息
		PageBeanInOrder pageBean = new PageBeanInOrder(page,7,size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		//定义存储新的评价表
		List<OrderUser> orderUserListForPage=new ArrayList<>();
		List<FeedbackComment> fbCommentListForPage=new ArrayList<>();
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			orderUserListForPage.add(orderDetail.getOrderUserList().get(i));
			fbCommentListForPage.add(fbCommentList.get(i));	
		}
		
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("size", size);
		model.addAttribute("orderUserListForPage", orderUserListForPage);
		
		model.addAttribute("commentList",commentList);
		model.addAttribute("orderUserList",orderUserList);
		model.addAttribute("fbCommentListForPage", fbCommentListForPage);
		return "client/userComment";
	}
	
	//积分兑换页
	@RequestMapping(value="/client/userCenter/userIntegral.action")
	public String userIntegral(Model model,HttpSession session,String pageNum) {
		
		//获取积分商城的商品
		List<Shop> shopList=userCenterService.getAllIntegral("1");
		List<Goods> goods=new ArrayList<>();
		List<Goodstype> goodsType=new ArrayList<>();
		for(int i=0;i<shopList.size();i++) {
			List<Goods> goodsList=simpleGoodsService.getGoodsById(shopList.get(i).getGoodsId());
			goods.add(goodsList.get(0));
		}
		for(int i=0;i<goods.size();i++) {
			Goodstype goodsTypeList=simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
			goodsType.add(goodsTypeList);
		}
		
		model.addAttribute("shopList", shopList);
		model.addAttribute("goods", goods);
		model.addAttribute("goodsType", goodsType);
		
		return "client/userIntegral";
	}
	
	//积分兑换页
	@RequestMapping(value="/client/userCenter/userExchangeRecord.action")
	public String userExchangeRecord(Model model,HttpSession session,String pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		
		//获取全部积分订单
		List<IntegralOrder> integralOrder=userCenterService.getAllIntegralOrderByUserId(userId);
		List<Shop> shopList=new ArrayList<>();
		for(int i=0;i<integralOrder.size();i++) {
			//获取积分商品信息
			Shop shop=userCenterService.getShopById(integralOrder.get(i).getShopId());
			shopList.add(shop);
		}
		List<Goods> goods=new ArrayList<>();
		for(int i=0;i<shopList.size();i++) {
			List<Goods> goodsList=simpleGoodsService.getGoodsById(shopList.get(i).getGoodsId());
			goods.add(goodsList.get(0));
		}
		
		//分页
		int size = integralOrder.size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

		List<IntegralOrder> integralOrderList = new ArrayList<>();
		// 获取分页信息
		Integer page=Integer.parseInt(pageNum);
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			integralOrderList.add(integralOrder.get(i));
		}
		
		
		model.addAttribute("integralOrder", integralOrderList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("size", size);
		model.addAttribute("shopList", shopList);
		model.addAttribute("goods", goods);
		
		return "client/userExchangeRecord";
	}
	
	//积分订单
	@RequestMapping(value="/client/userCenter/userAllIntegralOrder.action")
	public String userAllIntegralOrder(Model model,HttpSession session,String pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		
		//获取全部积分订单
		List<IntegralOrder> integralOrder=userCenterService.getAllIntegralOrderByUserId(userId);
		List<Shop> shopList=new ArrayList<>();
		for(int i=0;i<integralOrder.size();i++) {
			//获取积分商品信息
			Shop shop=userCenterService.getShopById(integralOrder.get(i).getShopId());
			shopList.add(shop);
		}
		List<Goods> goods=new ArrayList<>();
		for(int i=0;i<shopList.size();i++) {
			List<Goods> goodsList=simpleGoodsService.getGoodsById(shopList.get(i).getGoodsId());
			goods.add(goodsList.get(0));
		}
		List<Goodstype> goodsType = new ArrayList<>();
		for (int i = 0; i < goods.size(); i++) {
			Goodstype type = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
			goodsType.add(type);
		}
		
		//分页
		int size = integralOrder.size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

		List<IntegralOrder> integralOrderList = new ArrayList<>();
		// 获取分页信息
		Integer page=Integer.parseInt(pageNum);
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			integralOrderList.add(integralOrder.get(i));
		}
		
		
		model.addAttribute("integralOrder", integralOrderList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("size", size);
		model.addAttribute("shopList", shopList);
		model.addAttribute("goods", goods);
		model.addAttribute("goodsType", goodsType);

		//获取所有积分订单信息
		return "client/userAllIntegralOrder";
	}
	
	//积分订单--待付款
	@RequestMapping(value="/client/userCenter/userIntegralOrderWaitForPayment.action")
	public String userIntegralOrderWaitForPayment(Model model,HttpSession session,String pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		
		//获取待付款积分订单
		List<IntegralOrder> integralOrder=userCenterService.getIntegralOrderByStatus((short)1,userId);
		List<Shop> shopList=new ArrayList<>();
		for(int i=0;i<integralOrder.size();i++) {
			//获取积分商品信息
			Shop shop=userCenterService.getShopById(integralOrder.get(i).getShopId());
			shopList.add(shop);
		}
		List<Goods> goods=new ArrayList<>();
		for(int i=0;i<shopList.size();i++) {
			List<Goods> goodsList=simpleGoodsService.getGoodsById(shopList.get(i).getGoodsId());
			goods.add(goodsList.get(0));
		}
		List<Goodstype> goodsType = new ArrayList<>();
		for (int i = 0; i < goods.size(); i++) {
			Goodstype type = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
			goodsType.add(type);
		}
		
		//分页
		int size = integralOrder.size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

		List<IntegralOrder> integralOrderList = new ArrayList<>();
		// 获取分页信息
		Integer page=Integer.parseInt(pageNum);
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			integralOrderList.add(integralOrder.get(i));
		}
		
		
		model.addAttribute("integralOrder", integralOrderList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("size", size);
		model.addAttribute("shopList", shopList);
		model.addAttribute("goods", goods);
		model.addAttribute("goodsType", goodsType);

		//获取所有积分订单信息
		return "client/userIntegralOrderWaitForPayment";
	}
	
	//积分订单--待收货
	@RequestMapping(value="/client/userCenter/userIntegralOrderWaitForGet.action")
	public String userIntegralOrderWaitForGet(Model model,HttpSession session,String pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		
		//获取待收货积分订单
		List<IntegralOrder> integralOrder=userCenterService.getIntegralOrderByStatus((short)3,userId);
		List<Shop> shopList=new ArrayList<>();
		for(int i=0;i<integralOrder.size();i++) {
			//获取积分商品信息
			Shop shop=userCenterService.getShopById(integralOrder.get(i).getShopId());
			shopList.add(shop);
		}
		List<Goods> goods=new ArrayList<>();
		for(int i=0;i<shopList.size();i++) {
			List<Goods> goodsList=simpleGoodsService.getGoodsById(shopList.get(i).getGoodsId());
			goods.add(goodsList.get(0));
		}
		List<Goodstype> goodsType = new ArrayList<>();
		for (int i = 0; i < goods.size(); i++) {
			Goodstype type = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
			goodsType.add(type);
		}
		
		//分页
		int size = integralOrder.size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

		List<IntegralOrder> integralOrderList = new ArrayList<>();
		// 获取分页信息
		Integer page=Integer.parseInt(pageNum);
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			integralOrderList.add(integralOrder.get(i));
		}
		
		
		model.addAttribute("integralOrder", integralOrderList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("size", size);
		model.addAttribute("shopList", shopList);
		model.addAttribute("goods", goods);
		model.addAttribute("goodsType", goodsType);

		//获取所有积分订单信息
		return "client/userIntegralOrderWaitForGet";
	}
	
	//积分订单--待评价
	@RequestMapping(value="/client/userCenter/userIntegralOrderWaitForComment.action")
	public String userIntegralOrderWaitForComment(Model model,HttpSession session,String pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		
		//获取待评价积分订单
		List<IntegralOrder> integralOrder=userCenterService.getIntegralOrderByStatus((short)5,userId);
		List<Shop> shopList=new ArrayList<>();
		for(int i=0;i<integralOrder.size();i++) {
			//获取积分商品信息
			Shop shop=userCenterService.getShopById(integralOrder.get(i).getShopId());
			shopList.add(shop);
		}
		List<Goods> goods=new ArrayList<>();
		for(int i=0;i<shopList.size();i++) {
			List<Goods> goodsList=simpleGoodsService.getGoodsById(shopList.get(i).getGoodsId());
			goods.add(goodsList.get(0));
		}
		List<Goodstype> goodsType = new ArrayList<>();
		for (int i = 0; i < goods.size(); i++) {
			Goodstype type = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
			goodsType.add(type);
		}
		
		//分页
		int size = integralOrder.size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

		List<IntegralOrder> integralOrderList = new ArrayList<>();
		// 获取分页信息
		Integer page=Integer.parseInt(pageNum);
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			integralOrderList.add(integralOrder.get(i));
		}
		
		
		model.addAttribute("integralOrder", integralOrderList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("size", size);
		model.addAttribute("shopList", shopList);
		model.addAttribute("goods", goods);
		model.addAttribute("goodsType", goodsType);

		//获取所有积分订单信息
		return "client/userIntegralOrderWaitForComment";
	}
	
	//给积分订单对象去重
	private static List<IntegralOrder> removeDuplicateOrder(List<IntegralOrder> orderList) {
        Set<IntegralOrder> set = new TreeSet<IntegralOrder>(new Comparator<IntegralOrder>() {
            @Override
            public int compare(IntegralOrder a, IntegralOrder b) {
                // 字符串则按照asicc码升序排列
                return a.getIntegralOrderId().compareTo(b.getIntegralOrderId());
            }
        });
        
        set.addAll(orderList);
        return new ArrayList<IntegralOrder>(set);
    }
	//积分订单搜索框搜索
	@RequestMapping(value="/client/userCenter/userIntegralOrderBySearch.action")
	public String userIntegralOrderBySearch(Model model,HttpSession session,String pageNum,String search) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		
		// 如果输入为空
		if (search == "") {
			model.addAttribute("size", 0);
				
			return "client/userAllIntegralOrder";
		} else {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(search);
			// 根据订单号查询
			if (isNum.matches()) {
				Integer orderId = Integer.parseInt(search);
				// 判断订单号是否存在
				List<IntegralOrder> integralOrder=userCenterService.getIntegralOrderById(orderId);
				//如果找不到
				if (integralOrder.size()==0) {
					model.addAttribute("size", 0);
	
					return "client/userAllIntegralOrder";
				} else {
					List<Shop> shopList=new ArrayList<>();
					List<Goods> goods=new ArrayList<>();
					for(int i=0;i<integralOrder.size();i++) {
						//获取积分商品信息
						Shop shop=userCenterService.getShopById(integralOrder.get(i).getShopId());
						shopList.add(shop);
					}
					for(int i=0;i<shopList.size();i++) {
						List<Goods> goodsList=simpleGoodsService.getGoodsById(shopList.get(i).getGoodsId());
						goods.add(goodsList.get(0));
					}
					List<Goodstype> goodsType = new ArrayList<>();
					for (int i = 0; i < goods.size(); i++) {
						Goodstype type = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
						goodsType.add(type);
					}
					
					model.addAttribute("shopList", shopList);
					model.addAttribute("goods", goods);
					model.addAttribute("goodsType", goodsType);
					model.addAttribute("size", 1);
					model.addAttribute("integralOrder", integralOrder);
					return "client/userAllIntegralOrder";
				}
			}
			
			//模糊查询
			else {
				//获取全部订单
				List<IntegralOrder> integralOrderList=userCenterService.getAllIntegralOrderByUserId(userId);
				List<Shop> shopList=new ArrayList<>();
				List<Shop> shopListForSearch=new ArrayList<>();
				
				List<Goods> goods=new ArrayList<>();
				List<Goods> goodsForSearch=new ArrayList<>();
				List<Goodstype> goodsType=new ArrayList<>();
				for(int i=0;i<integralOrderList.size();i++) {
					//获取积分商品信息
					Shop shop=userCenterService.getShopById(integralOrderList.get(i).getShopId());
					shopList.add(shop);
				}
				for(int i=0;i<shopList.size();i++) {
					List<Goods> goodsList=simpleGoodsService.getGoodsById(shopList.get(i).getGoodsId());
					goods.add(goodsList.get(0));
				}
				for (int i = 0; i < goods.size(); i++) {
					//如果匹配得到
					if(goods.get(i).getGoodsName().contains(search)) {
						goodsForSearch.add(goods.get(i));
					}
				}
				//如果查询结果为0
				if(goodsForSearch.size()==0) {
					model.addAttribute("size", 0);
					return "client/userAllIntegralOrder";
				}else {
					for (int i = 0; i < goodsForSearch.size(); i++) {
						Goodstype type = simpleGoodsService.getGoodsType(goodsForSearch.get(i).getGoodsType());
						goodsType.add(type);
						//通过商品id获取积分商品信息
						List<Shop> shopListByGoodsId=userCenterService.getShopByGoodsId(goodsForSearch.get(i).getGoodsId());
						shopListForSearch.add(shopListByGoodsId.get(0));
					}
					//通过积分商城的id找到订单信息
					List<IntegralOrder> integralOrderForSearch=new ArrayList<>();
					
					//三个都是一样的
					for(int i=0;i<shopListForSearch.size();i++) {
						//获取查询出来的积分订单
						List<IntegralOrder> integralOrder=userCenterService.getIntegralOrderByShopId(shopListForSearch.get(i).getShopId(),userId);
						for(int j=0;j<integralOrder.size();j++) {
							integralOrderForSearch.add(integralOrder.get(j));
							System.out.println("time:"+integralOrder.get(j).getOrderTime());
						}
					}
					
					//去重
					List<IntegralOrder> dataList = removeDuplicateOrder(integralOrderForSearch);
					//分页
					int size = dataList.size();
					System.out.println("size:"+size);
					// 页数
					int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;

					List<IntegralOrder> integralOrderListForPage = new ArrayList<>();
					
					// 获取分页信息
					Integer page=Integer.parseInt(pageNum);
					PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
					// 结束索引
					int end = pageBean.getStartIndex() + 7;
					if (end > size) {
						end = size;
					}
					for (int i = pageBean.getStartIndex(); i < end; i++) {
						integralOrderListForPage.add(dataList.get(i));
					}
					
					model.addAttribute("shopList", shopListForSearch);
					model.addAttribute("goods", goodsForSearch);
					model.addAttribute("integralOrder", integralOrderListForPage);
					model.addAttribute("size", integralOrderListForPage.size());
					model.addAttribute("goodsType", goodsType);
					model.addAttribute("pageBean", pageBean);
					model.addAttribute("pageSize", pageSize);
				}
				
			}
				
		}
		return "client/userAllIntegralOrder";
	}
	
	//设置中心
	@RequestMapping(value="/client/userCenter/userSetting.action")
	public String userSetting(Model model,HttpSession session) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		
		model.addAttribute("userinfo", userinfo);
		return "client/userSetting";
	}
	
	//修改地址
	@RequestMapping(value="/client/userCenter/userSettingAddress.action")
	public String userSettingAddress(Model model,HttpSession session) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		
		model.addAttribute("userinfo", userinfo);
		return "client/userSettingAddress";
	}
	
	//保存地址
	@RequestMapping(value="/client/userCenter/userUpdateAddress.action")
	public String userUpdateAddress(Model model,HttpSession session,Userinfo user) {
		// 获取登录账户的session
		Integer userId=user.getUserId();  
		
		//保存地址
		user.setUserId(userId);
		userCenterService.updateAddress(user);
		return "redirect:/client/userCenter/userSetting.action";
	}
	
	//修改信息
	@RequestMapping(value="/client/userCenter/userSettingInfo.action")
	public String userSettingInfo(Model model,HttpSession session) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		
		model.addAttribute("userinfo", userinfo);
		return "client/userSettingInfo";
	}
	
	//保存信息
	@RequestMapping(value="/client/userCenter/userUpdateInfo.action")
	public String userUpdateInfo(Model model,HttpSession session,Userinfo user) {
		Integer userId=user.getUserId();  
		//保存信息
		user.setUserId(userId);
		userCenterService.updateInfo(user);
		return "redirect:/client/userCenter/userSetting.action";
	}
	
	//修改密码
	@RequestMapping(value="/client/userCenter/userSettingPass.action")
	public String userSettingPass(Model model,HttpSession session) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		
		model.addAttribute("userinfo", userinfo);
		return "client/userSettingPass";
	}
	
	//校验密码并保存密码
	@RequestMapping(value="/client/userCenter/userUpdatePass.action")
	public String userUpdatePass(Model model,HttpSession session,String nowPass,String newPass) {
		// 获取登录账户的session
		Userinfo user1 = (Userinfo) session.getAttribute("USER");
		Integer userId=user1.getUserId();  
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		//判断密码是否正确
		//获取当前的账户
		List<Userinfo> user=userValidateService.selectUserByPhoneAndPassword(userinfo.getUserPhone(), nowPass);
		if(user.size()==0) {
			model.addAttribute("userinfo", userinfo);
			model.addAttribute("mess", "密码输入有误，请重新输入");
			return "client/userSettingPass";
		}
		
		//保存密码
		userCenterService.updatePass(newPass,userId);
		return "redirect:/client/userCenter/userSetting.action";
	}
	
	//修改手机号
	@RequestMapping(value="/client/userCenter/userSettingPhone.action")
	public String userSettingPhone(Model model,HttpSession session) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		// 获取用户信息
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		
		model.addAttribute("userinfo", userinfo);
		return "client/userSettingPhone";
	}
	
	//检验并保存手机号码
	@RequestMapping(value="/client/userCenter/userUpdatePhone.action")
	public String userUpdatePhone(Model model,HttpSession session,String code,String checkCode,String phone) {
		// 获取登录账户的session
		Userinfo user1 = (Userinfo) session.getAttribute("USER");
		Integer userId=user1.getUserId();  
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		
		//检验验证码
		if(!code.equals(checkCode)) {
			model.addAttribute("messCode", "您的验证码有误，请重新输入");
			model.addAttribute("userinfo", userinfo);
			return "client/userSettingPhone";
		}
		//检验手机号码是否已存在
		List<Userinfo> user=userValidateService.selectUserByPhone(phone);
		if(user.size()!=0) {
			model.addAttribute("messPhone","手机号已存在");
			model.addAttribute("userinfo", userinfo);
			return "client/userSettingPhone";
		}
		
		//保存手机号
		userCenterService.updatePhone(userId,phone);
		return "redirect:/client/userCenter/userSetting.action";
	}
	
	//保存头像
	@RequestMapping(value="/client/userCenter/userSavePicture.action")
	public String userSavePicture(Model model,HttpSession session,HttpServletRequest request,MultipartFile pictrueFile) throws Exception {
		// 获取登录账户的session
		Userinfo user = (Userinfo) session.getAttribute("USER");
		Integer userId=user.getUserId();
		if(pictrueFile==null) {
			model.addAttribute("picMess", "图片为空");
			return "redirect:/client/userCenter/userSetting.action";
		}
		
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		
		
		//32位随机字符
		String name=UUID.randomUUID().toString().replaceAll("-", "");
		//jpg,自动获取原始的后缀名字
		String ext =FilenameUtils.getExtension(pictrueFile.getOriginalFilename());
		String picName=name+"."+ext;
		//获取项目跟路径
        String filePath = request.getServletContext().getRealPath("/");
        
        //获取项目名
        String projectName = request.getContextPath();
        //将项目跟路劲下的项目名称置为空，因为图片需要在项目外的webapp下面存放,sub截取下标为1的字符
        filePath=filePath.replace(projectName.substring(1),"");
        //删除原来图片
        new File(filePath+projectName+"\\images\\用户头像\\"+userinfo.getUserPhoto()).delete();//删除原先的图片 
        
        //上传新的图片
		pictrueFile.transferTo(new File(filePath+projectName+"\\images\\用户头像\\"+picName));
		
		//保存图片信息
		userCenterService.savePic(picName,userId);
		
		//重定向 
		return "redirect:/client/userCenter/userSetting.action";
	}
}
