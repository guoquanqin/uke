package com.client.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.client.entity.Basket;
import com.client.entity.Collect;
import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralFeedbackComment;
import com.client.entity.IntegralOrder;
import com.client.entity.OrderGoods;
import com.client.entity.OrderUser;
import com.client.entity.Orders;
import com.client.entity.PageBeanInOrder;
import com.client.entity.Shop;
import com.client.entity.Userinfo;
import com.client.service.DetailService;
import com.client.service.SimpleGoodsService;
import com.client.service.UserCenterService;
import com.client.service.UserValidateService;
import com.management.entity.Store;
import com.management.service.SysManagementService;

@Controller
public class DetailController {
	@Autowired
	private SimpleGoodsService simpleGoodsService;
	@Autowired
	private DetailService detailGoodsService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private SysManagementService sysManagementService;

	// 支付界面
	@RequestMapping(value = "/client/payment.action")
	public String payment(Model model, HttpSession httpSession, String goodsId, String number, String size) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();

		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);

		Integer id = Integer.parseInt(goodsId);
		Integer num = Integer.parseInt(number);
		List<Goods> goodsById = simpleGoodsService.getGoodsById(id);
		Goodstype goodsType = simpleGoodsService.getGoodsType(goodsById.get(0).getGoodsType());
		System.out.println("size:" + size);

		//获取店铺设置的内容
		Store store=sysManagementService.getStore();
				
		model.addAttribute("store",store);
		model.addAttribute("size", size);
		model.addAttribute("goodsList", goodsById.get(0));
		model.addAttribute("goodsType", goodsType);
		model.addAttribute("number", num);
		model.addAttribute("userinfo", userinfo);

		return "client/payment1";
	}

	// 购物车支付界面
	@RequestMapping(value = "/client/paymentForBasket.action")
	public String paymentForBasket(Model model, HttpSession httpSession, HttpServletRequest request) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);

		List<Goods> goodsList = new ArrayList<>();
		List<Basket> BasketList = new ArrayList<>();
		List<Goodstype> goodsTypeList = new ArrayList<>();
		String[] checkbox = request.getParameterValues("checkbox");
		for (int i = 0; i < checkbox.length; i++) {
			// 获取购物车
			Basket basket = detailGoodsService.getBasketById(checkbox[i]);
			BasketList.add(basket);
			List<Goods> goodsById = simpleGoodsService.getGoodsById(basket.getGoodsId());
			goodsList.add(goodsById.get(0));
			Goodstype goodsType = simpleGoodsService.getGoodsType(goodsById.get(0).getGoodsType());
			goodsTypeList.add(goodsType);
		}

		//获取店铺设置的内容
		Store store=sysManagementService.getStore();
				
		model.addAttribute("store",store);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("BasketList", BasketList);
		model.addAttribute("goodsTypeList", goodsTypeList);
		model.addAttribute("userinfo", userinfo);

		return "client/paymentForBasket";
	}

	// 删除订单
	@RequestMapping(value = "/client/deleteOrder.action")
	public String deleteOrder(HttpSession httpSession, String orderUserId) {
		// 将订单状态置为0
		Integer id = Integer.parseInt(orderUserId);
		detailGoodsService.setOrderStatus(id);
		return "redirect:/client/userCenter/userAllOrder.action?pageNum=1";
	}

	// 删除积分订单
	@RequestMapping(value = "/client/deleteIntegralOrder.action")
	public String deleteIntegralOrder(HttpSession httpSession, String integralOrderId) {
		// 将订单状态置为0
		Integer id = Integer.parseInt(integralOrderId);
		detailGoodsService.setIntegralOrderStatus(id);
		return "redirect:/client/userCenter/userAllIntegralOrder.action?pageNum=1";
	}

	// 删除购物车
	@RequestMapping(value = "/client/deleteBasket.action")
	public String deleteBasket(String basketId) {
		// 将购物车状态置为0
		Integer id = Integer.parseInt(basketId);
		detailGoodsService.setBasketStatus(id);
		return "redirect:/client/userCenter/userBasket.action";
	}

	// 删除部分购物车
	@RequestMapping(value = "/client/deletePartBasket.action")
	public String deletePartBasket(String[] basketId) {
		// 将购物车状态置为0
		for (int i = 0; i < basketId.length; i++) {
			Integer id = Integer.parseInt(basketId[i]);
			detailGoodsService.setBasketStatus(id);
		}
		return "redirect:/client/userCenter/userBasket.action";
	}

	// 删除一个收藏夹
	@RequestMapping(value = "/client/deleteCollect.action")
	public String deleteCollect(String collectId) {
		// 将购物车状态置为0
		Integer id = Integer.parseInt(collectId);
		detailGoodsService.deleteCollect(id);
		return "redirect:/client/userCenter/userCollect.action";
	}

	// 删除全部收藏夹
	@RequestMapping(value = "/client/deleteAllCollect.action")
	public String deleteAllCollect(HttpSession httpSession) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();
		detailGoodsService.deleteAllCollect(userId);
		return "redirect:/client/userCenter/userCollect.action";
	}

	// 删除部分收藏夹
	@RequestMapping(value = "/client/deletePartCollect.action")
	public String deletePartCollect(HttpSession httpSession, String[] collectList) {
		for (int i = 0; i < collectList.length; i++) {
			// 逐个删除收藏夹
			detailGoodsService.deleteCollect(Integer.parseInt(collectList[i]));
		}
		return "redirect:/client/userCenter/userCollect.action";
	}

	// 支付中心-积分兑换
	@RequestMapping(value = "/client/integralPayment.action")
	public String integralPayment(Model model, HttpSession httpSession, String shopId) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		Integer id = Integer.parseInt(shopId);
		// 通过id获取积分商品信息
		Shop shop = detailGoodsService.getShopGoodsById(id);
		// 获取商品信息
		List<Goods> goodsById = simpleGoodsService.getGoodsById(shop.getGoodsId());
		Goodstype goodsType = simpleGoodsService.getGoodsType(goodsById.get(0).getGoodsType());

		//获取店铺设置的内容
		Store store=sysManagementService.getStore();
				
		model.addAttribute("store",store);
		model.addAttribute("shop", shop);
		model.addAttribute("goodsById", goodsById.get(0));
		model.addAttribute("goodsType", goodsType);
		model.addAttribute("userinfo", userinfo);

		return "client/integralPayment";
	}

	// 积分付款 完成
	@RequestMapping(value = "/client/integralPaymentComplete.action")
	public String integralPaymentComplete(Model model, HttpSession httpSession, String shopId,
			IntegralOrder integralOrder) throws ParseException {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		IntegralOrder order = new IntegralOrder();
		order.setShopId(Integer.parseInt(shopId));
		order.setUserId(userId);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new Date());
		order.setOrderTime(date);
		order.setOrderStatus((short) 2);
		order.setIsExit("1");

		// 送达时间
		String deliveryTime = integralOrder.getDeliveryTime();
		String address = integralOrder.getAddress();
		order.setDeliveryTime(deliveryTime);
		order.setAddress(address);
		// 保存积分订单
		detailGoodsService.saveIntegralOrder(order);
		// 通过下单时间获取当前订单
		IntegralOrder orderInfo = detailGoodsService.getNowOrderByOrderTime(date);

		// 获取积分商品
		Shop shop = detailGoodsService.getShopGoodsById(Integer.parseInt(shopId));
		// 获取商品信息
		List<Goods> goodsById = simpleGoodsService.getGoodsById(shop.getGoodsId());
		Goodstype goodsType = simpleGoodsService.getGoodsType(goodsById.get(0).getGoodsType());
		
		//减少用户的积分
		user.setUserIntegral(user.getUserIntegral()-shop.getIntegral());
		//更新用户信息
		detailGoodsService.updateUserInfo(user);
		httpSession.removeAttribute("USER");
		//获取用户信息，重新设置session
		Userinfo userById = detailGoodsService.getUserById(user.getUserId());
		httpSession.setAttribute("USER", userById);
		
		
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("shop", shop);
		model.addAttribute("goodsById", goodsById.get(0));
		model.addAttribute("goodsType", goodsType);

		return "client/paymentForIntegralComplete";
	}

	// 订单评价
	@RequestMapping(value = "/client/goodsOrderComment.action")
	public String goodsOrderComment(Model model, HttpSession httpSession, String orderUserId) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();
		Userinfo userinfo = simpleGoodsService.getUserInfoById(userId);
		List<OrderUser> orderUser = userCenterService.getOrderUserByUserIdAndOrderId(userId,
				Integer.parseInt(orderUserId));

		// 获取order
		List<Orders> order = userCenterService.getOrderByOrderUserId(Integer.parseInt(orderUserId));
		List<OrderGoods> orderGoodsList = new ArrayList<>();
		List<Goods> goodsList = new ArrayList<>();
		List<Goodstype> goodsTypeList = new ArrayList<>();
		for (int i = 0; i < order.size(); i++) {
			OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(i).getOrderGoodsId());
			orderGoodsList.add(orderGoods);
			List<Goods> goodsById = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
			Goodstype goodsType = simpleGoodsService.getGoodsType(goodsById.get(0).getGoodsType());

			goodsList.add(goodsById.get(0));
			goodsTypeList.add(goodsType);
		}

		model.addAttribute("orderUser", orderUser.get(0));
		model.addAttribute("orderGoodsList", orderGoodsList);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsTypeList", goodsTypeList);
		return "client/goodsOrderComment";
	}

	// 提交商品订单的评论
	@RequestMapping(value = "/client/saveGoodsOrderComment.action")
	public String saveGoodsOrderComment(Model model, HttpSession httpSession, String orderUser, String comment,
			String count, HttpServletRequest request, MultipartFile pictrueFile1, MultipartFile pictrueFile2,
			MultipartFile pictrueFile3) throws Exception {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();
		Integer orderUserId = Integer.parseInt(orderUser);
		Short countNum = (short) Integer.parseInt(count);
		String picName1 = "";
		String picName2 = "";
		String picName3 = "";

		// 获取项目名
		String projectName = request.getContextPath();
		// 获取项目跟路径
		String filePath = request.getServletContext().getRealPath("/");
		// 将项目跟路劲下的项目名称置为空，因为图片需要在项目外的webapp下面存放,sub截取下标为1的字符
		filePath = filePath.replace(projectName.substring(1), "");

		// 判断图片是否为空
		if (!pictrueFile1.isEmpty()) {
			// 32位随机字符
			String name1 = UUID.randomUUID().toString().replaceAll("-", "");
			// jpg,自动获取原始的后缀名字
			String ext1 = FilenameUtils.getExtension(pictrueFile1.getOriginalFilename());
			picName1 = name1 + "." + ext1;
			// 上传新的图片
			pictrueFile1.transferTo(new File(filePath + projectName + "\\images\\评价图片\\" + picName1));
		}

		// 判断图片是否为空
		if (!pictrueFile2.isEmpty()) {
			// 32位随机字符
			String name2 = UUID.randomUUID().toString().replaceAll("-", "");
			// jpg,自动获取原始的后缀名字
			String ext2 = FilenameUtils.getExtension(pictrueFile2.getOriginalFilename());
			picName2 = name2 + "." + ext2;
			// 上传新的图片
			pictrueFile2.transferTo(new File(filePath + projectName + "\\images\\评价图片\\" + picName2));
		}

		// 判断图片是否为空
		if (!pictrueFile3.isEmpty()) {
			// 32位随机字符
			String name3 = UUID.randomUUID().toString().replaceAll("-", "");
			// jpg,自动获取原始的后缀名字
			String ext3 = FilenameUtils.getExtension(pictrueFile3.getOriginalFilename());
			picName3 = name3 + "." + ext3;
			// 上传新的图片
			pictrueFile3.transferTo(new File(filePath + projectName + "\\images\\评价图片\\" + picName3));
		}

		String commentPhoto = "";
		if (picName1 != "") {
			commentPhoto += picName1 + "/";
		}
		if (picName2 != "") {
			commentPhoto += picName2 + "/";
		}
		if (picName3 != "") {
			commentPhoto += picName3;
		}
		// 保存评价
		detailGoodsService.saveGoodsOrderComment(userId, orderUserId, countNum, comment, commentPhoto);
		return "redirect:/client/userCenter/userAllOrder.action?pageNum=1";
	}

	// 提交积分订单的评论
	@RequestMapping(value = "/client/integralOrderComment.action")
	public String integralOrderComment(Model model, HttpSession httpSession, String integralOrderId) {

		Integer orderId = Integer.parseInt(integralOrderId);
		// 获取积分订单
		List<IntegralOrder> integralOrderById = userCenterService.getIntegralOrderById(orderId);
		// 积分表
		Shop shop = userCenterService.getShopById(integralOrderById.get(0).getShopId());
		// 商品表
		List<Goods> goodsById = simpleGoodsService.getGoodsById(shop.getGoodsId());
		// 类型表
		Goodstype goodsType = simpleGoodsService.getGoodsType(goodsById.get(0).getGoodsType());
		model.addAttribute("integralOrderById", integralOrderById.get(0));
		model.addAttribute("shop", shop);
		model.addAttribute("goodsById", goodsById.get(0));
		model.addAttribute("goodsType", goodsType);
		return "client/integralOrderComment";
	}

	// 提交积分订单的评论
	@RequestMapping(value = "/client/saveIntegralOrderComment.action")
	public String saveIntegralOrderComment(Model model, HttpSession httpSession, String integralOrder, String comment,
			String count, HttpServletRequest request, MultipartFile pictrueFile1, MultipartFile pictrueFile2,
			MultipartFile pictrueFile3) throws Exception {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();
		Integer integralOrderId = Integer.parseInt(integralOrder);
		Short countNum = (short) Integer.parseInt(count);
		String picName1 = "";
		String picName2 = "";
		String picName3 = "";

		// 获取项目名
		String projectName = request.getContextPath();
		// 获取项目跟路径
		String filePath = request.getServletContext().getRealPath("/");
		// 将项目跟路劲下的项目名称置为空，因为图片需要在项目外的webapp下面存放,sub截取下标为1的字符
		filePath = filePath.replace(projectName.substring(1), "");

		// 判断图片是否为空
		if (!pictrueFile1.isEmpty()) {
			// 32位随机字符
			String name1 = UUID.randomUUID().toString().replaceAll("-", "");
			// jpg,自动获取原始的后缀名字
			String ext1 = FilenameUtils.getExtension(pictrueFile1.getOriginalFilename());
			picName1 = name1 + "." + ext1;
			// 上传新的图片
			pictrueFile1.transferTo(new File(filePath + projectName + "\\images\\评价图片\\" + picName1));
		}

		// 判断图片是否为空
		if (!pictrueFile2.isEmpty()) {
			// 32位随机字符
			String name2 = UUID.randomUUID().toString().replaceAll("-", "");
			// jpg,自动获取原始的后缀名字
			String ext2 = FilenameUtils.getExtension(pictrueFile2.getOriginalFilename());
			picName2 = name2 + "." + ext2;
			// 上传新的图片
			pictrueFile2.transferTo(new File(filePath + projectName + "\\images\\评价图片\\" + picName2));
		}

		// 判断图片是否为空
		if (!pictrueFile3.isEmpty()) {
			// 32位随机字符
			String name3 = UUID.randomUUID().toString().replaceAll("-", "");
			// jpg,自动获取原始的后缀名字
			String ext3 = FilenameUtils.getExtension(pictrueFile3.getOriginalFilename());
			picName3 = name3 + "." + ext3;
			// 上传新的图片
			pictrueFile3.transferTo(new File(filePath + projectName + "\\images\\评价图片\\" + picName3));
		}

		String commentPhoto = "";
		if (picName1 != "") {
			commentPhoto += picName1 + "/";
		}
		if (picName2 != "") {
			commentPhoto += picName2 + "/";
		}
		if (picName3 != "") {
			commentPhoto += picName3;
		}
		// 保存评价
		detailGoodsService.saveIntegralOrderComment(userId, integralOrderId, countNum, comment, commentPhoto);
		return "redirect:/client/userCenter/userIntegralComment.action?pageNum=1";
	}

	// 积分评价表
	@RequestMapping(value = "/client/userCenter/userIntegralComment.action")
	public String userIntegralComment(Model model, HttpSession httpSession, String pageNum) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();

		// 获取status为已完成的integral表
		List<IntegralOrder> integralOrderList = userCenterService.getIntegralOrderByUserIdAndStatus(userId, 7);
		List<IntegralComment> integralComment = new ArrayList<>();
		List<Shop> shopList = new ArrayList<>();
		List<Goods> goodsList = new ArrayList<>();
		List<Goodstype> goodsTypeList = new ArrayList<>();
		for (int i = 0; i < integralOrderList.size(); i++) {
			List<IntegralComment> getComment = userCenterService
					.getIntegralCommentById(integralOrderList.get(i).getIntegralOrderId());
			integralComment.add(getComment.get(0));

			// 获取shop
			Shop shop = detailGoodsService.getShopGoodsById(integralOrderList.get(i).getShopId());
			shopList.add(shop);
			// 获取商品信息
			List<Goods> goodsById = simpleGoodsService.getGoodsById(shop.getGoodsId());
			goodsList.add(goodsById.get(0));
			Goodstype goodsType = simpleGoodsService.getGoodsType(goodsById.get(0).getGoodsType());
			goodsTypeList.add(goodsType);

		}

		// 获取评价反馈表
		List<IntegralFeedbackComment> fbComment = userCenterService.getIntegralFeedbackCommentByUserId(userId);
		List<IntegralFeedbackComment> fbCommentList = new ArrayList<>();
		for (int i = 0; i < integralOrderList.size(); i++) {
			IntegralFeedbackComment fb = new IntegralFeedbackComment();
			fb.setIntegralFeedbackComment(null);
			fbCommentList.add(fb);
		}
		for (int i = 0; i < integralOrderList.size(); i++) {
			for (int j = 0; j < fbComment.size(); j++) {
				if (integralOrderList.get(i).getIntegralOrderId() == fbComment.get(j).getIntegralOrderId()) {
					fbCommentList.get(i).setIntegralFeedbackComment(fbComment.get(j));
				}
			}
		}

		// 分页
		int size = integralOrderList.size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;
		Integer page = Integer.parseInt(pageNum);
		// 获取分页信息
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		// 定义存储新的评价表
		List<IntegralOrder> integralOrderListForPage = new ArrayList<>();
		List<IntegralFeedbackComment> fbCommentListForPage = new ArrayList<>();
		List<Goods> goodsListForPage = new ArrayList<>();
		List<Goodstype> goodsTypeListForPage = new ArrayList<>();
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			integralOrderListForPage.add(integralOrderList.get(i));
			fbCommentListForPage.add(fbCommentList.get(i));
			goodsTypeListForPage.add(goodsTypeList.get(i));
			goodsListForPage.add(goodsList.get(i));
		}

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("size", size);
		model.addAttribute("goodsList", goodsListForPage);
		model.addAttribute("goodsTypeList", goodsTypeListForPage);
		model.addAttribute("integralOrderListForPage", integralOrderListForPage);

		model.addAttribute("integralComment", integralComment);
		model.addAttribute("integralOrderList", integralOrderList);
		model.addAttribute("fbCommentListForPage", fbCommentListForPage);

		return "client/userIntegralComment";
	}

	// 收藏商品
	@RequestMapping(value = "/client/collectGoods.action")
	public @ResponseBody String collectGoods(HttpSession httpSession, @RequestBody String goodsId) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();

		Integer goodId = Integer.parseInt(goodsId);
		// 判断是否已收藏
		// 获取收藏商品
		List<Collect> collect = detailGoodsService.selectCollect(userId, goodId);
		// 如果为空，保存收藏
		if (collect.size() == 0) {
			detailGoodsService.saveCollectGoods(userId, goodId);
		}
		// 如果不为空
		else {
			detailGoodsService.setCollectIsExit(userId, goodId, "1");
		}

		return null;
	}

	// 加入购物车
	@RequestMapping(value = "/client/saveBasketGoods.action", method = RequestMethod.POST)
	public @ResponseBody String saveToBasket(HttpSession httpSession, @RequestBody String[] sendData) {
		// 获取登录账户的session
		Userinfo user = (Userinfo) httpSession.getAttribute("USER");
		Integer userId = user.getUserId();
		Integer goodsId = Integer.parseInt(sendData[0]);
		String size = sendData[1];
		Integer count = Integer.parseInt(sendData[2]);
		// 商品表
		List<Goods> goodsById = simpleGoodsService.getGoodsById(goodsId);
		if (goodsById.get(0).getGoodsMiddlePrice() == null) {
			Integer goodTotalPrice = (int) (goodsById.get(0).getGoodsPrice() * count);

			Basket basket = new Basket();
			basket.setUserId(userId);
			basket.setGoodsId(goodsId);
			basket.setGoodsCount(count);
			basket.setBasketTime(new Date());
			basket.setGoodsTotalPrice((float) goodTotalPrice);
			basket.setGoodsSize("");
			basket.setBasketStatus((short) 1);
			// 保存购物车
			detailGoodsService.saveBasketNoSize(basket);
		} else {
			Basket basket = new Basket();
			basket.setUserId(userId);
			basket.setGoodsId(goodsId);
			basket.setGoodsCount(count);
			basket.setBasketTime(new Date());

			float goodTotalPrice = size == "1" ? goodsById.get(0).getGoodsPrice() * count
					: goodsById.get(0).getGoodsMiddlePrice() * count;
			basket.setGoodsTotalPrice(goodTotalPrice);
			basket.setGoodsSize(size);
			basket.setBasketStatus((short) 1);
			// 保存购物车
			detailGoodsService.saveBasketNoSize(basket);
		}

		return sendData[0];
	}

	// 提交积分订单的评论
	@RequestMapping(value = "/client/exit.action")
	public String exit(HttpSession httpSession) {
		httpSession.invalidate();

		return "client/login";
	}

}
