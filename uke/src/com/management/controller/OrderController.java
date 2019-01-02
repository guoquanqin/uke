package com.management.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.client.entity.Goods;
import com.client.entity.IntegralOrder;
import com.client.entity.OrderGoods;
import com.client.entity.OrderUser;
import com.client.entity.Orders;
import com.client.entity.PageBeanInOrder;
import com.client.entity.Shop;
import com.client.entity.Userinfo;
import com.client.service.SimpleGoodsService;
import com.client.service.UserCenterService;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.management.service.GoodsSettingService;
import com.management.service.OrderService;
import com.management.service.PushService;
import com.management.service.PushService2;

@Controller
public class OrderController {
	@Autowired
	private GoodsSettingService goodsSettingService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private SimpleGoodsService simpleGoodsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PushService pushService;
	@Autowired
	private PushService2 pushService2;

	//语音播报
	public void speck(String str) {
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
	    Dispatch sapo = sap.getObject();
	    try {
	        // 音量 0-100
	        sap.setProperty("Volume", new Variant(100));
	        // 语音朗读速度 -10 到 +10
	        sap.setProperty("Rate", new Variant(2));
	        
	        // 执行朗读
	        Dispatch.call(sapo, "Speak", new Variant(str));
 
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        sapo.safeRelease();
	        sap.safeRelease();
	    }
	}
	
	
	// 实时更新订单
	@RequestMapping(value = "/management/getAsyncInfo.action")
	public @ResponseBody 
	DeferredResult<String> selectTimeOrder(HttpSession httpSession) throws Exception{
		//获取订单的条数
		int orderCount = orderService.getOrderCount();
		int count=(int)httpSession.getAttribute("orderCount");
		//暂无订单
		if(orderCount==count) {
			return pushService.getAsyncUpdate();
		}
		//如果有新订单
		else {
			
			// 获取当天日期
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date = formatter.format(new Date());
			// 获取当天已支付订单数量
			Integer orderUserPayment1 = goodsSettingService.getTodayOrderPayment(date);
			// 获取当天已兑换的积分订单数量
			Integer orderUserPayment2 = goodsSettingService.getTodayIntegralOrderPayment(date);
			speck("您有新的优客站订单，请及时处理");
			
			httpSession.setAttribute("orderUserPayment", orderUserPayment1+orderUserPayment2);
			httpSession.setAttribute("orderCount", orderCount);
			
			return pushService2.getAsyncUpdate();
			
		}
	}
	
	public List<OrderUser> getOrderUserListByStatus(short status) throws Exception {
		List<OrderUser> orderUserList = goodsSettingService.getOrderUserByOrderStatus(status);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = formatter.format(new Date());
		// 获取状态为2（已支付）的积分订单
		List<IntegralOrder> integralOrderList = goodsSettingService.getIntegralOrderByOrderStatus(status);
		for (int i = 0; i < integralOrderList.size(); i++) {
			// 查询积分商品
			Shop shop = userCenterService.getShopById(integralOrderList.get(i).getShopId());
			// 通过shop查询商品信息
			Goods goodsById = orderService.getGoodsById(shop.getGoodsId());

			List<OrderGoods> orderGoodsList = new ArrayList<>();
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setGoods(goodsById);
			orderGoodsList.add(orderGoods);

			OrderUser orderUserBean = new OrderUser();
			orderUserBean.setAddress(integralOrderList.get(i).getAddress() + "/" + goodsById.getGoodsName());

			Date deliveryTime = formatter2.parse(integralOrderList.get(i).getDeliveryTime());
			orderUserBean.setDeliveryTime(deliveryTime);
			orderUserBean.setOrderPrice(Float.valueOf(shop.getIntegral()));

			Date orderTime = formatter2.parse(integralOrderList.get(i).getOrderTime());
			orderUserBean.setOrderTime(orderTime);
			orderUserBean.setIsExit("2");
			orderUserBean.setOrderUserId(integralOrderList.get(i).getIntegralOrderId());
			orderUserBean.setUserId(integralOrderList.get(i).getUserId());

			orderUserBean.setOrderGoodsList(orderGoodsList);
			orderUserList.add(orderUserBean);
		}

		return orderUserList;
	}

