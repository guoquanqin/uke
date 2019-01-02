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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.client.entity.Comment;
import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.IntegralOrder;
import com.client.entity.OrderGoods;
import com.client.entity.OrderUser;
import com.client.entity.Orders;
import com.client.entity.PageBeanInOrder;
import com.client.entity.Recommend;
import com.client.entity.Shop;
import com.client.entity.Userinfo;
import com.client.service.ProductService;
import com.client.service.SimpleGoodsService;
import com.client.service.UserCenterService;
import com.management.entity.Store;
import com.management.service.GoodsSettingService;
import com.management.service.SysManagementService;

@Controller
public class GoodsSettingController {
	@Autowired
	private GoodsSettingService goodsSettingService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private SimpleGoodsService simpleGoodsService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SysManagementService sysManagementService;

	// 主界面
	@RequestMapping(value = "/management/home.action")
	public String manaHome(Model model, HttpSession httpSession) {
		
		// 获取当天日期
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(new Date());

		// 定义set存储日期
		Set<String> dateSet = new TreeSet<>();

		// 获取当天已支付和正在配送的商品订单详情
		List<OrderUser> orderUser = goodsSettingService.getTodayOrder(date);
		// 获取当天已支付订单数量
		Integer orderUserPayment1 = goodsSettingService.getTodayOrderPayment(date);
		// 获取当天已兑换的积分订单数量
		Integer orderUserPayment2 = goodsSettingService.getTodayIntegralOrderPayment(date);
		// 获取当天所有订单
		List<OrderUser> allOrderUserInToday = goodsSettingService.getAllOrderToday(date);
		// 获取当天所有订单
		List<OrderUser> allOtherOrderUserInToday = goodsSettingService.getAllOtherToday(date);
		//获取所有订单
		List<OrderUser> allOrderUser=goodsSettingService.getAllOrderUser();
		
		
		for(int i=0;i<allOrderUser.size();i++) {
			String orderTime = formatter.format(allOrderUser.get(i).getOrderTime());
			dateSet.add(orderTime);
		}

		List<String> dateSetToList = new ArrayList<>(dateSet);
		List<String> dateList=new ArrayList<>();
		
		for(int i=dateSetToList.size()-1;i>=0;i--) {
			dateList.add(dateSetToList.get(i));
		}
		
		// 状态为2或3 的订单内容
		List<Userinfo> userInfoList1 = new ArrayList<>();
		List<String> goodsNameList1 = new ArrayList<>();
		for (int i = 0; i < orderUser.size(); i++) {
			// 根据id找userinfo
			Userinfo userinfo = simpleGoodsService.getUserInfoById(orderUser.get(i).getUserId());
			userInfoList1.add(userinfo);

			List<Orders> order = userCenterService.getOrderByOrderUserId(orderUser.get(i).getOrderUserId());
			String str = "";
			for (int j = 0; j < order.size(); j++) {
				OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
				List<Goods> goodsById = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
				if (j < order.size() - 1) {
					str += goodsById.get(0).getGoodsName() + "+";
				}
				if (j == order.size() - 1) {
					str += goodsById.get(0).getGoodsName();
				}
			}
			goodsNameList1.add(str);
		}
		
		//所有当天订单的内容
		List<Userinfo> userInfoList2 = new ArrayList<>();
		List<String> goodsNameList2 = new ArrayList<>();
		for (int i = 0; i < allOrderUserInToday.size(); i++) {
			// 根据id找userinfo
			Userinfo userinfo = simpleGoodsService.getUserInfoById(allOrderUserInToday.get(i).getUserId());
			userInfoList2.add(userinfo);

			List<Orders> order = userCenterService.getOrderByOrderUserId(allOrderUserInToday.get(i).getOrderUserId());
			String str = "";
			for (int j = 0; j < order.size(); j++) {
				OrderGoods orderGoods = userCenterService.getOrderGoodsByOrderId(order.get(j).getOrderGoodsId());
				List<Goods> goodsById = simpleGoodsService.getGoodsById(orderGoods.getGoodsId());
				if (j < order.size() - 1) {
					str += goodsById.get(0).getGoodsName() + "+";
				}
				if (j == order.size() - 1) {
					str += goodsById.get(0).getGoodsName();
				}
			}
			goodsNameList2.add(str);
		}
		

		// 获取订单统计的数据
		// 迭代
		//Iterator<String> it = dateSet.iterator();
		// 存放订单数
		List<Integer> orderNum = new ArrayList<>();
		// 存放交易数额
		List<Integer> amount = new ArrayList<>();
		// 存放成功订单数,状态为4到7为成功订单
		List<Integer> successOrder = new ArrayList<>();
		// 存放失败订单数，状态为1到3为失败订单
		List<Integer> failOrder = new ArrayList<>();
		//while (it.hasNext()) {
		for(int i=0;i<dateList.size();i++) {
			String dateNext=dateList.get(i);
			Integer orderCount = goodsSettingService.getOrderCountByDate(dateNext);
			Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateNext);
			Integer success = goodsSettingService.getSuccessOrderByDate(dateNext);
			Integer fail = goodsSettingService.getFailOrderByDate(dateNext);
			orderNum.add(orderCount);
			amount.add(orderAmount);
			successOrder.add(success);
			failOrder.add(fail);	
		}

