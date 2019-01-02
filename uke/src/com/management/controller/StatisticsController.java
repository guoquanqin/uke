package com.management.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.client.entity.Comment;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralOrder;
import com.client.entity.OrderUser;
import com.client.entity.PageBeanInOrder;
import com.management.entity.OtherProject;
import com.management.service.GoodsSettingService;
import com.management.service.StatisticsService;

@Controller
public class StatisticsController {
	@Autowired
	private GoodsSettingService goodsSettingService;
	@Autowired
	private StatisticsService statisticsService;

	public static List<String> dateSort(List<String> a) throws Exception{
        int len = a.size();
        for(int i = len-1;i>=1;i--){
            for(int j = 0;j<=i-1;j++){
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            	Date date1=formatter.parse(a.get(j));
            	Date date2=formatter.parse(a.get(j+1));
                if(date1.getTime()<date2.getTime()){
                    String temp = a.get(j);
                    String temp2=a.get(j);
                    temp2=a.get(j+1);
                    String temp3=a.get(j+1);
                    temp3=temp;
                }
            }
        }
        return a;
    }
	
	// 订单统计
	@RequestMapping(value = "/management/orderStatistics.action")
	public String orderStatistics(Model model, String pageNum) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date=formatter.format(new Date());
		//获取所有订单
		List<OrderUser> allOrderUser=statisticsService.getAllOrderUser();
		//获取所有积分订单
		List<IntegralOrder> allIntegralOrderList=statisticsService.getAllIntegralOrderList();
		// 获取当天所有订单
		List<OrderUser> allOrderUserInToday = goodsSettingService.getAllOrderToday(date);
		// 定义set存储日期
		Set<String> dateSet = new TreeSet<>();
		for(int i=0;i<allOrderUser.size();i++) {
			String orderTime = formatter.format(allOrderUser.get(i).getOrderTime());
			dateSet.add(orderTime);
		}
		
		//存储积分订单时间
		for(int i=0;i<allIntegralOrderList.size();i++) {
			String orderTime=allIntegralOrderList.get(i).getOrderTime().substring(0,10);
			if(!dateSet.contains(orderTime)) {
				dateSet.add(orderTime);
			}
		}
		
		
		List<String> dateSetToList = new ArrayList<>(dateSet);
		//降序排序时间
		dateSort(dateSetToList);
		List<String> dateList=new ArrayList<>();
		
		for(int i=dateSetToList.size()-1;i>=0;i--) {
			dateList.add(dateSetToList.get(i));
		}
		
		// 获取订单统计的数据
		// 存放订单数
		List<Integer> orderNum = new ArrayList<>();
		// 存放交易数额
		List<Integer> amount = new ArrayList<>();
		// 存放成功订单数,状态为4到7为成功订单
		List<Integer> successOrder = new ArrayList<>();
		// 存放失败订单数，状态为9为失败订单
		List<Integer> failOrder = new ArrayList<>();
		//存放好评数
		List<String> comment=new ArrayList<>();
		for(int i=0;i<dateList.size();i++) {
			String dateNext=dateSetToList.get(i);
			
			Integer orderCount = goodsSettingService.getOrderCountByDate(dateNext);
			Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateNext);
			Integer success = goodsSettingService.getSuccessOrderByDate(dateNext);
			Integer fail = goodsSettingService.getFailOrderByDate(dateNext);
			//查询积分订单数据
			Integer orderCount2=statisticsService.getIntegralOrderCountByDate(dateNext);
			Integer success2=statisticsService.getIntegralSuccessOrderByDate(dateNext);
			Integer fail2=statisticsService.getIntegralFailOrderByDate(dateNext);
			
			orderNum.add(orderCount+orderCount2);
			amount.add(orderAmount);
			successOrder.add(success+success2);
			failOrder.add(orderCount+orderCount2-success-success2);	
			