	// 订单详情-新订单
	@RequestMapping(value = "/management/orderSearch.action")
	public String orderSearch(Model model, String pageNum) throws Exception {

		// 查询订单状态为2（已支付）的订单
		Short status = 2;
		List<OrderUser> orderUserList = getOrderUserListByStatus(status);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		// 获取状态为2（已支付）的积分订单
		List<IntegralOrder> integralOrderList = goodsSettingService.getIntegralOrderByOrderStatus(status);
		if (orderUserList.size() == 0) {
			model.addAttribute("today", today);
			model.addAttribute("size", 0);
			return "management/orderSearch";
		} else {

			for (int i = 0; i < orderUserList.size() - integralOrderList.size(); i++) {
				List<OrderGoods> orderGoodsList = new ArrayList<>();
				List<Orders> orderByOrderUserId = userCenterService
						.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
				for (int j = 0; j < orderByOrderUserId.size(); j++) {
					OrderGoods orderGoods = userCenterService
							.getOrderGoodsByOrderId(orderByOrderUserId.get(j).getOrderGoodsId());
					Goods goodsById = orderService.getGoodsById(orderGoods.getGoodsId());
					orderGoods.setGoods(goodsById);
					orderGoodsList.add(orderGoods);
				}
				orderUserList.get(i).setOrderGoodsList(orderGoodsList);
			}

			// 获取用户信息
			List<Userinfo> userInfoList = new ArrayList<>();
			for (int i = 0; i < orderUserList.size(); i++) {
				// 根据id找userinfo
				Userinfo userinfo = simpleGoodsService.getUserInfoById(orderUserList.get(i).getUserId());
				userInfoList.add(userinfo);
			}

			// 分页
			int size = orderUserList.size();
			// 页数
			int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;

			List<OrderUser> orderUserListForPage = new ArrayList<>();
			List<Userinfo> userInfoListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 5;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				orderUserListForPage.add(orderUserList.get(i));
				userInfoListForPage.add(userInfoList.get(i));
			}

			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("userInfoListForPage", userInfoListForPage);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("size", size);
			model.addAttribute("today", today);
			return "management/orderSearch";
		}
	}

	// 接单
	@RequestMapping(value = "/management/receiveOrder.action")
	public String receiveOrder(HttpSession session, String id) {
		Integer orderUserId = Integer.parseInt(id);
		// 设置订单状态
		short status = 8;
		int count=(int)session.getAttribute("orderUserPayment");
		session.setAttribute("orderUserPayment", count-1);
		orderService.setOrderUserStatusByOrderUserId(orderUserId, status);
		return "redirect:/management/orderSearch.action?pageNum=1";
	}

	// 接单(积分订单)
	@RequestMapping(value = "/management/receiveIntegralOrder.action")
	public String receiveIntegralOrder(HttpSession session, String id) {
		Integer IntegralOrderId = Integer.parseInt(id);
		// 设置订单状态
		short status = 8;
		int count=(int)session.getAttribute("orderUserPayment");
		session.setAttribute("orderUserPayment", count-1);
		orderService.setOrderStatusByIntegralOrderId(IntegralOrderId, status);
		return "redirect:/management/orderSearch.action?pageNum=1";
	}

	// 一键接单
	@RequestMapping(value = "/management/receiveAllOrder.action")
	public String receiveAllOrder(HttpSession session) {
		// 查询订单状态为2（已支付）的订单
		Short status = 2;
		List<OrderUser> orderUserList = goodsSettingService.getOrderUserByOrderStatus(status);
		// 获取状态为2（已支付）的积分订单
		List<IntegralOrder> integralOrderList = goodsSettingService.getIntegralOrderByOrderStatus(status);

		for (int i = 0; i < integralOrderList.size(); i++) {
			// 设置订单状态
			short status2 = 8;
			orderService.setOrderStatusByIntegralOrderId(integralOrderList.get(i).getIntegralOrderId(), status2);
		}
		for (int i = 0; i < orderUserList.size(); i++) {
			// 设置订单状态
			short status2 = 8;
			orderService.setOrderUserStatusByOrderUserId(orderUserList.get(i).getOrderUserId(), status2);
		}
		session.setAttribute("orderUserPayment", 0);
		
		return "redirect:/management/orderSearch.action?pageNum=1";
	}

	// 拒单
	@RequestMapping(value = "/management/refuseOrder.action")
	public String refuseOrder(Model model, String id) {
		Integer orderUserId = Integer.parseInt(id);
		// 设置订单状态
		short status = 9;
		orderService.setOrderUserStatusByOrderUserId(orderUserId, status);
		return "redirect:/management/orderSearch.action?pageNum=1";
	}

	// 拒单(积分订单)
	@RequestMapping(value = "/management/refuseIntegralOrder.action")
	public String refuseIntegralOrder(Model model, String id) {
		Integer orderUserId = Integer.parseInt(id);
		// 设置订单状态
		short status = 9;
		orderService.setOrderStatusByIntegralOrderId(orderUserId, status);
		return "redirect:/management/orderSearch.action?pageNum=1";
	}

	// 订单详情-已接收订单
	@RequestMapping(value = "/management/orderSearchForRecevied.action")
	public String orderSearchForRecevied(Model model, String pageNum) throws Exception {

		// 查询订单状态为8（已接收）的订单
		Short status = 8;
		List<OrderUser> orderUserList = getOrderUserListByStatus(status);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		// 获取状态为8（已接收）的积分订单
		List<IntegralOrder> integralOrderList = goodsSettingService.getIntegralOrderByOrderStatus(status);

		if (orderUserList.size() == 0) {
			model.addAttribute("size", 0);
			model.addAttribute("today", today);
			return "management/orderSearchForRecevied";
		} else {

			for (int i = 0; i < orderUserList.size() - integralOrderList.size(); i++) {
				List<OrderGoods> orderGoodsList = new ArrayList<>();
				List<Orders> orderByOrderUserId = userCenterService
						.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
				for (int j = 0; j < orderByOrderUserId.size(); j++) {
					OrderGoods orderGoods = userCenterService
							.getOrderGoodsByOrderId(orderByOrderUserId.get(j).getOrderGoodsId());
					Goods goodsById = orderService.getGoodsById(orderGoods.getGoodsId());
					orderGoods.setGoods(goodsById);
					orderGoodsList.add(orderGoods);
				}

				orderUserList.get(i).setOrderGoodsList(orderGoodsList);
			}

			// 获取用户信息
			List<Userinfo> userInfoList = new ArrayList<>();
			for (int i = 0; i < orderUserList.size(); i++) {
				// 根据id找userinfo
				Userinfo userinfo = simpleGoodsService.getUserInfoById(orderUserList.get(i).getUserId());
				userInfoList.add(userinfo);
			}

			// 分页
			int size = orderUserList.size();
			// 页数
			int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;

			List<OrderUser> orderUserListForPage = new ArrayList<>();
			List<Userinfo> userInfoListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 5;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				orderUserListForPage.add(orderUserList.get(i));
				userInfoListForPage.add(userInfoList.get(i));
			}

			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("userInfoListForPage", userInfoListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("today", today);
			return "management/orderSearchForRecevied";
		}
	}

	// 配送
	@RequestMapping(value = "/management/deliveryOrder.action")
	public String deliveryOrder(Model model, String id) {
		Integer orderUserId = Integer.parseInt(id);
		// 设置订单状态
		short status = 3;
		orderService.setOrderUserStatusByOrderUserId(orderUserId, status);
		return "redirect:/management/orderSearchForRecevied.action?pageNum=1";
	}

	// 配送(积分订单）
	@RequestMapping(value = "/management/deliveryIntegralOrder.action")
	public String deliveryIntegralOrder(Model model, String id) {
		Integer orderUserId = Integer.parseInt(id);
		// 设置订单状态
		short status = 3;
		orderService.setOrderStatusByIntegralOrderId(orderUserId, status);
		return "redirect:/management/orderSearchForRecevied.action?pageNum=1";
	}

	// 一键配送
	@RequestMapping(value = "/management/deliveryAllOrder.action")
	public String deliveryAllOrder(Model model) {
		// 查询订单状态为8（已接收）的订单
		Short status = 8;
		List<OrderUser> orderUserList = goodsSettingService.getOrderUserByOrderStatus(status);
		for (int i = 0; i < orderUserList.size(); i++) {
			// 设置订单状态
			short status2 = 3;
			orderService.setOrderUserStatusByOrderUserId(orderUserList.get(i).getOrderUserId(), status2);
		}
		List<IntegralOrder> integralOrderList = goodsSettingService.getIntegralOrderByOrderStatus(status);
		for (int i = 0; i < integralOrderList.size(); i++) {
			// 设置订单状态
			short status2 = 3;
			orderService.setOrderStatusByIntegralOrderId(integralOrderList.get(i).getIntegralOrderId(), status2);
		}
		return "redirect:/management/orderSearchForRecevied.action?pageNum=1";
	}

	// 订单详情-正在配送
	@RequestMapping(value = "/management/orderSearchForDelivery.action")
	public String orderSearchForDelivery(Model model, String pageNum) throws Exception {

		// 查询订单状态为3（正在配送）的订单
		Short status = 3;
		List<OrderUser> orderUserList = getOrderUserListByStatus(status);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		// 获取状态为3（正在配送）的积分订单
		List<IntegralOrder> integralOrderList = goodsSettingService.getIntegralOrderByOrderStatus(status);

		if (orderUserList.size() == 0) {
			model.addAttribute("today", today);
			model.addAttribute("size", 0);
			return "management/orderSearchForDelivery";
		} else {

			for (int i = 0; i < orderUserList.size() - integralOrderList.size(); i++) {
				List<OrderGoods> orderGoodsList = new ArrayList<>();
				List<Orders> orderByOrderUserId = userCenterService
						.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
				for (int j = 0; j < orderByOrderUserId.size(); j++) {
					OrderGoods orderGoods = userCenterService
							.getOrderGoodsByOrderId(orderByOrderUserId.get(j).getOrderGoodsId());
					Goods goodsById = orderService.getGoodsById(orderGoods.getGoodsId());
					orderGoods.setGoods(goodsById);
					orderGoodsList.add(orderGoods);
				}

				orderUserList.get(i).setOrderGoodsList(orderGoodsList);
			}

			// 获取用户信息
			List<Userinfo> userInfoList = new ArrayList<>();
			for (int i = 0; i < orderUserList.size(); i++) {
				// 根据id找userinfo
				Userinfo userinfo = simpleGoodsService.getUserInfoById(orderUserList.get(i).getUserId());
				userInfoList.add(userinfo);
			}

			// 分页
			int size = orderUserList.size();
			// 页数
			int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;

			List<OrderUser> orderUserListForPage = new ArrayList<>();
			List<Userinfo> userInfoListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 5;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				orderUserListForPage.add(orderUserList.get(i));
				userInfoListForPage.add(userInfoList.get(i));
			}

			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("userInfoListForPage", userInfoListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("today", today);
			return "management/orderSearchForDelivery";
		}
	}

	// 送达
	@RequestMapping(value = "/management/finishOrder.action")
	public String finishOrder(Model model, String id) {
		Integer orderUserId = Integer.parseInt(id);
		// 设置订单状态
		short status = 4;
		orderService.setOrderUserStatusByOrderUserId(orderUserId, status);
		return "redirect:/management/orderSearchForDelivery.action?pageNum=1";
	}

	// 送达（积分订单）
	@RequestMapping(value = "/management/finishIntegralOrder.action")
	public String finishIntegralOrder(Model model, String id) {
		Integer orderUserId = Integer.parseInt(id);
		// 设置订单状态
		short status = 4;
		orderService.setOrderStatusByIntegralOrderId(orderUserId, status);
		return "redirect:/management/orderSearchForDelivery.action?pageNum=1";
	}

	// 一键配送
	@RequestMapping(value = "/management/finishAllOrder.action")
	public String finishAllOrder(Model model) {
		// 查询订单状态为3（正在配送）的订单
		Short status = 3;
		List<OrderUser> orderUserList = goodsSettingService.getOrderUserByOrderStatus(status);
		for (int i = 0; i < orderUserList.size(); i++) {
			// 设置订单状态
			short status2 = 4;
			orderService.setOrderUserStatusByOrderUserId(orderUserList.get(i).getOrderUserId(), status2);
		}
		List<IntegralOrder> integralOrderList = goodsSettingService.getIntegralOrderByOrderStatus(status);
		for (int i = 0; i < integralOrderList.size(); i++) {
			// 设置订单状态
			short status2 = 4;
			orderService.setOrderStatusByIntegralOrderId(integralOrderList.get(i).getIntegralOrderId(), status2);
		}
		return "redirect:/management/orderSearchForDelivery.action?pageNum=1";
	}

	// 订单详情-已收货
	@RequestMapping(value = "/management/orderSearchForFinish.action")
	public String orderSearchForFinish(Model model, String pageNum) throws Exception {

		// 查询订单状态为4（正在配送）的订单
		Short status = 4;
		List<OrderUser> orderUserList = getOrderUserListByStatus(status);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		// 获取状态为4（正在配送）的积分订单
		List<IntegralOrder> integralOrderList = goodsSettingService.getIntegralOrderByOrderStatus(status);

		if (orderUserList.size() == 0) {
			model.addAttribute("today", today);
			model.addAttribute("size", 0);
			return "management/orderSearchForDelivery";
		} else {

			for (int i = 0; i < orderUserList.size() - integralOrderList.size(); i++) {
				List<OrderGoods> orderGoodsList = new ArrayList<>();
				List<Orders> orderByOrderUserId = userCenterService
						.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
				for (int j = 0; j < orderByOrderUserId.size(); j++) {
					OrderGoods orderGoods = userCenterService
							.getOrderGoodsByOrderId(orderByOrderUserId.get(j).getOrderGoodsId());
					Goods goodsById = orderService.getGoodsById(orderGoods.getGoodsId());
					orderGoods.setGoods(goodsById);
					orderGoodsList.add(orderGoods);
				}

				orderUserList.get(i).setOrderGoodsList(orderGoodsList);
			}

			// 获取用户信息
			List<Userinfo> userInfoList = new ArrayList<>();
			for (int i = 0; i < orderUserList.size(); i++) {
				// 根据id找userinfo
				Userinfo userinfo = simpleGoodsService.getUserInfoById(orderUserList.get(i).getUserId());
				userInfoList.add(userinfo);
			}

			// 分页
			int size = orderUserList.size();
			// 页数
			int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;

			List<OrderUser> orderUserListForPage = new ArrayList<>();
			List<Userinfo> userInfoListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 5;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				orderUserListForPage.add(orderUserList.get(i));
				userInfoListForPage.add(userInfoList.get(i));
			}

			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("userInfoListForPage", userInfoListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("today", today);
			return "management/orderSearchForFinish";
		}
	}

	public List<OrderUser> getOrderUserList() throws Exception {
		// 查询所有订单（不管删没删）
		List<OrderUser> orderUserList = orderService.getAllOrderUserForHistory();

		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 查询所有的积分订单（不管删没删）
		List<IntegralOrder> integralOrderList = orderService.getAllIntegralOrderForHistory();
		for (int i = 0; i < integralOrderList.size(); i++) {
			// 查询积分商品
			Shop shop = orderService.getShopById(integralOrderList.get(i).getShopId());
			// 通过shop查询商品信息
			Goods goodsById = orderService.getGoodsById(shop.getGoodsId());

			List<OrderGoods> orderGoodsList = new ArrayList<>();
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setGoodsId(goodsById.getGoodsId());
			orderGoods.setGoods(goodsById);
			orderGoodsList.add(orderGoods);

			OrderUser orderUserBean = new OrderUser();
			// 没办法只能把商品名放到地址再截取了
			orderUserBean.setAddress(integralOrderList.get(i).getAddress() + "/" + goodsById.getGoodsName());

			Date deliveryTime = formatter2.parse(integralOrderList.get(i).getDeliveryTime());
			orderUserBean.setDeliveryTime(deliveryTime);
			orderUserBean.setOrderPrice(Float.valueOf(shop.getIntegral()));

			Date orderTime = formatter2.parse(integralOrderList.get(i).getOrderTime());
			orderUserBean.setOrderTime(orderTime);
			orderUserBean.setIsExit("2");
			orderUserBean.setOrderUserId(integralOrderList.get(i).getIntegralOrderId());
			orderUserBean.setUserId(integralOrderList.get(i).getUserId());

			orderUserBean.setOrderGoodsList(orderGoodsList);
			orderUserList.add(orderUserBean);
		}
		return orderUserList;
	}

	// 历史订单
	@RequestMapping(value = "/management/historicalOrder.action")
	public String historicalOrder(Model model, String pageNum) throws Exception {
		List<OrderUser> orderUserList = getOrderUserList();

		if (orderUserList.size() == 0) {
			model.addAttribute("size", 0);
			return "management/historicalOrder";
		} else {
			// 对orderUserList按orderTime排序（降序）
			Collections.sort(orderUserList, new Comparator<OrderUser>() {
				@Override
				public int compare(OrderUser o1, OrderUser o2) {
					// 升序
					return o2.getOrderTime().compareTo(o1.getOrderTime());
				}
			});

			for (int i = 0; i < orderUserList.size(); i++) {
				List<OrderGoods> orderGoodsList = new ArrayList<>();
				List<Orders> orderByOrderUserId = userCenterService
						.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
				for (int j = 0; j < orderByOrderUserId.size(); j++) {
					OrderGoods orderGoods = userCenterService
							.getOrderGoodsByOrderId(orderByOrderUserId.get(j).getOrderGoodsId());
					Goods goodsById = orderService.getGoodsById(orderGoods.getGoodsId());
					orderGoods.setGoods(goodsById);
					orderGoodsList.add(orderGoods);
				}

				orderUserList.get(i).setOrderGoodsList(orderGoodsList);
			}

			// 获取用户信息
			List<Userinfo> userInfoList = new ArrayList<>();
			for (int i = 0; i < orderUserList.size(); i++) {
				// 根据id找userinfo
				Userinfo userinfo = simpleGoodsService.getUserInfoById(orderUserList.get(i).getUserId());
				userInfoList.add(userinfo);
			}

			// 分页
			int size = orderUserList.size();
			// 页数
			int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;

			List<OrderUser> orderUserListForPage = new ArrayList<>();
			List<Userinfo> userInfoListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 5;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				orderUserListForPage.add(orderUserList.get(i));
				userInfoListForPage.add(userInfoList.get(i));
			}

			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("userInfoListForPage", userInfoListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			return "management/historicalOrder";
		}
	}

	// 历史订单(第一个搜素框)
	@RequestMapping(value = "/management/findHistoricalOrderByFirstCondition.action")
	public String findHistoricalOrderByFirstCondition(Model model, String content, String pageNum) throws Exception {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(content);

		List<OrderUser> orderUserList = getOrderUserList();
		// 对orderUserList按orderTime排序（降序）
		Collections.sort(orderUserList, new Comparator<OrderUser>() {
			@Override
			public int compare(OrderUser o1, OrderUser o2) {
				// 升序
				return o2.getOrderTime().compareTo(o1.getOrderTime());
			}
		});

		for (int i = 0; i < orderUserList.size(); i++) {
			List<OrderGoods> orderGoodsList = new ArrayList<>();
			List<Orders> orderByOrderUserId = userCenterService
					.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
			for (int j = 0; j < orderByOrderUserId.size(); j++) {
				OrderGoods orderGoods = userCenterService
						.getOrderGoodsByOrderId(orderByOrderUserId.get(j).getOrderGoodsId());
				Goods goodsById = orderService.getGoodsById(orderGoods.getGoodsId());
				orderGoods.setGoods(goodsById);
				orderGoodsList.add(orderGoods);
			}
			orderUserList.get(i).setOrderGoodsList(orderGoodsList);
		}

		// 获取用户信息
		List<Userinfo> userInfoList = new ArrayList<>();
		for (int i = 0; i < orderUserList.size(); i++) {
			// 根据id找userinfo
			Userinfo userinfo = simpleGoodsService.getUserInfoById(orderUserList.get(i).getUserId());
			userInfoList.add(userinfo);
		}

		// 根据订单号或手机号查找查询
		if (isNum.matches()) {
			Integer id = Integer.parseInt(content);
			if (orderUserList.size() == 0) {
				model.addAttribute("size", 0);
				return "management/historicalOrder";
			} else {

				// 查询订单号
				// 存储查询结果
				List<OrderUser> orderUser = new ArrayList<>();
				for (int i = 0; i < orderUserList.size(); i++) {
					if (id.equals(orderUserList.get(i).getOrderUserId())) {
						orderUser.add(orderUserList.get(i));
					}
				}
				// 按手机号查询
				for (int i = 0; i < userInfoList.size(); i++) {
					String phone = id + "";
					if (phone.equals(userInfoList.get(i).getUserPhone())) {
						orderUser.add(orderUserList.get(i));
					}
				}

				// 分页
				int size = orderUser.size();
				// 页数
				int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;

				List<OrderUser> orderUserListForPage = new ArrayList<>();
				List<Userinfo> userInfoListForPage = new ArrayList<>();
				// 获取分页信息
				Integer page = Integer.parseInt(pageNum);
				PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
				// 结束索引
				int end = pageBean.getStartIndex() + 5;
				if (end > size) {
					end = size;
				}
				for (int i = pageBean.getStartIndex(); i < end; i++) {
					orderUserListForPage.add(orderUser.get(i));
					userInfoListForPage.add(userInfoList.get(i));
				}

				model.addAttribute("orderUserListForPage", orderUserListForPage);
				model.addAttribute("userInfoListForPage", userInfoListForPage);
				model.addAttribute("size", size);
				model.addAttribute("pageBean", pageBean);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("content", content);
				// 查询标识
				model.addAttribute("search1", 1);
				return "management/historicalOrder";
			}

		}
		// 按用户名查询
		else {
			List<OrderUser> orderUser = new ArrayList<>();
			for (int i = 0; i < userInfoList.size(); i++) {
				if (userInfoList.get(i).getUserName().contains(content)) {
					orderUser.add(orderUserList.get(i));
				}
			}
			// 分页
			int size = orderUser.size();
			// 页数
			int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;

			List<OrderUser> orderUserListForPage = new ArrayList<>();
			List<Userinfo> userInfoListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 5;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				orderUserListForPage.add(orderUser.get(i));
				userInfoListForPage.add(userInfoList.get(i));
			}

			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("userInfoListForPage", userInfoListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("content", content);
			// 查询标识
			model.addAttribute("search1", 1);
			return "management/historicalOrder";
		}
	}
	
	
	// 历史订单(第二个搜素框)
	@RequestMapping(value = "/management/findHistoricalOrderBySecondCondition.action")
	public String findHistoricalOrderBySecondCondition(Model model,String pageNum, String datetimepicker1, String datetimepicker2) throws Exception {
		List<OrderUser> orderUserList = getOrderUserList();
		// 对orderUserList按orderTime排序（降序）
		Collections.sort(orderUserList, new Comparator<OrderUser>() {
			@Override
			public int compare(OrderUser o1, OrderUser o2) {
				// 升序
				return o2.getOrderTime().compareTo(o1.getOrderTime());
			}
		});

		for (int i = 0; i < orderUserList.size(); i++) {
			List<OrderGoods> orderGoodsList = new ArrayList<>();
			List<Orders> orderByOrderUserId = userCenterService
					.getOrderByOrderUserId(orderUserList.get(i).getOrderUserId());
			for (int j = 0; j < orderByOrderUserId.size(); j++) {
				OrderGoods orderGoods = userCenterService
						.getOrderGoodsByOrderId(orderByOrderUserId.get(j).getOrderGoodsId());
				Goods goodsById = orderService.getGoodsById(orderGoods.getGoodsId());
				orderGoods.setGoods(goodsById);
				orderGoodsList.add(orderGoods);
			}
			orderUserList.get(i).setOrderGoodsList(orderGoodsList);
		}

		// 获取用户信息
		List<Userinfo> userInfoList = new ArrayList<>();
		for (int i = 0; i < orderUserList.size(); i++) {
			// 根据id找userinfo
			Userinfo userinfo = simpleGoodsService.getUserInfoById(orderUserList.get(i).getUserId());
			userInfoList.add(userinfo);
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(datetimepicker1!=""&&datetimepicker2=="") {
			List<OrderUser> orderUser=new ArrayList<>();
			for(int i=0;i<orderUserList.size();i++) {
				Date date1=formatter.parse(datetimepicker1);
				if(date1.getTime()<=orderUserList.get(i).getOrderTime().getTime()) {
					orderUser.add(orderUserList.get(i));
				}
			}
			
			// 分页
			int size = orderUser.size();
			// 页数
			int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;

			List<OrderUser> orderUserListForPage = new ArrayList<>();
			List<Userinfo> userInfoListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 5;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				orderUserListForPage.add(orderUser.get(i));
				userInfoListForPage.add(userInfoList.get(i));
			}

			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("userInfoListForPage", userInfoListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("datetimepicker1", datetimepicker1);
			// 查询标识
			model.addAttribute("search2", 1);
			return "management/historicalOrder";
			
		}
		if(datetimepicker1==""&&datetimepicker2!="") {
			List<OrderUser> orderUser=new ArrayList<>();
			for(int i=0;i<orderUserList.size();i++) {
				Date date2=formatter.parse(datetimepicker2);
				if(date2.getTime()>=orderUserList.get(i).getOrderTime().getTime()) {
					orderUser.add(orderUserList.get(i));
				}
			}
			
			// 分页
			int size = orderUser.size();
			// 页数
			int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;
			
			List<OrderUser> orderUserListForPage = new ArrayList<>();
			List<Userinfo> userInfoListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 5;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				orderUserListForPage.add(orderUser.get(i));
				userInfoListForPage.add(userInfoList.get(i));
			}
			
			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("userInfoListForPage", userInfoListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("datetimepicker2", datetimepicker2);
			// 查询标识
			model.addAttribute("search3", 1);
			return "management/historicalOrder";
		}
		else {
			List<OrderUser> orderUser=new ArrayList<>();
			for(int i=0;i<orderUserList.size();i++) {
				Date date1=formatter.parse(datetimepicker1);
				Date date2=formatter.parse(datetimepicker2);
				if(date2.getTime()>=orderUserList.get(i).getOrderTime().getTime()&&date1.getTime()<=orderUserList.get(i).getOrderTime().getTime()) {
					orderUser.add(orderUserList.get(i));
				}
			}
			
			// 分页
			int size = orderUser.size();
			// 页数
			int pageSize = size % 5 == 0 ? size / 5 : size / 5 + 1;
			
			List<OrderUser> orderUserListForPage = new ArrayList<>();
			List<Userinfo> userInfoListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 5, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 5;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				orderUserListForPage.add(orderUser.get(i));
				userInfoListForPage.add(userInfoList.get(i));
			}
			
			model.addAttribute("orderUserListForPage", orderUserListForPage);
			model.addAttribute("userInfoListForPage", userInfoListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("datetimepicker1", datetimepicker1);
			model.addAttribute("datetimepicker2", datetimepicker2);
			// 查询标识
			model.addAttribute("search4", 1);
			return "management/historicalOrder";
		}
		
	}
}
