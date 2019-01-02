package com.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.dao.GoodsMapper;
import com.client.dao.IntegralOrderMapper;
import com.client.dao.OrderUserMapper;
import com.client.dao.ShopMapper;
import com.client.entity.IntegralOrder;
import com.client.entity.IntegralOrderExample;
import com.client.entity.OrderUser;
import com.client.entity.OrderUserExample;
import com.management.dao.OtherProjectMapper;
import com.management.entity.OtherProject;
import com.management.entity.OtherProjectExample;
import com.management.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	private OrderUserMapper orderUserMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private IntegralOrderMapper integralOrderMapper;
	@Autowired
	private OtherProjectMapper otherProjectMapper;
	
	//获取所有订单
	@Override
	public List<OrderUser> getAllOrderUser() {
		OrderUserExample example=new OrderUserExample();
		OrderUserExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("order_time desc");
		criteria.andOrderUserIdIsNotNull();
		return orderUserMapper.selectByExample(example);
	}

	//获取所有积分订单
	@Override
	public List<IntegralOrder> getAllIntegralOrderList() {
		IntegralOrderExample example=new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("order_time desc");
		criteria.andIntegralOrderIdIsNotNull();
		return integralOrderMapper.selectByExample(example);
	}

	//积分订单数
	@Override
	public Integer getIntegralOrderCountByDate(String dateNext) {
		IntegralOrderExample example=new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		criteria.andIntegralOrderIdIsNotNull();
		List<IntegralOrder> selectByExample = integralOrderMapper.selectByExample(example);
		
		int count=0;
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=selectByExample.get(i).getOrderTime().substring(0, 10);
			if(dateNext.equals(orderTime)) {
				count++;
			}
		}
		return count;
	}

	//积分订单成功数
	@Override
	public Integer getIntegralSuccessOrderByDate(String dateNext) {
		IntegralOrderExample example=new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		criteria.andOrderStatusBetween((short)4,(short)7);
		List<IntegralOrder> selectByExample = integralOrderMapper.selectByExample(example);
		
		int count=0;
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=selectByExample.get(i).getOrderTime().substring(0, 10);
			if(dateNext.equals(orderTime)) {
				count++;
			}
		}
		return count;
	}

	

	//积分订单失败单数
	@Override
	public Integer getIntegralFailOrderByDate(String dateNext) {
		IntegralOrderExample example=new IntegralOrderExample();
		IntegralOrderExample.Criteria criteria=example.createCriteria();
		criteria.andOrderStatusEqualTo((short)9);
		List<IntegralOrder> selectByExample = integralOrderMapper.selectByExample(example);
		
		int count=0;
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=selectByExample.get(i).getOrderTime().substring(0, 10);
			if(dateNext.equals(orderTime)) {
				count++;
			}
		}
		return count;
	}

	//获取所有存在业务表(按时间排序)
	@Override
	public List<OtherProject> getAllOtherProjectExist() {
		OtherProjectExample example=new OtherProjectExample();
		OtherProjectExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("project_date desc");
		criteria.andIsExistEqualTo("1");
		return otherProjectMapper.selectByExample(example);
	}

	//通过日期找业务表
	@Override
	public List<OtherProject> getProjectByDate(String string) {
		OtherProjectExample example=new OtherProjectExample();
		OtherProjectExample.Criteria criteria=example.createCriteria();
		criteria.andIsExistEqualTo("1");
		List<OtherProject> selectByExample =otherProjectMapper.selectByExample(example);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<OtherProject> list=new ArrayList<>();
		for(int i=0;i<selectByExample.size();i++) {
			String orderTime=formatter.format(selectByExample.get(i).getProjectDate());
			if(string.equals(orderTime)) {
				list.add(selectByExample.get(i));
			}
		}
		return list;
	}

	//保存其他业务
	@Override
	public void saveOtherProject(OtherProject project) {
		otherProjectMapper.insert(project);
	}

	//删除业务
	@Override
	public void deleteProject(Integer opId) {
		OtherProject project = otherProjectMapper.selectByPrimaryKey(opId);
		project.setIsExist("0");
		otherProjectMapper.updateByPrimaryKey(project);
	}

	//修改业务信息
	@Override
	public void alterOtherProject(Integer opId, OtherProject project) {
		OtherProject otherpProject = otherProjectMapper.selectByPrimaryKey(opId);
		otherpProject.setProjectAmount(project.getProjectAmount());
		otherpProject.setProjectDate(project.getProjectDate());
		otherpProject.setProjectName(project.getProjectName());
		otherProjectMapper.updateByPrimaryKey(otherpProject);
	}

	//获取每日的业务消费
	@Override
	public Integer getOtherProjectAmountByDate(String string) {
		OtherProjectExample example=new OtherProjectExample();
		OtherProjectExample.Criteria criteria=example.createCriteria();
		criteria.andIsExistEqualTo("1");
		List<OtherProject> selectByExample = otherProjectMapper.selectByExample(example);
		
		int money=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<selectByExample.size();i++) {
			String time=formatter.format(selectByExample.get(i).getProjectDate());
			if(string.equals(time)) {
				money+=selectByExample.get(i).getProjectAmount();
			}
		}
		return money;
	}

}