		Integer levelCount=0;
		//获取好评率
		for(int i=0;i<allOrderUserInToday.size();i++) {
			//通过orderUserID找评论
			Comment comment=goodsSettingService.getCommentByOrderUserId(allOrderUserInToday.get(i).getOrderUserId());
			if(comment==null) {
				levelCount+=0;
			}else {
				Integer level =comment.getCommentLevel().intValue();
				if(level==5) {
					levelCount++;
				}
			}
		}
		
		String commentLevel="";
		if(allOrderUserInToday.size()==0)
			commentLevel="0%";
		else
			commentLevel=(levelCount/allOrderUserInToday.size())*100+"%";
		
		//获取店铺设置的内容
		Store store=sysManagementService.getStore();
		
		httpSession.setAttribute("store", store);
		
		httpSession.setAttribute("orderUserSize", orderUser.size());
		httpSession.setAttribute("allOrderSize", allOrderUserInToday.size());
		httpSession.setAttribute("allOtherOrderUserInTodaysize", allOtherOrderUserInToday.size());
		httpSession.setAttribute("amount", amount);
		httpSession.setAttribute("commentLevel", commentLevel);
		httpSession.setAttribute("orderNum", orderNum);
		httpSession.setAttribute("orderUserPayment", orderUserPayment1+orderUserPayment2);
		
