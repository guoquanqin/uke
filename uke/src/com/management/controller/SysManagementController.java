package com.management.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.client.entity.Comment;
import com.client.entity.FeedbackComment;
import com.client.entity.Goods;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralFeedbackComment;
import com.client.entity.IntegralOrder;
import com.client.entity.OrderGoods;
import com.client.entity.OrderUser;
import com.client.entity.Orders;
import com.client.entity.PageBeanInOrder;
import com.client.entity.Shop;
import com.client.entity.Userinfo;
import com.client.service.SimpleGoodsService;
import com.client.service.UserCenterService;
import com.management.entity.Managerinfo;
import com.management.entity.Store;
import com.management.service.ManaValidateService;
import com.management.service.OrderService;
import com.management.service.SysManagementService;
import com.md5Util.MD5Util;

@Controller
public class SysManagementController {
	@Autowired
	private SysManagementService sysManagementService;
	@Autowired
	private ManaValidateService manaValidateService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SimpleGoodsService simpleGoodsService;

	// 添加账户
	@RequestMapping(value = "/management/addManager.action")
	public String addManager(Model model, Managerinfo managerinfo, String code, String realCode) {
		// 验证手机号码存不存在
		List<Managerinfo> userByPhone = manaValidateService.selectUserByPhone(managerinfo.getManagerPhone());
		if (userByPhone.size() != 0) {
			model.addAttribute("phoneMess", "手机号码已存在");
			return "management/addManager";
		}
		if (!code.equals(realCode)) {
			model.addAttribute("codeMess", "验证码不正确");
			return "management/addManager";
		} else {
			// 添加账户
			managerinfo.setManagerPhoto("头像.jpg");
			// 保存
			sysManagementService.saveManager(managerinfo);
			return "management/addManager";
		}
	}

	// 获取现有账户
	@RequestMapping(value = "/management/getAllManager.action")
	public String getAllManager(Model model) {
		// 获取账户
		List<Managerinfo> managerInfo = sysManagementService.getAllManager();
		model.addAttribute("managerInfo", managerInfo);

		return "management/deleteManager";
	}

	// 删除账户
	@RequestMapping(value = "/management/deleteManager.action")
	public String deleteManager(Model model, String managerId, String managerPhone, String code, String realCode) {
		Integer id = Integer.parseInt(managerId);
		List<Managerinfo> managerInfo = sysManagementService.getAllManager();
		// 判断账户是否存在
		List<Managerinfo> userByPhone = manaValidateService.selectUserByPhone(managerPhone);
		if (userByPhone.size() == 0) {
			String phoneMess = "手机号码不存在";
			model.addAttribute("phoneMess", phoneMess);
			model.addAttribute("managerInfo", managerInfo);
			model.addAttribute("managerInfo", managerInfo);
			return "management/deleteManager";
		}
		if (!userByPhone.get(0).getManagerId().equals(id)) {
			String phoneMess = "手机号码不是该账户的";
			model.addAttribute("managerInfo", managerInfo);
			model.addAttribute("phoneMess", phoneMess);
			return "management/deleteManager";
		}
		if (!realCode.equals(code)) {
			String codeMess = "验证码不正确";
			model.addAttribute("managerInfo", managerInfo);
			model.addAttribute("codeMess", codeMess);
			return "management/deleteManager";
		} else {

			// 删除账户
			sysManagementService.deleteManager(id);
			return "redirect:/management/getAllManager.action";
		}
	}

	// 个人信息
	@RequestMapping(value = "/management/personalMessage.action")
	public String personalMessage(Model model, HttpSession session, HttpServletRequest request,
			MultipartFile pictrueFile) throws Exception {
		Managerinfo managerinfo = (Managerinfo) session.getAttribute("MANAGER");
		String picName = "";

		// 获取项目名
		String projectName = request.getContextPath();
		// 获取项目跟路径
		String filePath = request.getServletContext().getRealPath("/");
		// 将项目跟路劲下的项目名称置为空，因为图片需要在项目外的webapp下面存放,sub截取下标为1的字符
		filePath = filePath.replace(projectName.substring(1), "");

		// 判断图片是否为空
		if (!pictrueFile.isEmpty()) {
			// 32位随机字符
			String name1 = UUID.randomUUID().toString().replaceAll("-", "");
			// jpg,自动获取原始的后缀名字
			String ext1 = FilenameUtils.getExtension(pictrueFile.getOriginalFilename());
			picName = name1 + "." + ext1;
			// 上传新的图片
			pictrueFile.transferTo(new File(filePath + projectName + "\\images\\管理员头像\\" + picName));
		}

		// 保存图片
		sysManagementService.saveManagerPhoto(managerinfo.getManagerId(), picName);
		return "management/personalMessage";
	}

