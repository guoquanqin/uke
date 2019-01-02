package com.management.service;

import java.util.List;

import com.client.entity.IntegralOrder;
import com.client.entity.OrderUser;
import com.management.entity.OtherProject;

public interface StatisticsService {

	//获取所有订单
	List<OrderUser> getAllOrderUser();

	//获取所有积分订单
	List<IntegralOrder> getAllIntegralOrderList();

	//积分订单数
	Integer getIntegralOrderCountByDate(String dateNext);
	//积分订单成功数
	Integer getIntegralSuccessOrderByDate(String dateNext);
	//积分订单失败单数
	Integer getIntegralFailOrderByDate(String dateNext);

	//获取所有存在业务表(按时间排序)
	List<OtherProject> getAllOtherProjectExist();

	//通过日期找业务表
	List<OtherProject> getProjectByDate(String string);

	//保存其他业务
	void saveOtherProject(OtherProject project);

	//删除业务
	void deleteProject(Integer opId);

	//修改业务信息
	void alterOtherProject(Integer opId, OtherProject project);

	//获取每日的业务消费
	Integer getOtherProjectAmountByDate(String string);

}