		model.addAttribute("orderUser", orderUser);
		model.addAttribute("allOrderUser", allOrderUserInToday);
		model.addAttribute("allOtherOrderUserInToday", allOtherOrderUserInToday);
		model.addAttribute("dateList", dateList);
		model.addAttribute("successOrder", successOrder);
		model.addAttribute("failOrder", failOrder);
		model.addAttribute("userInfoList1", userInfoList1);
		model.addAttribute("goodsNameList1", goodsNameList1);
		model.addAttribute("userInfoList2", userInfoList2);
		model.addAttribute("goodsNameList2", goodsNameList2);
		return "management/home";
	}

	// 商品设置主界面
	@RequestMapping(value = "/management/goodsSet1.action")
	public String goodsSet1(Model model, String goodsType) {
		Integer type2=Integer.parseInt(goodsType);
		//通过类型查商品
		List<Goods> goodsList=goodsSettingService.findGoodsByGoodsType(type2);
		//通过type查类型
		Goodstype type = simpleGoodsService.getGoodsType(type2);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("type", type);
		return "management/goodsSet1";
	}
	
	// 商品下架
	@RequestMapping(value = "/management/lowerFrame.action")
	public String lowerFrame(Model model, String id) {
		Integer goodsId=Integer.parseInt(id);
		//下架商品
		Integer goodsInventory=0;
		String goodsIsSale="0";
		//设置商品的库存量和出售情况
		goodsSettingService.setGoodsByGoodsId(goodsId,goodsInventory,goodsIsSale);
		
		return "redirect:/management/goodsSet1.action?goodsType=1";
	}
	
	// 商品上架
	@RequestMapping(value = "/management/saleGoods.action")
	public String saleGoods(Model model, String inventory,String id) {
		Integer goodsId=Integer.parseInt(id);
		Integer inventoryNum=Integer.parseInt(inventory);
		//上架商品
		String goodsIsSale="1";
		//设置商品的库存量和出售情况
		goodsSettingService.setGoodsByGoodsId(goodsId,inventoryNum,goodsIsSale);
		
		return "redirect:/management/goodsSet1.action?goodsType=1";
	}
	
	// 商品设置
	@RequestMapping(value = "/management/goodsSet2.action")
	public String goodsSet2(Model model, String id,String mess) {
		Integer goodsId=Integer.parseInt(id);
		//通过id查商品信息(包括下架)
		Goods goodsById = goodsSettingService.getGoodsById(goodsId);
		//通过type查类型
		Goodstype type = simpleGoodsService.getGoodsType(goodsById.getGoodsType());
		//获取所有商品类型
		List<Goodstype> allGoodsType=goodsSettingService.getAllGoodsType();
		
		if(mess!="") {
			model.addAttribute("mess", mess);
		}
		model.addAttribute("goods", goodsById);
		model.addAttribute("type", type);
		model.addAttribute("allGoodsType", allGoodsType);
		return "management/goodsSet2";
	}
	
	// 添加商品页面
	@RequestMapping(value = "/management/toAddGoods.action")
	public String toAddGoods(Model model,String mess) {
		//获取所有商品类型
		List<Goodstype> allGoodsType=goodsSettingService.getAllGoodsType();
		if(mess!="") {
			model.addAttribute("mess", mess);
		}
		
		model.addAttribute("allGoodsType", allGoodsType);
		return "management/addGoods";
	}
	
	// 保存商品设置
	@RequestMapping(value = "/management/saveGoodsSetting.action")
	public String saveGoodsSetting(HttpServletRequest request,Model model,String id,String goodsName,String goodsType,MultipartFile pictrueFile,
			String size,String[] price1,String[] price2,String describe) throws Exception {
		
		Integer goodsId=Integer.parseInt(id);
		Integer type=Integer.parseInt(goodsType);
		String picName="";
		//通过id查商品信息
		Goods goods= simpleGoodsService.getGoodsById(goodsId).get(0);
		
		//名字与原来不一致
		if(!goods.getGoodsName().equals(goodsName)) {
			//判断名字是否存在
			//获取所有goods
			List<Goods> goodsList=goodsSettingService.getAllGoods();
			//统计相同名字数量
			Integer count=0;
			for(int i=0;i<goodsList.size();i++) {
				if(goodsName.equals(goodsList.get(i).getGoodsName())) {
					count++;
				}
			}
			//如果重复,返回
			if(count==1) {
				String mess="1";
				return "redirect:/management/goodsSet2.action?id="+goodsId+"&mess="+mess;
			}
		}
		
		goods.setGoodsName(goodsName);
		goods.setGoodsType(type);
		goods.setGoodsDescribe(describe);
		if(size=="0") {
			Float goodsPrice=Float.parseFloat(price1[1]);
			goods.setGoodsPrice((float)goodsPrice);
			goods.setGoodsMiddlePrice(null);
		}else {
			Float goodsPrice=Float.parseFloat(price1[1]);
			Float goodsMiddlePrice=Float.parseFloat(price2[1]);
			goods.setGoodsPrice(goodsPrice);
			goods.setGoodsMiddlePrice(goodsMiddlePrice);
		}
		//通过type查类型
		Goodstype goodstype = simpleGoodsService.getGoodsType(type);
		//获取项目名
		String projectName = request.getContextPath();
		//获取项目跟路径
		String filePath = request.getServletContext().getRealPath("/");
		//将项目跟路劲下的项目名称置为空，因为图片需要在项目外的webapp下面存放,sub截取下标为1的字符
		filePath=filePath.replace(projectName.substring(1),"");
		
		//判断图片是否为空
		if(!pictrueFile.isEmpty()) {
			//32位随机字符
			String name1=UUID.randomUUID().toString().replaceAll("-", "");
			//jpg,自动获取原始的后缀名字
			String ext1 =FilenameUtils.getExtension(pictrueFile.getOriginalFilename());
			picName=name1+"."+ext1;
			//上传新的图片
			pictrueFile.transferTo(new File(filePath+projectName+"\\images\\"+goodstype.getGtName()+"\\"+picName));
			//pictrueFile.transferTo(new File("D:\\eclipse-java\\eclipse-SSH\\uke\\WebContent\\images\\"+goodstype.getGtName()+"\\"+picName));
			goods.setGoodsPicture(picName);
		}else {
			goods.setGoodsPicture(goods.getGoodsPicture());
		}
		
		
		//保存设置
		goodsSettingService.saveGoodsSetting(goods);
		
		return "redirect:/management/goodsSet1.action?goodsType=1";
	}
	
	// 添加商品
	@RequestMapping(value = "/management/addGoods.action")
	public String addGoods(HttpServletRequest request,Model model,String goodsName,String goodsType,MultipartFile pictrueFile,
			String size,String[] price1,String[] price2,String describe) throws Exception {
		Integer type=Integer.parseInt(goodsType);
		String picName="";
		//查询商品名称是否重复
		List<Goods> getGoodsByName=goodsSettingService.getGoodsByName(goodsName);
		
		//判断名字是否存在,如果重复，则返回
		if(getGoodsByName.size()!=0) {
			String mess="1";
			return "redirect:/management/toAddGoods.action?&mess="+mess;
		}
		Goods goods=new Goods();
		goods.setGoodsName(goodsName);
		goods.setGoodsType(type);
		goods.setGoodsDescribe(describe);
		goods.setGoodsInventory(100);
		goods.setGoodsSalesVolume(0);
		goods.setGoodsIsSale("1");
		if(size=="0") {
			Float goodsPrice=Float.parseFloat(price1[1]);
			goods.setGoodsPrice((float)goodsPrice);
			goods.setGoodsMiddlePrice(null);
		}else {
			Float goodsPrice=Float.parseFloat(price1[1]);
			Float goodsMiddlePrice=Float.parseFloat(price2[1]);
			goods.setGoodsPrice(goodsPrice);
			goods.setGoodsMiddlePrice(goodsMiddlePrice);
		}
		//通过type查类型
		Goodstype goodstype = simpleGoodsService.getGoodsType(type);
		//获取项目名
		String projectName = request.getContextPath();
		//获取项目跟路径
		String filePath = request.getServletContext().getRealPath("/");
		//将项目跟路劲下的项目名称置为空，因为图片需要在项目外的webapp下面存放,sub截取下标为1的字符
		filePath=filePath.replace(projectName.substring(1),"");
		
		//判断图片是否为空
		if(!pictrueFile.isEmpty()) {
			//32位随机字符
			String name1=UUID.randomUUID().toString().replaceAll("-", "");
			//jpg,自动获取原始的后缀名字
			String ext1 =FilenameUtils.getExtension(pictrueFile.getOriginalFilename());
			picName=name1+"."+ext1;
			//上传新的图片
			pictrueFile.transferTo(new File(filePath+projectName+"\\images\\"+goodstype.getGtName()+"\\"+picName));
			goods.setGoodsPicture(picName);
		}else {
			goods.setGoodsPicture("暂无.png");
		}
		
		
		//添加商品
		goodsSettingService.addGoods(goods);
		
		return "redirect:/management/goodsSet1.action?goodsType=1";
	}
	
	// 店长推荐
	@RequestMapping(value = "/management/recommend.action")
	public String recommend(Model model) {
		//获取所有商品类型
		List<Goodstype> allGoodsType=goodsSettingService.getAllGoodsType();
		//获取所有推荐商品
		List<Goods> goods = productService.getRecommendGoods();
		//获取推荐商品的商品类型
		List<Goodstype> typeList=new ArrayList<>();
		for(int i=0;i<goods.size();i++) {
			Goodstype goodsType = simpleGoodsService.getGoodsType(goods.get(i).getGoodsType());
			typeList.add(goodsType);
		}
		List<Integer> goodsIdList=new ArrayList<>();
		for(int i=0;i<goods.size();i++) {
			goodsIdList.add(goods.get(i).getGoodsId());
		}
		//通过类型获取商品
		List<Goods> jdnc=goodsSettingService.getGoodsByGoodsType(1,goodsIdList);
		
		
		model.addAttribute("allGoodsType", allGoodsType);
		model.addAttribute("goods", goods);
		model.addAttribute("typeList", typeList);
		model.addAttribute("jdnc", jdnc);
		return "management/recommend";
	}
	
	//联动获取推荐商品
	@RequestMapping(value="/management/findGoodsNameByType.action")
	public @ResponseBody
	 List<Goods> findGoodsNameByType(@RequestBody String gtId) throws Exception{
		Integer id=Integer.parseInt(gtId);
		
		//获取所有推荐商品
		List<Goods> goods = productService.getRecommendGoods();
		List<Integer> goodsIdList=new ArrayList<>();
		for(int i=0;i<goods.size();i++) {
			goodsIdList.add(goods.get(i).getGoodsId());
		}
		List<Goods> goodsList=goodsSettingService.getGoodsByGoodsType(id,goodsIdList);
		return goodsList;
	}
	
	// 添加推荐商品
	@RequestMapping(value = "/management/addRecommend.action")
	public String addRecommend(String gtId,String name) {
		Integer id=Integer.parseInt(gtId);		
		//通过goodTypeId和商品名查找商品
		Goods goods=goodsSettingService.findGoodsByGoodsTypeAndGoodsName(id,name);
		
		//先查看是否存在
		List<Recommend> isExist= goodsSettingService.findRecommendByGoodsId(goods.getGoodsId());
		if(isExist.size()==0) {
			Recommend recommend=new Recommend();
			recommend.setGoodsId(goods.getGoodsId());
			recommend.setIsSale("1");
			//保存推荐商品
			goodsSettingService.saveRecommend(recommend);
		}else {
			//设置is_sale为1
			goodsSettingService.setRecommendIsSale(isExist.get(0).getRecommendId());
		}
		return "redirect:/management/recommend.action";
	}
	
	// 移除推荐商品
	@RequestMapping(value = "/management/removeRecommend.action")
	public String removeRecommend(String goodsId) {
		Integer id=Integer.parseInt(goodsId);		
		//设置推荐商品为下架
		goodsSettingService.removeRecommend(id);
		return "redirect:/management/recommend.action";
	}
	
	// 积分设置
	@RequestMapping(value = "/management/integralSetting.action")
	public String integralSetting(Model model) {
		//获取积分所有商品
		List<Shop> shop = productService.getShopGoods();
		//获取对应的商品
		List<Goods> goodsList=new ArrayList<>();
		List<Goodstype> typeList=new ArrayList<>();
		for(int i=0;i<shop.size();i++) {
			List<Goods> goods=simpleGoodsService.getGoodsById(shop.get(i).getGoodsId());
			goodsList.add(goods.get(0));
			//获取积分商品的类型
			Goodstype goodsType = simpleGoodsService.getGoodsType(goods.get(0).getGoodsType());
			typeList.add(goodsType);
		}
		
		//获取所有商品类型
		List<Goodstype> allGoodsType=goodsSettingService.getAllGoodsType();
		
		List<Integer> goodsIdList=new ArrayList<>();
		for(int i=0;i<shop.size();i++) {
			goodsIdList.add(shop.get(i).getGoodsId());
		}
		//通过类型获取商品
		List<Goods> jdnc=goodsSettingService.getGoodsByGoodsType(1,goodsIdList);
		
		model.addAttribute("shop", shop);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("allGoodsType", allGoodsType);
		model.addAttribute("jdnc", jdnc);
		model.addAttribute("typeList", typeList);
		
		return "management/integralSetting";
	}
	
	//联动获取商品
	@RequestMapping(value="/management/findGoodsNameByTypeInIntegral.action")
	public @ResponseBody
	 List<Goods> findGoodsNameByTypeInIntegral(@RequestBody String gtId) throws Exception{
		Integer id=Integer.parseInt(gtId);
		
		//获取积分所有商品
		List<Shop> shop = productService.getShopGoods();
		List<Integer> goodsIdList=new ArrayList<>();
		for(int i=0;i<shop.size();i++) {
			goodsIdList.add(shop.get(i).getGoodsId());
		}
		List<Goods> goodsList=goodsSettingService.getGoodsByGoodsType(id,goodsIdList);
		return goodsList;
	}
	
	// 移除积分商品
	@RequestMapping(value = "/management/removeIntegralGoods.action")
	public String removeIntegralGoods(String goodsId) {
		Integer id=Integer.parseInt(goodsId);		
		//设置积分商品为下架
		goodsSettingService.removeIntegralGoods(id);
		return "redirect:/management/integralSetting.action";
	}
	
	// 添加积分商品
	@RequestMapping(value = "/management/addIntegralGoods.action")
	public String addIntegralGoods(String gtId,String goodsId,String integral) {
		Integer id=Integer.parseInt(goodsId);		
		Integer integralNum=Integer.parseInt(integral);	
		
		//先查看是否存在
		List<Shop> isExist= goodsSettingService.findShopGoodsByGoodsId(id);
		if(isExist.size()==0) {
			Shop shop=new Shop();
			shop.setGoodsId(id);
			shop.setIsSale("1");
			shop.setIntegral(integralNum);
			//保存积分商品
			goodsSettingService.saveShopGoods(shop);
		}else {
			//设置is_sale为1
			goodsSettingService.setShopGoodsIsSale(isExist.get(0).getShopId());
		}
		return "redirect:/management/integralSetting.action";
	}
	
	// 修改积分商品
	@RequestMapping(value = "/management/alterIntegral.action")
	public String alterIntegral(String integralId,String integral) {
		Integer id=Integer.parseInt(integralId);		
		Integer integralNum=Integer.parseInt(integral);		
		
		//修改积分商品
		goodsSettingService.alterIntegral(id,integralNum);
		return "redirect:/management/integralSetting.action";
	}
	
	
	
}