	// 修改用户名
	@RequestMapping(value = "/management/alterManaName.action")
	public String alterManaName(Model model, HttpSession session, String newName, String password) {
		Managerinfo managerinfo = (Managerinfo) session.getAttribute("MANAGER");
		// 密码加密
		MD5Util md5 = new MD5Util();
		String md5Password = md5.string2MD5(password);
		if (!managerinfo.getManagerPassword().equals(md5Password)) {
			model.addAttribute("mess1", "密码不正确");
			return "management/alterManaInfo";
		}
		// 保存用户名
		sysManagementService.saveManaName(managerinfo.getManagerId(), newName);
		Managerinfo managerinfo1 = sysManagementService.getManagerById(managerinfo.getManagerId());
		session.removeAttribute("MANAGER");
		session.setAttribute("MANAGER", managerinfo1);

		return "management/personalMessage";
	}

	// 修改密码
	@RequestMapping(value = "/management/alterPassword.action")
	public String alterPassword(Model model, HttpSession session, String oldPassword, String newPassword) {
		Managerinfo managerinfo = (Managerinfo) session.getAttribute("MANAGER");
		// 密码加密
		MD5Util md5 = new MD5Util();
		String md5Password = md5.string2MD5(oldPassword);
		if (!managerinfo.getManagerPassword().equals(md5Password)) {
			model.addAttribute("mess1", "密码不正确");
			return "management/alterPassword";
		}
		// 密码加密
		String newPass = md5.string2MD5(newPassword);
		// 保存密码
		sysManagementService.saveManaPassword(managerinfo.getManagerId(), newPass);

		return "management/personalMessage";
	}

	// 修改手机号
	@RequestMapping(value = "/management/alterManaPhone.action")
	public String alterManaPhone(Model model, HttpSession session, String oldPhone, String newPhone, String code,
			String realCode) {
		Managerinfo managerinfo = (Managerinfo) session.getAttribute("MANAGER");
		// 手机号不一致
		if (!oldPhone.equals(managerinfo.getManagerPhone())) {
			model.addAttribute("mess2", "手机号不一致");
			model.addAttribute("oldPhone", oldPhone);
			model.addAttribute("newPhone", newPhone);
			return "management/alterManaInfo";
		}
		if (oldPhone.equals(newPhone)) {
			model.addAttribute("mess3", "手机号与原始号码相同");
			model.addAttribute("oldPhone", oldPhone);
			model.addAttribute("newPhone", newPhone);
			return "management/alterManaInfo";
		}
		// 检查手机号是否存在
		List<Managerinfo> userByPhone = manaValidateService.selectUserByPhone(newPhone);
		if (oldPhone != newPhone && userByPhone.size() != 0) {
			model.addAttribute("mess3", "手机号已存在");
			model.addAttribute("oldPhone", oldPhone);
			model.addAttribute("newPhone", newPhone);
			return "management/alterManaInfo";
		}
		if (!code.equals(realCode)) {
			model.addAttribute("mess4", "验证码有误");
			model.addAttribute("oldPhone", oldPhone);
			model.addAttribute("newPhone", newPhone);
			return "management/alterManaInfo";
		}
		// 保存手机号
		sysManagementService.saveManaPhone(managerinfo.getManagerId(), newPhone);

		return "management/personalMessage";
	}

	// 评价管理
	@RequestMapping(value = "/management/commentManagement.action")
	public String commentManagement(Model model, String pageNum) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取所有评价表
		List<Comment> allComment = sysManagementService.getAllComment();
		// 获取所有的积分评价表
		List<IntegralComment> allIntegralComment = sysManagementService.getAllIntegralComment();