			//获取每天的订单
			List<OrderUser> orderUser=goodsSettingService.getTodayOrderUser(dateNext);
			//获取每天的积分订单
			List<IntegralOrder> integralOrder=goodsSettingService.getTodayIntegralOrder(dateNext);
			Integer commentCount=0;
			for(int j=0;j<orderUser.size();j++) {
				Comment commentBean=goodsSettingService.getCommentByOrderUserId(orderUser.get(j).getOrderUserId());
				if(commentBean!=null) {
					Integer level =commentBean.getCommentLevel().intValue();
					if(level==5) {
						commentCount++;
					}
				}
			}
			
			
			//积分订单的好评率
			Integer commentCount2=0;
			for(int j=0;j<integralOrder.size();j++) {
				//获取积分订单评论表
				IntegralComment integralComment=goodsSettingService.getIntegralCommentByOrderUserId(integralOrder.get(j).getIntegralOrderId());
				if(integralComment!=null) {
					Integer level2 =integralComment.getCommentLevel().intValue();
					if(level2==5) {
						commentCount2++;
					}
				}
			}
			String level="";
			if(commentCount==0&&commentCount2==0)
				level="0%";
			else
				level=((commentCount+commentCount2)/(orderUser.size()+integralOrder.size()))*100+"%";
			//存放好评率
			comment.add(level);
		}
		
		//今日销售额
		Integer orderAmount=goodsSettingService.getOrderMoneyByDate(date);
		//今日订单数
		Integer orderCount = goodsSettingService.getOrderCountByDate(date);
		//查询积分订单数据
		Integer orderCount2=statisticsService.getIntegralOrderCountByDate(date);
		
		
		// 分页
		int size = dateSetToList.size();
		// 页数
		int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;
		
		List<String> dateListForPage = new ArrayList<>();
		// 存放订单数
		List<Integer> orderNumList = new ArrayList<>();
		// 存放交易数额
		List<Integer> amountList = new ArrayList<>();
		// 存放成功订单数,状态为4到7为成功订单
		List<Integer> successOrderList = new ArrayList<>();
		// 存放失败订单数，状态为9为失败订单
		List<Integer> failOrderList = new ArrayList<>();
		//存放好评数
		List<String> commentList=new ArrayList<>();
		// 获取分页信息
		Integer page = Integer.parseInt(pageNum);
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			dateListForPage.add(dateList.get(i));
			orderNumList.add(orderNum.get(i));
			amountList.add(amount.get(i));
			successOrderList.add(successOrder.get(i));
			failOrderList.add(failOrder.get(i));
			commentList.add(comment.get(i));
		}
		
		model.addAttribute("dateListForPage", dateListForPage);
		model.addAttribute("orderNumList", orderNumList);
		model.addAttribute("amountList", amountList);
		model.addAttribute("successOrderList", successOrderList);
		model.addAttribute("failOrderList", failOrderList);
		model.addAttribute("commentList", commentList);
		model.addAttribute("date", date);
		model.addAttribute("orderCount", orderCount+orderCount2);
		model.addAttribute("orderAmount", orderAmount);
		model.addAttribute("todaySize", allOrderUserInToday.size());
		model.addAttribute("size", size);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("pageSize", pageSize);
		
		return "management/orderStatistics";
	}
	
	
	// 订单统计搜素框
	@RequestMapping(value = "/management/findOrderStatistics.action")
	public String findOrderStatistics(Model model,String pageNum, String datetimepicker1, String datetimepicker2) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date=formatter.format(new Date());
		//获取所有订单
		List<OrderUser> allOrderUser=statisticsService.getAllOrderUser();
		//获取所有积分订单
		List<IntegralOrder> allIntegralOrderList=statisticsService.getAllIntegralOrderList();
		// 获取当天所有订单
		List<OrderUser> allOrderUserInToday = goodsSettingService.getAllOrderToday(date);
		// 定义set存储日期
		Set<String> dateSet = new TreeSet<>();
		for(int i=0;i<allOrderUser.size();i++) {
			String orderTime = formatter.format(allOrderUser.get(i).getOrderTime());
			dateSet.add(orderTime);
		}
		
		//存储积分订单时间
		for(int i=0;i<allIntegralOrderList.size();i++) {
			String orderTime=allIntegralOrderList.get(i).getOrderTime().substring(0,10);
			if(!dateSet.contains(orderTime)) {
				dateSet.add(orderTime);
			}
		}
		
		
		List<String> dateSetToList = new ArrayList<>(dateSet);
		//降序排序时间
		dateSort(dateSetToList);
		List<String> dateList=new ArrayList<>();
		
		for(int i=dateSetToList.size()-1;i>=0;i--) {
			dateList.add(dateSetToList.get(i));
		}
		
		//第一个搜索框不为空
		if(datetimepicker1!=""&&datetimepicker2=="") {
			List<String> firstSearchDate=new ArrayList<>();
			for(int i=0;i<dateList.size();i++) {
				Date date1=formatter.parse(datetimepicker1);
				Date date2=formatter.parse(dateList.get(i));
				if(date1.getTime()<=date2.getTime()) {
					firstSearchDate.add(dateList.get(i));
				}
			}
			// 获取订单统计的数据
			// 存放订单数
			List<Integer> orderNum = new ArrayList<>();
			// 存放交易数额
			List<Integer> amount = new ArrayList<>();
			// 存放成功订单数,状态为4到7为成功订单
			List<Integer> successOrder = new ArrayList<>();
			// 存放失败订单数，状态为9为失败订单
			List<Integer> failOrder = new ArrayList<>();
			//存放好评数
			List<String> comment=new ArrayList<>();
			for(int i=0;i<firstSearchDate.size();i++) {
				String dateNext=firstSearchDate.get(i);
				
				Integer orderCount = goodsSettingService.getOrderCountByDate(dateNext);
				Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateNext);
				Integer success = goodsSettingService.getSuccessOrderByDate(dateNext);
				Integer fail = goodsSettingService.getFailOrderByDate(dateNext);
				//查询积分订单数据
				Integer orderCount2=statisticsService.getIntegralOrderCountByDate(dateNext);
				Integer success2=statisticsService.getIntegralSuccessOrderByDate(dateNext);
				Integer fail2=statisticsService.getIntegralFailOrderByDate(dateNext);
				
				orderNum.add(orderCount+orderCount2);
				amount.add(orderAmount);
				successOrder.add(success+success2);
				failOrder.add(fail+fail2);	
				
				//获取每天的订单
				List<OrderUser> orderUser=goodsSettingService.getTodayOrderUser(dateNext);
				//获取每天的积分订单
				List<IntegralOrder> integralOrder=goodsSettingService.getTodayIntegralOrder(dateNext);
				Integer commentCount=0;
				for(int j=0;j<orderUser.size();j++) {
					Comment commentBean=goodsSettingService.getCommentByOrderUserId(orderUser.get(j).getOrderUserId());
					if(commentBean!=null) {
						Integer level =commentBean.getCommentLevel().intValue();
						if(level==5) {
							commentCount++;
						}
					}
				}
				
				
				//积分订单的好评率
				Integer commentCount2=0;
				for(int j=0;j<integralOrder.size();j++) {
					//获取积分订单评论表
					IntegralComment integralComment=goodsSettingService.getIntegralCommentByOrderUserId(integralOrder.get(j).getIntegralOrderId());
					if(integralComment!=null) {
						Integer level2 =integralComment.getCommentLevel().intValue();
						if(level2==5) {
							commentCount2++;
						}
					}
				}
				String level="";
				if(commentCount==0&&commentCount2==0)
					level="0%";
				else
					level=((commentCount+commentCount2)/(orderUser.size()+integralOrder.size()))*100+"%";
				//存放好评率
				comment.add(level);
			}
			
			
			//今日销售额
			Integer orderAmount=goodsSettingService.getOrderMoneyByDate(date);
			//今日订单数
			Integer orderCount = goodsSettingService.getOrderCountByDate(date);
			//查询积分订单数据
			Integer orderCount2=statisticsService.getIntegralOrderCountByDate(date);
			
			
			// 分页
			int size = firstSearchDate.size();
			// 页数
			int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;
			
			List<String> dateListForPage = new ArrayList<>();
			// 存放订单数
			List<Integer> orderNumList = new ArrayList<>();
			// 存放交易数额
			List<Integer> amountList = new ArrayList<>();
			// 存放成功订单数,状态为4到7为成功订单
			List<Integer> successOrderList = new ArrayList<>();
			// 存放失败订单数，状态为9为失败订单
			List<Integer> failOrderList = new ArrayList<>();
			//存放好评数
			List<String> commentList=new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 7;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				dateListForPage.add(firstSearchDate.get(i));
				orderNumList.add(orderNum.get(i));
				amountList.add(amount.get(i));
				successOrderList.add(successOrder.get(i));
				failOrderList.add(failOrder.get(i));
				commentList.add(comment.get(i));
			}
			model.addAttribute("dateListForPage", dateListForPage);
			model.addAttribute("orderNumList", orderNumList);
			model.addAttribute("amountList", amountList);
			model.addAttribute("successOrderList", successOrderList);
			model.addAttribute("failOrderList", failOrderList);
			model.addAttribute("commentList", commentList);
			model.addAttribute("date", date);
			model.addAttribute("orderCount", orderCount+orderCount2);
			model.addAttribute("orderAmount", orderAmount);
			model.addAttribute("todaySize", allOrderUserInToday.size());
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("search1", 1);
			model.addAttribute("datetimepicker1", datetimepicker1);
			return "management/orderStatistics";
			
		}
		//第二个搜索框不为空
		if(datetimepicker1==""&&datetimepicker2!="") {
			List<String> firstSearchDate=new ArrayList<>();
			for(int i=0;i<dateList.size();i++) {
				Date date1=formatter.parse(datetimepicker2);
				Date date2=formatter.parse(dateList.get(i));
				if(date1.getTime()>=date2.getTime()) {
					firstSearchDate.add(dateList.get(i));
				}
			}
			// 获取订单统计的数据
			// 存放订单数
			List<Integer> orderNum = new ArrayList<>();
			// 存放交易数额
			List<Integer> amount = new ArrayList<>();
			// 存放成功订单数,状态为4到7为成功订单
			List<Integer> successOrder = new ArrayList<>();
			// 存放失败订单数，状态为9为失败订单
			List<Integer> failOrder = new ArrayList<>();
			//存放好评数
			List<String> comment=new ArrayList<>();
			for(int i=0;i<firstSearchDate.size();i++) {
				String dateNext=firstSearchDate.get(i);
				
				Integer orderCount = goodsSettingService.getOrderCountByDate(dateNext);
				Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateNext);
				Integer success = goodsSettingService.getSuccessOrderByDate(dateNext);
				Integer fail = goodsSettingService.getFailOrderByDate(dateNext);
				//查询积分订单数据
				Integer orderCount2=statisticsService.getIntegralOrderCountByDate(dateNext);
				Integer success2=statisticsService.getIntegralSuccessOrderByDate(dateNext);
				Integer fail2=statisticsService.getIntegralFailOrderByDate(dateNext);
				
				orderNum.add(orderCount+orderCount2);
				amount.add(orderAmount);
				successOrder.add(success+success2);
				failOrder.add(fail+fail2);	
				
				//获取每天的订单
				List<OrderUser> orderUser=goodsSettingService.getTodayOrderUser(dateNext);
				//获取每天的积分订单
				List<IntegralOrder> integralOrder=goodsSettingService.getTodayIntegralOrder(dateNext);
				Integer commentCount=0;
				for(int j=0;j<orderUser.size();j++) {
					Comment commentBean=goodsSettingService.getCommentByOrderUserId(orderUser.get(j).getOrderUserId());
					if(commentBean!=null) {
						Integer level =commentBean.getCommentLevel().intValue();
						if(level==5) {
							commentCount++;
						}
					}
				}
				
				
				//积分订单的好评率
				Integer commentCount2=0;
				for(int j=0;j<integralOrder.size();j++) {
					//获取积分订单评论表
					IntegralComment integralComment=goodsSettingService.getIntegralCommentByOrderUserId(integralOrder.get(j).getIntegralOrderId());
					if(integralComment!=null) {
						Integer level2 =integralComment.getCommentLevel().intValue();
						if(level2==5) {
							commentCount2++;
						}
					}
				}
				String level="";
				if(commentCount==0&&commentCount2==0)
					level="0%";
				else
					level=((commentCount+commentCount2)/(orderUser.size()+integralOrder.size()))*100+"%";
				//存放好评率
				comment.add(level);
			}
			
			
			//今日销售额
			Integer orderAmount=goodsSettingService.getOrderMoneyByDate(date);
			//今日订单数
			Integer orderCount = goodsSettingService.getOrderCountByDate(date);
			//查询积分订单数据
			Integer orderCount2=statisticsService.getIntegralOrderCountByDate(date);
			
			
			// 分页
			int size = firstSearchDate.size();
			// 页数
			int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;
			
			List<String> dateListForPage = new ArrayList<>();
			// 存放订单数
			List<Integer> orderNumList = new ArrayList<>();
			// 存放交易数额
			List<Integer> amountList = new ArrayList<>();
			// 存放成功订单数,状态为4到7为成功订单
			List<Integer> successOrderList = new ArrayList<>();
			// 存放失败订单数，状态为9为失败订单
			List<Integer> failOrderList = new ArrayList<>();
			//存放好评数
			List<String> commentList=new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 7;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				dateListForPage.add(firstSearchDate.get(i));
				orderNumList.add(orderNum.get(i));
				amountList.add(amount.get(i));
				successOrderList.add(successOrder.get(i));
				failOrderList.add(failOrder.get(i));
				commentList.add(comment.get(i));
			}
			model.addAttribute("dateListForPage", dateListForPage);
			model.addAttribute("orderNumList", orderNumList);
			model.addAttribute("amountList", amountList);
			model.addAttribute("successOrderList", successOrderList);
			model.addAttribute("failOrderList", failOrderList);
			model.addAttribute("commentList", commentList);
			model.addAttribute("date", date);
			model.addAttribute("orderCount", orderCount+orderCount2);
			model.addAttribute("orderAmount", orderAmount);
			model.addAttribute("todaySize", allOrderUserInToday.size());
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("datetimepicker2", datetimepicker2);
			model.addAttribute("search2", 1);
			return "management/orderStatistics";
			
		}
		//两个搜索框不为空
		else{
			List<String> firstSearchDate=new ArrayList<>();
			for(int i=0;i<dateList.size();i++) {
				Date date1=formatter.parse(datetimepicker1);
				Date date2=formatter.parse(datetimepicker2);
				Date date3=formatter.parse(dateList.get(i));
				if(date1.getTime()<=date3.getTime()&&date2.getTime()>=date3.getTime()) {
					firstSearchDate.add(dateList.get(i));
				}
			}
			// 获取订单统计的数据
			// 存放订单数
			List<Integer> orderNum = new ArrayList<>();
			// 存放交易数额
			List<Integer> amount = new ArrayList<>();
			// 存放成功订单数,状态为4到7为成功订单
			List<Integer> successOrder = new ArrayList<>();
			// 存放失败订单数，状态为9为失败订单
			List<Integer> failOrder = new ArrayList<>();
			//存放好评数
			List<String> comment=new ArrayList<>();
			for(int i=0;i<firstSearchDate.size();i++) {
				String dateNext=firstSearchDate.get(i);
				
				Integer orderCount = goodsSettingService.getOrderCountByDate(dateNext);
				Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateNext);
				Integer success = goodsSettingService.getSuccessOrderByDate(dateNext);
				Integer fail = goodsSettingService.getFailOrderByDate(dateNext);
				//查询积分订单数据
				Integer orderCount2=statisticsService.getIntegralOrderCountByDate(dateNext);
				Integer success2=statisticsService.getIntegralSuccessOrderByDate(dateNext);
				Integer fail2=statisticsService.getIntegralFailOrderByDate(dateNext);
				
				orderNum.add(orderCount+orderCount2);
				amount.add(orderAmount);
				successOrder.add(success+success2);
				failOrder.add(fail+fail2);	
				
				//获取每天的订单
				List<OrderUser> orderUser=goodsSettingService.getTodayOrderUser(dateNext);
				//获取每天的积分订单
				List<IntegralOrder> integralOrder=goodsSettingService.getTodayIntegralOrder(dateNext);
				Integer commentCount=0;
				for(int j=0;j<orderUser.size();j++) {
					Comment commentBean=goodsSettingService.getCommentByOrderUserId(orderUser.get(j).getOrderUserId());
					if(commentBean!=null) {
						Integer level =commentBean.getCommentLevel().intValue();
						if(level==5) {
							commentCount++;
						}
					}
				}
				
				
				//积分订单的好评率
				Integer commentCount2=0;
				for(int j=0;j<integralOrder.size();j++) {
					//获取积分订单评论表
					IntegralComment integralComment=goodsSettingService.getIntegralCommentByOrderUserId(integralOrder.get(j).getIntegralOrderId());
					if(integralComment!=null) {
						Integer level2 =integralComment.getCommentLevel().intValue();
						if(level2==5) {
							commentCount2++;
						}
					}
				}
				String level="";
				if(commentCount==0&&commentCount2==0)
					level="0%";
				else
					level=((commentCount+commentCount2)/(orderUser.size()+integralOrder.size()))*100+"%";
				//存放好评率
				comment.add(level);
			}
			
			
			//今日销售额
			Integer orderAmount=goodsSettingService.getOrderMoneyByDate(date);
			//今日订单数
			Integer orderCount = goodsSettingService.getOrderCountByDate(date);
			//查询积分订单数据
			Integer orderCount2=statisticsService.getIntegralOrderCountByDate(date);
			
			
			// 分页
			int size = firstSearchDate.size();
			// 页数
			int pageSize = size % 7 == 0 ? size / 7 : size / 7 + 1;
			
			List<String> dateListForPage = new ArrayList<>();
			// 存放订单数
			List<Integer> orderNumList = new ArrayList<>();
			// 存放交易数额
			List<Integer> amountList = new ArrayList<>();
			// 存放成功订单数,状态为4到7为成功订单
			List<Integer> successOrderList = new ArrayList<>();
			// 存放失败订单数，状态为9为失败订单
			List<Integer> failOrderList = new ArrayList<>();
			//存放好评数
			List<String> commentList=new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 7;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				dateListForPage.add(firstSearchDate.get(i));
				orderNumList.add(orderNum.get(i));
				amountList.add(amount.get(i));
				successOrderList.add(successOrder.get(i));
				failOrderList.add(failOrder.get(i));
				commentList.add(comment.get(i));
			}
			model.addAttribute("dateListForPage", dateListForPage);
			model.addAttribute("orderNumList", orderNumList);
			model.addAttribute("amountList", amountList);
			model.addAttribute("successOrderList", successOrderList);
			model.addAttribute("failOrderList", failOrderList);
			model.addAttribute("commentList", commentList);
			model.addAttribute("date", date);
			model.addAttribute("orderCount", orderCount+orderCount2);
			model.addAttribute("orderAmount", orderAmount);
			model.addAttribute("todaySize", allOrderUserInToday.size());
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("datetimepicker1", datetimepicker1);
			model.addAttribute("datetimepicker2", datetimepicker2);
			model.addAttribute("search3", 1);
			return "management/orderStatistics";
		}
	}
	
	// 其他业务
	@RequestMapping(value = "/management/otherProject.action")
	public String otherProject(Model model, String pageNum) throws Exception {
		//获取所有存在业务表(按时间排序)
		List<OtherProject> otherProject=statisticsService.getAllOtherProjectExist();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Set<String> dateSet1=new TreeSet<>();
		for(int i=0;i<otherProject.size();i++) {
			dateSet1.add(formatter.format(otherProject.get(i).getProjectDate()));
		}
		//倒叙排序
		List<String> dateSetToList1=new ArrayList<>(dateSet1);
		List<String> dateSetToList=new ArrayList<>();
		
		for(int i=dateSetToList1.size()-1;i>=0;i--) {
			dateSetToList.add(dateSetToList1.get(i));
		}
		
		List<OtherProject> otherProjectList=new ArrayList<>();
		for(int i=0;i<dateSetToList.size();i++) {
			OtherProject otherProjectBean=new OtherProject();
			//通过日期找业务表
			List<OtherProject> projectList=statisticsService.getProjectByDate(dateSetToList.get(i));
			otherProjectBean.setProjectDate(formatter.parse(dateSetToList.get(i)));
			otherProjectBean.setOtherProject(projectList);
			otherProjectList.add(otherProjectBean);
		}
		
		// 分页
		int size = otherProjectList.size();
		System.out.println(size);
		System.out.println(otherProjectList.get(1).getOtherProject().get(0).getProjectName());
		// 页数
		int pageSize = size % 6 == 0 ? size /6 : size / 6+ 1;
		
		List<OtherProject> otherProjectForPage=new ArrayList<>();
		// 获取分页信息
		Integer page = Integer.parseInt(pageNum);
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 6, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 6;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			otherProjectForPage.add(otherProjectList.get(i));
		}
		
		model.addAttribute("otherProjectForPage", otherProjectForPage);
		model.addAttribute("size", size);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("pageSize", pageSize);
		return "management/otherProject";
	}
	
	// 添加业务
	@RequestMapping(value = "/management/addProject.action")
	public String addProject(Model model,String name,String time,String amount) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date projectTime=formatter.parse(time);
		Float projectAmount=Float.valueOf(amount);
		OtherProject project=new OtherProject();
		project.setProjectAmount(projectAmount);
		project.setProjectDate(projectTime);
		project.setProjectName(name);
		project.setIsExist("1");
		//保存
		statisticsService.saveOtherProject(project);
		
		return "redirect:/management/otherProject.action?pageNum=1";
	}
	
	// 其他业务搜素框
	@RequestMapping(value = "/management/findProject.action")
	public String findProject(Model model,String pageNum, String datetimepicker1, String datetimepicker2) throws Exception {
		//获取所有存在业务表(按时间排序)
		List<OtherProject> otherProject=statisticsService.getAllOtherProjectExist();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Set<String> dateSet1=new TreeSet<>();
		for(int i=0;i<otherProject.size();i++) {
			dateSet1.add(formatter.format(otherProject.get(i).getProjectDate()));
		}
		//倒叙排序
		List<String> dateSetToList1=new ArrayList<>(dateSet1);
		List<String> dateSetToList2=new ArrayList<>();
		
		for(int i=dateSetToList1.size()-1;i>=0;i--) {
			dateSetToList2.add(dateSetToList1.get(i));
		}
		
		//第一个搜索框不为空
		if(datetimepicker1!=""&&datetimepicker2=="") {
			List<String> dateSetToList=new ArrayList<>();
			int flag=0;
			for(int i=0;i<dateSetToList2.size();i++) {
				Date date1=formatter.parse(datetimepicker1);
				Date date2=formatter.parse(dateSetToList2.get(i));
				if(date1.getTime()<=date2.getTime()) {
					dateSetToList.add(dateSetToList2.get(i));
				}
				if(date1.getTime()>date2.getTime()) {
					flag++;
				}
			}
			//如果不在时间范围
			if(flag==dateSetToList2.size()) {
				model.addAttribute("size", 0);
				return "management/otherProject";
			}else {
			
			List<OtherProject> otherProjectList=new ArrayList<>();
			for(int i=0;i<dateSetToList.size();i++) {
				OtherProject otherProjectBean=new OtherProject();
				//通过日期找业务表
				List<OtherProject> projectList=statisticsService.getProjectByDate(dateSetToList.get(i));
				otherProjectBean.setProjectDate(formatter.parse(dateSetToList.get(i)));
				otherProjectBean.setOtherProject(projectList);
				otherProjectList.add(otherProjectBean);
			}
			
			// 分页
			int size = otherProjectList.size();
			System.out.println(size);
			System.out.println(otherProjectList.get(1).getOtherProject().get(0).getProjectName());
			// 页数
			int pageSize = size % 6 == 0 ? size /6 : size / 6+ 1;
			
			List<OtherProject> otherProjectForPage=new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 6, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 6;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				otherProjectForPage.add(otherProjectList.get(i));
			}
			
			model.addAttribute("otherProjectForPage", otherProjectForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("datetimepicker1", datetimepicker1);
			model.addAttribute("search1", 1);
			return "management/otherProject";
			}
		}
		//第二个搜索框不为空
		if(datetimepicker1==""&&datetimepicker2!="") {
			List<String> dateSetToList=new ArrayList<>();
			int flag=0;
			for(int i=0;i<dateSetToList2.size();i++) {
				Date date1=formatter.parse(datetimepicker2);
				Date date2=formatter.parse(dateSetToList2.get(i));
				if(date1.getTime()>=date2.getTime()) {
					dateSetToList.add(dateSetToList2.get(i));
				}
				if(date1.getTime()<date2.getTime()) {
					flag++;
				}
			}
			//如果不在时间范围
			if(flag==dateSetToList2.size()) {
				model.addAttribute("size", 0);
				return "management/otherProject";
			}else {
			
			List<OtherProject> otherProjectList=new ArrayList<>();
			for(int i=0;i<dateSetToList.size();i++) {
				OtherProject otherProjectBean=new OtherProject();
				//通过日期找业务表
				List<OtherProject> projectList=statisticsService.getProjectByDate(dateSetToList.get(i));
				otherProjectBean.setProjectDate(formatter.parse(dateSetToList.get(i)));
				otherProjectBean.setOtherProject(projectList);
				otherProjectList.add(otherProjectBean);
			}
			
			// 分页
			int size = otherProjectList.size();
			System.out.println(size);
			System.out.println(otherProjectList.get(1).getOtherProject().get(0).getProjectName());
			// 页数
			int pageSize = size % 6 == 0 ? size /6 : size / 6+ 1;
			
			List<OtherProject> otherProjectForPage=new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 6, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 6;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				otherProjectForPage.add(otherProjectList.get(i));
			}
			
			model.addAttribute("otherProjectForPage", otherProjectForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("datetimepicker2", datetimepicker1);
			model.addAttribute("search2", 1);
			return "management/otherProject";
		}
		}
		//都不为空
		else{
			List<String> dateSetToList=new ArrayList<>();
			int flag=0;
			for(int i=0;i<dateSetToList2.size();i++) {
				Date date1=formatter.parse(datetimepicker1);
				Date date2=formatter.parse(datetimepicker2);
				Date date3=formatter.parse(dateSetToList2.get(i));
				if(date1.getTime()<=date3.getTime()&&date2.getTime()>=date3.getTime()) {
					dateSetToList.add(dateSetToList2.get(i));
				}
				if(date1.getTime()>date3.getTime()||date2.getTime()<date3.getTime()) {
					flag++;
				}
			}
			//如果不在时间范围
			if(flag==dateSetToList2.size()) {
				model.addAttribute("size", 0);
				return "management/otherProject";
			}else {
			List<OtherProject> otherProjectList=new ArrayList<>();
			for(int i=0;i<dateSetToList.size();i++) {
				OtherProject otherProjectBean=new OtherProject();
				//通过日期找业务表
				List<OtherProject> projectList=statisticsService.getProjectByDate(dateSetToList.get(i));
				otherProjectBean.setProjectDate(formatter.parse(dateSetToList.get(i)));
				otherProjectBean.setOtherProject(projectList);
				otherProjectList.add(otherProjectBean);
			}
			
			// 分页
			int size = otherProjectList.size();
			// 页数
			int pageSize = size % 6 == 0 ? size /6 : size / 6+ 1;
			
			List<OtherProject> otherProjectForPage=new ArrayList<>();
			// 获取分页信息
			Integer page = Integer.parseInt(pageNum);
			PageBeanInOrder pageBean = new PageBeanInOrder(page, 6, size);
			// 结束索引
			int end = pageBean.getStartIndex() + 6;
			if (end > size) {
				end = size;
			}
			for (int i = pageBean.getStartIndex(); i < end; i++) {
				otherProjectForPage.add(otherProjectList.get(i));
			}
			
			model.addAttribute("otherProjectForPage", otherProjectForPage);
			model.addAttribute("size", size);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("datetimepicker1", datetimepicker1);
			model.addAttribute("datetimepicker2", datetimepicker2);
			model.addAttribute("search3", 1);
			return "management/otherProject";
		}
		}
		
	}
	
	// 删除业务
	@RequestMapping(value = "/management/deleteProject.action")
	public String deleteProject(Model model,String id) {
		Integer opId=Integer.parseInt(id);
		//删除业务
		statisticsService.deleteProject(opId);
		
		return "redirect:management/otherProject.action?pageNum=1";
	}
	
	// 修改业务
	@RequestMapping(value = "/management/alterProject.action")
	public String alterProject(Model model,String id,String name,String time,String amount) throws Exception {
		Integer opId=Integer.parseInt(id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date projectTime=formatter.parse(time);
		Float projectAmount=Float.valueOf(amount);
		OtherProject project=new OtherProject();
		project.setProjectAmount(projectAmount);
		project.setProjectDate(projectTime);
		project.setProjectName(name);
		//修改
		statisticsService.alterOtherProject(opId,project);
		
		
		return "redirect:/management/otherProject.action?pageNum=1";
	}
	
	// 定期结算
	@RequestMapping(value = "/management/termSettlement.action")
	public String termSettlement(Model model,String pageNum,String count,String count2){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//获取所有存在业务表(按时间排序)
		List<OtherProject> otherProject=statisticsService.getAllOtherProjectExist();
		//获取所有订单
		List<OrderUser> allOrderUser=statisticsService.getAllOrderUser();
		
		Set<String> dateSet1=new TreeSet<>();
		for(int i=0;i<otherProject.size();i++) {
			dateSet1.add(formatter.format(otherProject.get(i).getProjectDate()));
		}
		for(int i=0;i<allOrderUser.size();i++) {
			dateSet1.add(formatter.format(allOrderUser.get(i).getOrderTime()));
		}
		//倒叙排序
		List<String> dateSetToList1=new ArrayList<>(dateSet1);
		List<String> dateSetToList=new ArrayList<>();
		
		for(int i=dateSetToList1.size()-1;i>=0;i--) {
			dateSetToList.add(dateSetToList1.get(i));
		}
		
		List<Integer> orderAmountList=new ArrayList<>();
		List<Integer> otherProjectAmountList=new ArrayList<>();
		for(int i=0;i<dateSetToList.size();i++) {
			//获取每日订单受益
			Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateSetToList.get(i));
			//获取每日的业务消费
			Integer otherProjectAmount=statisticsService.getOtherProjectAmountByDate(dateSetToList.get(i));
			orderAmountList.add(orderAmount);
			otherProjectAmountList.add(otherProjectAmount);
		}
		
		// 分页
		int size = dateSetToList.size();
		// 页数
		int pageSize = size % 7 == 0 ? size /7 : size / 7+ 1;
		
		List<Integer> orderAmountListForPage=new ArrayList<>();
		List<String> dateSetToListForPage=new ArrayList<>();
		List<Integer> otherProjectAmountListForPage=new ArrayList<>();
		// 获取分页信息
		Integer page = Integer.parseInt(pageNum);
		PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
		// 结束索引
		int end = pageBean.getStartIndex() + 7;
		if (end > size) {
			end = size;
		}
		for (int i = pageBean.getStartIndex(); i < end; i++) {
			orderAmountListForPage.add(orderAmountList.get(i));
			dateSetToListForPage.add(dateSetToList.get(i));
			otherProjectAmountListForPage.add(otherProjectAmountList.get(i));
		}
		
		String account="";
		if(count==null) {
			account="0";
		}else {
			account=count;
		}
		String account2="";
		if(count2==null) {
			account2="0";
		}else {
			account2=count2;
		}
		
		model.addAttribute("orderAmountListForPage", orderAmountListForPage);
		model.addAttribute("otherProjectAmountListForPage", otherProjectAmountListForPage);
		model.addAttribute("size", size);
		model.addAttribute("account", account);
		model.addAttribute("account2", account2);
		model.addAttribute("dateSetToListForPage", dateSetToListForPage);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("pageSize", pageSize);
		
		return "management/termSettlement";
	}
	
	// 定期结算搜素框
	@RequestMapping(value = "/management/findtermSettlement.action")
	public String findtermSettlement(Model model,String pageNum, String datetimepicker1, String datetimepicker2) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//获取所有存在业务表(按时间排序)
		List<OtherProject> otherProject=statisticsService.getAllOtherProjectExist();
		//获取所有订单
		List<OrderUser> allOrderUser=statisticsService.getAllOrderUser();
		
		Set<String> dateSet1=new TreeSet<>();
		for(int i=0;i<otherProject.size();i++) {
			dateSet1.add(formatter.format(otherProject.get(i).getProjectDate()));
		}
		for(int i=0;i<allOrderUser.size();i++) {
			dateSet1.add(formatter.format(allOrderUser.get(i).getOrderTime()));
		}
		//倒叙排序
		List<String> dateSetToList1=new ArrayList<>(dateSet1);
		List<String> dateSetToList=new ArrayList<>();
		
		for(int i=dateSetToList1.size()-1;i>=0;i--) {
			dateSetToList.add(dateSetToList1.get(i));
		}
		
		if(datetimepicker1!=""&&datetimepicker2=="") {
			List<String> dateList=new ArrayList<>();
			int flag=0;
			for(int i=0;i<dateSetToList.size();i++) {
				Date date1=formatter.parse(datetimepicker1);
				Date date2=formatter.parse(dateSetToList.get(i));
				if(date1.getTime()<=date2.getTime()) {
					dateList.add(dateSetToList.get(i));
				}
				if(date1.getTime()>date2.getTime()) {
					flag++;
				}
			}
			//如果不在时间范围
			if(flag==dateSetToList.size()) {
				model.addAttribute("size", 0);
				return "management/termSettlement";
			}else {
				List<Integer> orderAmountList=new ArrayList<>();
				List<Integer> otherProjectAmountList=new ArrayList<>();
				for(int i=0;i<dateList.size();i++) {
					//获取每日订单受益
					Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateSetToList.get(i));
					//获取每日的业务消费
					Integer otherProjectAmount=statisticsService.getOtherProjectAmountByDate(dateSetToList.get(i));
					orderAmountList.add(orderAmount);
					otherProjectAmountList.add(otherProjectAmount);
				}
				
				// 分页
				int size = dateList.size();
				// 页数
				int pageSize = size % 9 == 0 ? size /9 : size / 9+ 1;
				
				List<Integer> orderAmountListForPage=new ArrayList<>();
				List<String> dateSetToListForPage=new ArrayList<>();
				List<Integer> otherProjectAmountListForPage=new ArrayList<>();
				// 获取分页信息
				Integer page = Integer.parseInt(pageNum);
				PageBeanInOrder pageBean = new PageBeanInOrder(page, 9, size);
				// 结束索引
				int end = pageBean.getStartIndex() + 9;
				if (end > size) {
					end = size;
				}
				for (int i = pageBean.getStartIndex(); i < end; i++) {
					orderAmountListForPage.add(orderAmountList.get(i));
					dateSetToListForPage.add(dateList.get(i));
					otherProjectAmountListForPage.add(otherProjectAmountList.get(i));
				}
				
				model.addAttribute("orderAmountListForPage", orderAmountListForPage);
				model.addAttribute("otherProjectAmountListForPage", otherProjectAmountListForPage);
				model.addAttribute("size", size);
				model.addAttribute("dateSetToListForPage", dateSetToListForPage);
				model.addAttribute("pageBean", pageBean);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("datetimepicker1", datetimepicker1);
				model.addAttribute("search1", 1);
				
				return "management/termSettlement";
			}
		}
			//第二个搜索框不为空
			if(datetimepicker1==""&&datetimepicker2!="") {
				List<String> dateList=new ArrayList<>();
				int flag=0;
				for(int i=0;i<dateSetToList.size();i++) {
					Date date1=formatter.parse(datetimepicker2);
					Date date2=formatter.parse(dateSetToList.get(i));
					if(date1.getTime()>=date2.getTime()) {
						dateList.add(dateSetToList.get(i));
					}
					if(date1.getTime()<date2.getTime()) {
						flag++;
					}
				}
				//如果不在时间范围
				if(flag==dateSetToList.size()) {
					model.addAttribute("size", 0);
					return "management/termSettlement";
				}else {
					List<Integer> orderAmountList=new ArrayList<>();
					List<Integer> otherProjectAmountList=new ArrayList<>();
					for(int i=0;i<dateList.size();i++) {
						//获取每日订单受益
						Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateSetToList.get(i));
						//获取每日的业务消费
						Integer otherProjectAmount=statisticsService.getOtherProjectAmountByDate(dateSetToList.get(i));
						orderAmountList.add(orderAmount);
						otherProjectAmountList.add(otherProjectAmount);
					}
					
					// 分页
					int size = dateList.size();
					// 页数
					int pageSize = size % 9 == 0 ? size /9 : size / 9+ 1;
					
					List<Integer> orderAmountListForPage=new ArrayList<>();
					List<String> dateSetToListForPage=new ArrayList<>();
					List<Integer> otherProjectAmountListForPage=new ArrayList<>();
					// 获取分页信息
					Integer page = Integer.parseInt(pageNum);
					PageBeanInOrder pageBean = new PageBeanInOrder(page, 9, size);
					// 结束索引
					int end = pageBean.getStartIndex() + 9;
					if (end > size) {
						end = size;
					}
					for (int i = pageBean.getStartIndex(); i < end; i++) {
						orderAmountListForPage.add(orderAmountList.get(i));
						dateSetToListForPage.add(dateList.get(i));
						otherProjectAmountListForPage.add(otherProjectAmountList.get(i));
					}
					
					model.addAttribute("orderAmountListForPage", orderAmountListForPage);
					model.addAttribute("otherProjectAmountListForPage", otherProjectAmountListForPage);
					model.addAttribute("size", size);
					model.addAttribute("dateSetToListForPage", dateSetToListForPage);
					model.addAttribute("pageBean", pageBean);
					model.addAttribute("pageSize", pageSize);
					model.addAttribute("datetimepicker2", datetimepicker2);
					model.addAttribute("search2", 1);
					return "management/termSettlement";
				}
		}
			//都不为空
			else{
				List<String> dateList=new ArrayList<>();
				int flag=0;
				for(int i=0;i<dateSetToList.size();i++) {
					Date date1=formatter.parse(datetimepicker1);
					Date date2=formatter.parse(datetimepicker2);
					Date date3=formatter.parse(dateSetToList.get(i));
					if(date1.getTime()<=date3.getTime()&&date2.getTime()>=date3.getTime()) {
						dateList.add(dateSetToList.get(i));
					}
					if(date1.getTime()>date3.getTime()||date2.getTime()<date3.getTime()) {
						flag++;
					}
				}
				//如果不在时间范围
				if(flag==dateSetToList.size()) {
					model.addAttribute("size", 0);
					return "management/termSettlement";
				}else {
					List<Integer> orderAmountList=new ArrayList<>();
					List<Integer> otherProjectAmountList=new ArrayList<>();
					for(int i=0;i<dateList.size();i++) {
						//获取每日订单受益
						Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateSetToList.get(i));
						//获取每日的业务消费
						Integer otherProjectAmount=statisticsService.getOtherProjectAmountByDate(dateSetToList.get(i));
						orderAmountList.add(orderAmount);
						otherProjectAmountList.add(otherProjectAmount);
					}
					
					// 分页
					int size = dateList.size();
					// 页数
					int pageSize = size % 7 == 0 ? size /7 : size / 7+ 1;
					
					List<Integer> orderAmountListForPage=new ArrayList<>();
					List<String> dateSetToListForPage=new ArrayList<>();
					List<Integer> otherProjectAmountListForPage=new ArrayList<>();
					// 获取分页信息
					Integer page = Integer.parseInt(pageNum);
					PageBeanInOrder pageBean = new PageBeanInOrder(page, 7, size);
					// 结束索引
					int end = pageBean.getStartIndex() + 7;
					if (end > size) {
						end = size;
					}
					for (int i = pageBean.getStartIndex(); i < end; i++) {
						orderAmountListForPage.add(orderAmountList.get(i));
						dateSetToListForPage.add(dateList.get(i));
						otherProjectAmountListForPage.add(otherProjectAmountList.get(i));
					}
					
					model.addAttribute("orderAmountListForPage", orderAmountListForPage);
					model.addAttribute("otherProjectAmountListForPage", otherProjectAmountListForPage);
					model.addAttribute("size", size);
					model.addAttribute("dateSetToListForPage", dateSetToListForPage);
					model.addAttribute("pageBean", pageBean);
					model.addAttribute("pageSize", pageSize);
					model.addAttribute("datetimepicker1", datetimepicker1);
					model.addAttribute("datetimepicker2", datetimepicker2);
					model.addAttribute("search3", 1);
					return "management/termSettlement";
			}
		}
	}
	
	
	// 结算搜素框
	@RequestMapping(value = "/management/settlement.action")
	public @ResponseBody
	String settlement(@RequestBody String date) throws Exception{
		String[] data=date.split("/");
		String datetimepicker1=data[0];
		String datetimepicker2=data[1];
		System.out.println(datetimepicker1+"-----"+datetimepicker2);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy");
		//获取所有存在业务表(按时间排序)
		List<OtherProject> otherProject=statisticsService.getAllOtherProjectExist();
		//获取所有订单
		List<OrderUser> allOrderUser=statisticsService.getAllOrderUser();
		
		Set<String> dateSet1=new TreeSet<>();
		for(int i=0;i<otherProject.size();i++) {
			dateSet1.add(formatter.format(otherProject.get(i).getProjectDate()));
		}
		for(int i=0;i<allOrderUser.size();i++) {
			dateSet1.add(formatter.format(allOrderUser.get(i).getOrderTime()));
		}
		//倒叙排序
		List<String> dateSetToList1=new ArrayList<>(dateSet1);
		List<String> dateSetToList=new ArrayList<>();
		
		for(int i=dateSetToList1.size()-1;i>=0;i--) {
			dateSetToList.add(dateSetToList1.get(i));
		}
		
		//证明按月查询
		if(datetimepicker1.length()==7) {
			List<String> dateList=new ArrayList<>();
			int flag=0;
			for(int i=0;i<dateSetToList.size();i++) {
				Date date1=formatter2.parse(datetimepicker1);
				Date date2=formatter2.parse(datetimepicker2);
				Date date3=formatter2.parse(dateSetToList.get(i));
				if(date1.getTime()<=date3.getTime()&&date2.getTime()>=date3.getTime()) {
					dateList.add(dateSetToList.get(i));
				}
				else{
					flag++;
				}
			}
			//如果不在时间范围
			if(flag==dateSetToList.size()) {
				int i=0;
				return "count1="+i;
			}else {
				int orderAmountMoney=0;
				int projectAmountMoney=0;
				for(int i=0;i<dateList.size();i++) {
					//获取每日订单受益
					Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateSetToList.get(i));
					//获取每日的业务消费
					Integer otherProjectAmount=statisticsService.getOtherProjectAmountByDate(dateSetToList.get(i));
					orderAmountMoney+=orderAmount;
					projectAmountMoney+=otherProjectAmount;
				}
				
				int money=orderAmountMoney-projectAmountMoney;
				return "count1="+money;
				
			}
		}
		//按年结算
		if(datetimepicker1.length()==4) {
			List<String> dateList=new ArrayList<>();
			int flag=0;
			for(int i=0;i<dateSetToList.size();i++) {
				Date date1=formatter3.parse(datetimepicker1);
				Date date2=formatter3.parse(datetimepicker2);
				Date date3=formatter3.parse(dateSetToList.get(i));
				if(date1.getTime()<=date3.getTime()&&date2.getTime()>=date3.getTime()) {
					dateList.add(dateSetToList.get(i));
				}
				else{
					flag++;
				}
			}
			//如果不在时间范围
			if(flag==dateSetToList.size()) {
				int i=0;
				return "count2="+i;
			}else {
				int orderAmountMoney=0;
				int projectAmountMoney=0;
				for(int i=0;i<dateList.size();i++) {
					//获取每日订单受益
					Integer orderAmount = goodsSettingService.getOrderMoneyByDate(dateSetToList.get(i));
					//获取每日的业务消费
					Integer otherProjectAmount=statisticsService.getOtherProjectAmountByDate(dateSetToList.get(i));
					orderAmountMoney+=orderAmount;
					projectAmountMoney+=otherProjectAmount;
				}
				
				int money=orderAmountMoney-projectAmountMoney;
				return "count2="+money;
				
			}
		}
	
		
		return "count1=0";
	}
	
	
	
	
}