		Set<String> dateSet1 = new TreeSet<>();
		for (int i = 0; i < allComment.size(); i++) {
			dateSet1.add(formatter.format(allComment.get(i).getCommentTime()));
		}
		for (int i = 0; i < allIntegralComment.size(); i++) {
			dateSet1.add(formatter.format(allIntegralComment.get(i).getCommentTime()));
		}

		// 倒叙排序
		List<String> dateSetToList1 = new ArrayList<>(dateSet1);
		// 最终版
		List<String> dateSetToList = new ArrayList<>();

		for (int i = dateSetToList1.size() - 1; i >= 0; i--) {
			dateSetToList.add(dateSetToList1.get(i));
		}

		List<Comment> commentList = new ArrayList<>();
		for (int i = 0; i < dateSetToList.size(); i++) {
			// 通过dateSetToList找comment
			List<Comment> comment2 = sysManagementService.getCommentByDate(dateSetToList.get(i));
			if (comment2.size() != 0) {
				Comment comment = comment2.get(0);
				// 查找用户
				Userinfo userinfo = simpleGoodsService.getUserInfoById(comment.getUserId());
				comment.setUser(userinfo);

				// 获取订单
				OrderUser orderUser = simpleGoodsService.getOrderUserById(comment.getOrderUserId());
				List<Orders> order = userCenterService.getOrderByOrderUserId(orderUser.getOrderUserId());
				List<Goods> goodsList = new ArrayList<>();
				for (int j = 0; j < order.size(); j++) {
					OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
					List<Goods> goodsById = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
					goodsList.add(goodsById.get(0));
				}
				comment.setGoodsList(goodsList);

				// 获取反馈信息
				List<FeedbackComment> feedbackComment = sysManagementService
						.getFeedbackCommentByOrderId(comment.getOrderUserId());
				if (feedbackComment.size() != 0) {
					comment.setFeedbackComment(feedbackComment.get(0).getFeedbackContent());
				} else {
					comment.setFeedbackComment(null);
				}
				commentList.add(comment);

			} else {
				// 通过dateSetToList找integralComment
				IntegralComment integralComment = sysManagementService.getIntegralCommentByDate(dateSetToList.get(i));
				// 把积分评价表混入商品评价表里面
				Comment commentForIntegral = new Comment();
				commentForIntegral.setOrderUserId(integralComment.getIntegralOrderId());
				commentForIntegral.setUserId(integralComment.getUserId());
				commentForIntegral.setCommentContents(integralComment.getCommentContents());
				commentForIntegral.setCommentLevel(integralComment.getCommentLevel());
				commentForIntegral.setCommentTime(integralComment.getCommentTime());
				commentForIntegral.setCommentPhoto(integralComment.getCommentPhoto());
				commentForIntegral.setCommentId(integralComment.getIntegralCommentId());
				System.out.println(integralComment.getIsShow());
				// 如果展示则为2（用于标识是不是积分订单）
				if (integralComment.getIsShow().equals("1")) {
					commentForIntegral.setIsShow("2");
					System.out.println("为2");
				}
				if (integralComment.getIsShow().equals("0")) {
					// 否则为3
					commentForIntegral.setIsShow("3");
					System.out.println("为3");
				}
				// 查找用户
				Userinfo userinfo = simpleGoodsService.getUserInfoById(integralComment.getUserId());
				commentForIntegral.setUser(userinfo);

				// 查询订单
				List<IntegralOrder> integralOrder = userCenterService
						.getIntegralOrderById(integralComment.getIntegralOrderId());
				// 查询积分商品
				Shop shop = userCenterService.getShopById(integralOrder.get(0).getShopId());
				// 通过shop查询商品信息
				Goods goodsById = orderService.getGoodsById(shop.getGoodsId());
				List<Goods> goodsList = new ArrayList<>();
				goodsList.add(goodsById);
				commentForIntegral.setGoodsList(goodsList);

				// 查询反馈信息
				List<IntegralFeedbackComment> integralFeedbackComment = sysManagementService
						.getFeedbackCommentByIntegralOrderId(integralComment.getIntegralOrderId());
				if (integralFeedbackComment.size() != 0) {
					commentForIntegral.setFeedbackComment(integralFeedbackComment.get(0).getFeedbackContent());
				} else {
					commentForIntegral.setFeedbackComment(null);
				}

				commentList.add(commentForIntegral);
			}
		}

		// 分页
		int size = commentList.size();
		// 页数
		int pageSize = size % 4 == 0 ? size / 4 : size / 4 + 1;

		List<Comment> commentListForPage = new ArrayList<>();
		// 获取分页信息
		Integer page = Integer.parseInt(pageNum);
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 4, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 4;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			commentListForPage.add(commentList.get(i));
		}

		model.addAttribute("commentListForPage", commentListForPage);
		model.addAttribute("size", size);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("pageSize", pageSize);

		return "management/commentManagement";
	}

	// 添加反馈
	@RequestMapping(value = "/management/addFeedbackComment.action")
	public String addFeedbackComment(Model model, String userId, String isShow, String orderId, String comment) {
		// 商品订单反馈
		Integer orderid = Integer.parseInt(orderId);
		Integer userid = Integer.parseInt(userId);
		if (isShow.equals("1") || isShow.equals("0")) {
			// 保存商品反馈
			FeedbackComment feedbackComment = new FeedbackComment();
			feedbackComment.setUserId(userid);
			feedbackComment.setFeedbackContent(comment);
			feedbackComment.setFeedbackTime(new Date());
			feedbackComment.setOrderUserId(orderid);
			sysManagementService.saveFeedbackComment(feedbackComment);
		} else {
			// 保存积分订单反馈
			IntegralFeedbackComment feedbackComment = new IntegralFeedbackComment();
			feedbackComment.setUserId(userid);
			feedbackComment.setFeedbackContent(comment);
			feedbackComment.setFeedbackTime(new Date());
			feedbackComment.setIntegralOrderId(orderid);
			sysManagementService.saveIntegralFeedbackComment(feedbackComment);
		}
		return "redirect:/management/commentManagement.action?pageNum=1";
	}

	// 删除反馈
	@RequestMapping(value = "/management/deleteFeedbackComment.action")
	public String deleteFeedbackComment(Model model, String orderId, String isShow) {
		// 商品订单反馈
		Integer id = Integer.parseInt(orderId);
		if (isShow.equals("1") || isShow.equals("0")) {
			// 删除商品订单反馈
			sysManagementService.deleteFeedbackComment(id);
		} else {
			// 删除积分订单反馈
			sysManagementService.deleteIntegralFeedbackComment(id);

		}
		return "redirect:/management/commentManagement.action?pageNum=1";
	}

	// 删除评价
	@RequestMapping(value = "/management/deleteComment.action")
	public String deleteComment(Model model, String commentId, String isShow) {
		// 商品订单反馈
		Integer id = Integer.parseInt(commentId);
		if (isShow.equals("1") || isShow.equals("0")) {
			String flag = "0";
			// 删除商品订单评价
			sysManagementService.setComment(id, flag);
		} else {
			String flag = "0";
			// 删除积分订单评价
			sysManagementService.setIntegralComment(id, flag);
		}
		return "redirect:/management/commentManagement.action?pageNum=1";
	}

	// 还原评价
	@RequestMapping(value = "/management/backComment.action")
	public String backComment(Model model, String commentId, String isShow) {
		// 商品订单反馈
		Integer id = Integer.parseInt(commentId);
		if (isShow.equals("1") || isShow.equals("0")) {
			String flag = "1";
			// 删除商品订单评价
			sysManagementService.setComment(id, flag);
		} else {
			String flag = "1";
			// 删除积分订单评价
			sysManagementService.setIntegralComment(id, flag);
		}
		return "redirect:/management/commentManagement.action?pageNum=1";
	}

	// 评价搜素框
	@RequestMapping(value = "/management/findComment.action")
	public String findComment(Model model, String pageNum, String datetimepicker1, String datetimepicker2)
			throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取所有评价表
		List<Comment> allComment = sysManagementService.getAllComment();
		// 获取所有的积分评价表
		List<IntegralComment> allIntegralComment = sysManagementService.getAllIntegralComment();

		Set<String> dateSet1 = new TreeSet<>();
		for (int i = 0; i < allComment.size(); i++) {
			dateSet1.add(formatter2.format(allComment.get(i).getCommentTime()));
		}
		for (int i = 0; i < allIntegralComment.size(); i++) {
			dateSet1.add(formatter2.format(allIntegralComment.get(i).getCommentTime()));
		}

		// 倒叙排序
		List<String> dateSetToList1 = new ArrayList<>(dateSet1);
		// 最终版
		List<String> dateSetToList = new ArrayList<>();

		for (int i = dateSetToList1.size() - 1; i >= 0; i--) {
			dateSetToList.add(dateSetToList1.get(i));
		}

		// 第一个搜索框不为空
		if (datetimepicker1 != "" && datetimepicker2 == "") {
			List<String> searchDate = new ArrayList<>();
			int flag = 0;
			for (int i = 0; i < dateSetToList.size(); i++) {
				Date date1 = formatter.parse(datetimepicker1);
				Date date2 = formatter.parse(dateSetToList.get(i));
				if (date1.getTime() <= date2.getTime()) {
					searchDate.add(dateSetToList.get(i));
				}
				if (date1.getTime() > date2.getTime()) {
					flag++;
				}
			}
			if (flag == dateSetToList.size()) {
				model.addAttribute("size", 0);
				return "management/commentManagement";
			}
			List<Comment> commentList = new ArrayList<>();
			for (int i = 0; i < searchDate.size(); i++) {
				// 通过dateSetToList找comment
				List<Comment> comment2 = sysManagementService.getCommentByDate(searchDate.get(i));
				if (comment2.size() != 0) {
					Comment comment = comment2.get(0);
					// 查找用户
					Userinfo userinfo = simpleGoodsService.getUserInfoById(comment.getUserId());
					comment.setUser(userinfo);

					// 获取订单
					OrderUser orderUser = simpleGoodsService.getOrderUserById(comment.getOrderUserId());
					List<Orders> order = userCenterService.getOrderByOrderUserId(orderUser.getOrderUserId());
					List<Goods> goodsList = new ArrayList<>();
					for (int j = 0; j < order.size(); j++) {
						OrderGoods orderGoods = userCenterService
								.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
						List<Goods> goodsById = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
						goodsList.add(goodsById.get(0));
					}
					comment.setGoodsList(goodsList);

					// 获取反馈信息
					List<FeedbackComment> feedbackComment = sysManagementService
							.getFeedbackCommentByOrderId(comment.getOrderUserId());
					if (feedbackComment.size() != 0) {
						comment.setFeedbackComment(feedbackComment.get(0).getFeedbackContent());
					} else {
						comment.setFeedbackComment(null);
					}
					commentList.add(comment);

				} else {
					// 通过dateSetToList找integralComment
					IntegralComment integralComment = sysManagementService.getIntegralCommentByDate(searchDate.get(i));
					// 把积分评价表混入商品评价表里面
					Comment commentForIntegral = new Comment();
					commentForIntegral.setOrderUserId(integralComment.getIntegralOrderId());
					commentForIntegral.setUserId(integralComment.getUserId());
					commentForIntegral.setCommentContents(integralComment.getCommentContents());
					commentForIntegral.setCommentLevel(integralComment.getCommentLevel());
					commentForIntegral.setCommentTime(integralComment.getCommentTime());
					commentForIntegral.setCommentPhoto(integralComment.getCommentPhoto());
					commentForIntegral.setCommentId(integralComment.getIntegralCommentId());
					// 如果展示则为2（用于标识是不是积分订单）
					if (integralComment.getIsShow().equals("1")) {
						commentForIntegral.setIsShow("2");
					}
					if (integralComment.getIsShow().equals("0")) {
						// 否则为3
						commentForIntegral.setIsShow("3");
					}
					// 查找用户
					Userinfo userinfo = simpleGoodsService.getUserInfoById(integralComment.getUserId());
					commentForIntegral.setUser(userinfo);

					// 查询订单
					List<IntegralOrder> integralOrder = userCenterService
							.getIntegralOrderById(integralComment.getIntegralOrderId());
					// 查询积分商品
					Shop shop = userCenterService.getShopById(integralOrder.get(0).getShopId());
					// 通过shop查询商品信息
					Goods goodsById = orderService.getGoodsById(shop.getGoodsId());
					List<Goods> goodsList = new ArrayList<>();
					goodsList.add(goodsById);
					commentForIntegral.setGoodsList(goodsList);

					// 查询反馈信息
					List<IntegralFeedbackComment> integralFeedbackComment = sysManagementService
							.getFeedbackCommentByIntegralOrderId(integralComment.getIntegralOrderId());
					if (integralFeedbackComment.size() != 0) {
						commentForIntegral.setFeedbackComment(integralFeedbackComment.get(0).getFeedbackContent());
					} else {
						commentForIntegral.setFeedbackComment(null);
					}

					commentList.add(commentForIntegral);
				}
			}

			// 分页
			int size = commentList.size();
			// 页数
			int pageSize = size % 4 == 0 ? size / 4 : size / 4 + 1;

			List<Comment> commentListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 4, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 4;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				commentListForPage.add(commentList.get(i));
			}

			model.addAttribute("commentListForPage", commentListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("search1", 1);

			return "management/commentManagement";

		}
		// 第二个搜索框不为空
		if (datetimepicker1 == "" && datetimepicker2 != "") {
			List<String> searchDate = new ArrayList<>();
			int flag = 0;
			for (int i = 0; i < dateSetToList.size(); i++) {
				Date date1 = formatter.parse(datetimepicker2);
				Date date2 = formatter.parse(dateSetToList.get(i));
				if (date1.getTime() >= date2.getTime()) {
					searchDate.add(dateSetToList.get(i));
				}
				if (date1.getTime() < date2.getTime()) {
					flag++;
				}
			}
			if (flag == dateSetToList.size()) {
				model.addAttribute("size", 0);
				return "management/commentManagement";
			}
			List<Comment> commentList = new ArrayList<>();
			for (int i = 0; i < searchDate.size(); i++) {
				// 通过dateSetToList找comment
				List<Comment> comment2 = sysManagementService.getCommentByDate(searchDate.get(i));
				if (comment2.size() != 0) {
					Comment comment = comment2.get(0);
					// 查找用户
					Userinfo userinfo = simpleGoodsService.getUserInfoById(comment.getUserId());
					comment.setUser(userinfo);

					// 获取订单
					OrderUser orderUser = simpleGoodsService.getOrderUserById(comment.getOrderUserId());
					List<Orders> order = userCenterService.getOrderByOrderUserId(orderUser.getOrderUserId());
					List<Goods> goodsList = new ArrayList<>();
					for (int j = 0; j < order.size(); j++) {
						OrderGoods orderGoods = userCenterService
								.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
						List<Goods> goodsById = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
						goodsList.add(goodsById.get(0));
					}
					comment.setGoodsList(goodsList);

					// 获取反馈信息
					List<FeedbackComment> feedbackComment = sysManagementService
							.getFeedbackCommentByOrderId(comment.getOrderUserId());
					if (feedbackComment.size() != 0) {
						comment.setFeedbackComment(feedbackComment.get(0).getFeedbackContent());
					} else {
						comment.setFeedbackComment(null);
					}
					commentList.add(comment);

				} else {
					System.out.println(searchDate.get(i));
					// 通过dateSetToList找integralComment
					IntegralComment integralComment = sysManagementService.getIntegralCommentByDate(searchDate.get(i));
					// 把积分评价表混入商品评价表里面
					Comment commentForIntegral = new Comment();
					commentForIntegral.setOrderUserId(integralComment.getIntegralOrderId());
					commentForIntegral.setUserId(integralComment.getUserId());
					commentForIntegral.setCommentContents(integralComment.getCommentContents());
					commentForIntegral.setCommentLevel(integralComment.getCommentLevel());
					commentForIntegral.setCommentTime(integralComment.getCommentTime());
					commentForIntegral.setCommentPhoto(integralComment.getCommentPhoto());
					commentForIntegral.setCommentId(integralComment.getIntegralCommentId());
					// 如果展示则为2（用于标识是不是积分订单）
					if (integralComment.getIsShow().equals("1")) {
						commentForIntegral.setIsShow("2");
					}
					if (integralComment.getIsShow().equals("0")) {
						// 否则为3
						commentForIntegral.setIsShow("3");
					}
					// 查找用户
					Userinfo userinfo = simpleGoodsService.getUserInfoById(integralComment.getUserId());
					commentForIntegral.setUser(userinfo);

					// 查询订单
					List<IntegralOrder> integralOrder = userCenterService
							.getIntegralOrderById(integralComment.getIntegralOrderId());
					// 查询积分商品
					Shop shop = userCenterService.getShopById(integralOrder.get(0).getShopId());
					// 通过shop查询商品信息
					Goods goodsById = orderService.getGoodsById(shop.getGoodsId());
					List<Goods> goodsList = new ArrayList<>();
					goodsList.add(goodsById);
					commentForIntegral.setGoodsList(goodsList);

					// 查询反馈信息
					List<IntegralFeedbackComment> integralFeedbackComment = sysManagementService
							.getFeedbackCommentByIntegralOrderId(integralComment.getIntegralOrderId());
					if (integralFeedbackComment.size() != 0) {
						commentForIntegral.setFeedbackComment(integralFeedbackComment.get(0).getFeedbackContent());
					} else {
						commentForIntegral.setFeedbackComment(null);
					}

					commentList.add(commentForIntegral);
				}
			}

			// 分页
			int size = commentList.size();
			// 页数
			int pageSize = size % 4 == 0 ? size / 4 : size / 4 + 1;

			List<Comment> commentListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 4, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 4;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				commentListForPage.add(commentList.get(i));
			}

			model.addAttribute("commentListForPage", commentListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("search2", 1);

			return "management/commentManagement";

		}
		// 两个搜索框不为空
		else {
			List<String> searchDate = new ArrayList<>();
			int flag = 0;
			for (int i = 0; i < dateSetToList.size(); i++) {
				Date date1 = formatter.parse(datetimepicker1);
				Date date2 = formatter.parse(datetimepicker2);
				Date date3 = formatter.parse(dateSetToList.get(i));
				if (date1.getTime() <= date3.getTime() && date2.getTime() >= date3.getTime()) {
					searchDate.add(dateSetToList.get(i));
				}
				if (date1.getTime() > date3.getTime() || date2.getTime() < date3.getTime()) {
					flag++;
				}
			}
			if (flag == dateSetToList.size()) {
				model.addAttribute("size", 0);
				return "management/commentManagement";
			}
			List<Comment> commentList = new ArrayList<>();
			for (int i = 0; i < searchDate.size(); i++) {
				// 通过dateSetToList找comment
				List<Comment> comment2 = sysManagementService.getCommentByDate(searchDate.get(i));
				if (comment2.size() != 0) {
					Comment comment = comment2.get(0);
					// 查找用户
					Userinfo userinfo = simpleGoodsService.getUserInfoById(comment.getUserId());
					comment.setUser(userinfo);

					// 获取订单
					OrderUser orderUser = simpleGoodsService.getOrderUserById(comment.getOrderUserId());
					List<Orders> order = userCenterService.getOrderByOrderUserId(orderUser.getOrderUserId());
					List<Goods> goodsList = new ArrayList<>();
					for (int j = 0; j < order.size(); j++) {
						OrderGoods orderGoods = userCenterService
								.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
						List<Goods> goodsById = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
						goodsList.add(goodsById.get(0));
					}
					comment.setGoodsList(goodsList);

					// 获取反馈信息
					List<FeedbackComment> feedbackComment = sysManagementService
							.getFeedbackCommentByOrderId(comment.getOrderUserId());
					if (feedbackComment.size() != 0) {
						comment.setFeedbackComment(feedbackComment.get(0).getFeedbackContent());
					} else {
						comment.setFeedbackComment(null);
					}
					commentList.add(comment);

				} else {
					// 通过dateSetToList找integralComment
					IntegralComment integralComment = sysManagementService.getIntegralCommentByDate(searchDate.get(i));
					// 把积分评价表混入商品评价表里面
					Comment commentForIntegral = new Comment();
					commentForIntegral.setOrderUserId(integralComment.getIntegralOrderId());
					commentForIntegral.setUserId(integralComment.getUserId());
					commentForIntegral.setCommentContents(integralComment.getCommentContents());
					commentForIntegral.setCommentLevel(integralComment.getCommentLevel());
					commentForIntegral.setCommentTime(integralComment.getCommentTime());
					commentForIntegral.setCommentPhoto(integralComment.getCommentPhoto());
					commentForIntegral.setCommentId(integralComment.getIntegralCommentId());
					// 如果展示则为2（用于标识是不是积分订单）
					if (integralComment.getIsShow().equals("1")) {
						commentForIntegral.setIsShow("2");
					}
					if (integralComment.getIsShow().equals("0")) {
						// 否则为3
						commentForIntegral.setIsShow("3");
					}
					// 查找用户
					Userinfo userinfo = simpleGoodsService.getUserInfoById(integralComment.getUserId());
					commentForIntegral.setUser(userinfo);

					// 查询订单
					List<IntegralOrder> integralOrder = userCenterService
							.getIntegralOrderById(integralComment.getIntegralOrderId());
					// 查询积分商品
					Shop shop = userCenterService.getShopById(integralOrder.get(0).getShopId());
					// 通过shop查询商品信息
					Goods goodsById = orderService.getGoodsById(shop.getGoodsId());
					List<Goods> goodsList = new ArrayList<>();
					goodsList.add(goodsById);
					commentForIntegral.setGoodsList(goodsList);

					// 查询反馈信息
					List<IntegralFeedbackComment> integralFeedbackComment = sysManagementService
							.getFeedbackCommentByIntegralOrderId(integralComment.getIntegralOrderId());
					if (integralFeedbackComment.size() != 0) {
						commentForIntegral.setFeedbackComment(integralFeedbackComment.get(0).getFeedbackContent());
					} else {
						commentForIntegral.setFeedbackComment(null);
					}

					commentList.add(commentForIntegral);
				}
			}

			// 分页
			int size = commentList.size();
			// 页数
			int pageSize = size % 4 == 0 ? size / 4 : size / 4 + 1;

			List<Comment> commentListForPage = new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 4, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 4;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				commentListForPage.add(commentList.get(i));
			}

			model.addAttribute("commentListForPage", commentListForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("search3", 1);

			return "management/commentManagement";

		}

	}

	// 店铺设置
	@RequestMapping(value = "/management/storeSetting.action")
	public String storeSetting(Model model) {
		// 获取店铺设置的内容
		Store store = sysManagementService.getStore();
		model.addAttribute("store", store);
		return "management/storeSetting";
	}

	// 店铺信息修改
	@RequestMapping(value = "/management/storeSetting2.action")
	public String storeSetting2(Model model, Store store, String deliveryTime1, String deliveryTime2) {
		String businessHours = deliveryTime1 + "至" + deliveryTime2;
		// 修改店铺设置的内容
		store.setBusinessHours(businessHours);
		sysManagementService.alterStore(store);
		return "redirect:/management/storeSetting.action";
	}
	
	//转发到权限设置页面
	@RequestMapping(value="/management/permissionSetting.action")
	public String permissionSetting(Model model) {
		//查询加入黑名单客户
		List<Userinfo> blackListUser=sysManagementService.getBlackList();
		//查询未加入黑名单客户
		List<Userinfo> noBlackListUser=sysManagementService.getNoBlackList();
		
		model.addAttribute("blackListUser", blackListUser);
		model.addAttribute("noBlackListUser", noBlackListUser);
		return "management/permissionSetting";
	}
	
	//移除黑名单客户
	@RequestMapping(value="/management/deleteBlackList.action")
	public String deleteBlackList(Model model,String userId) {
		Integer id=Integer.valueOf(userId);
		//删除黑名单
		sysManagementService.deleteBlackList(id);
		return "redirect:/management/permissionSetting.action";
	}
	
	//添加黑名单客户
	@RequestMapping(value="/management/addBlackList.action")
	public String addBlackList(Model model,String userId) {
		Integer id=Integer.valueOf(userId);
		//添加黑名单
		sysManagementService.addBlackList(id);
		return "redirect:/management/permissionSetting.action";
	}

}
