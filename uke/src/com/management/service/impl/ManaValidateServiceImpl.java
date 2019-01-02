package com.management.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dao.ManagerinfoMapper;
import com.management.entity.Managerinfo;
import com.management.entity.ManagerinfoExample;
import com.management.service.ManaValidateService;
import com.md5Util.MD5Util;
@Service
public class ManaValidateServiceImpl implements ManaValidateService{

	@Autowired
	private ManagerinfoMapper manaMapper;

	@Override
	public List<Managerinfo> selectUserByPhoneAndPassword(String phone, String password) {
		ManagerinfoExample manaExample=new ManagerinfoExample();
		ManagerinfoExample.Criteria criteria=manaExample.createCriteria();
		//加密比较
		MD5Util md5=new MD5Util();
		String md5Password=md5.string2MD5(password);
		
		criteria.andManagerPhoneEqualTo(phone).andManagerPasswordEqualTo(md5Password);
		return manaMapper.selectByExample(manaExample);
	}

	@Override
	public List<Managerinfo> selectUserByName(String name) {
		ManagerinfoExample manaExample=new ManagerinfoExample();
		ManagerinfoExample.Criteria criteria=manaExample.createCriteria();
		criteria.andManagerNameEqualTo(name);
		return manaMapper.selectByExample(manaExample);
	}

	@Override
	public List<Managerinfo> selectUserByPhone(String phone) {
		ManagerinfoExample manaExample=new ManagerinfoExample();
		ManagerinfoExample.Criteria criteria=manaExample.createCriteria();
		criteria.andManagerPhoneEqualTo(phone);
		return manaMapper.selectByExample(manaExample);
	}

	@Override
	public void setPassword(String password, String phone) {
		ManagerinfoExample manaExample=new ManagerinfoExample();
		ManagerinfoExample.Criteria criteria=manaExample.createCriteria();
		criteria.andManagerPhoneEqualTo(phone);
		List<Managerinfo> userList=manaMapper.selectByExample(manaExample);
		//加密
		MD5Util md5=new MD5Util();
		String md5Password=md5.string2MD5(password);
		userList.get(0).setManagerPassword(md5Password);
		manaMapper.updateByPrimaryKey(userList.get(0));		
	}

	@Override
	public void saveManaInfo(Managerinfo manaInfo) {
		String password=manaInfo.getManagerPassword();
		//加密
		MD5Util md5=new MD5Util();
		String md5Password=md5.string2MD5(password);
		manaInfo.setManagerPassword(md5Password);
		manaInfo.setManagerPhoto("头像.jpg");
		
		manaMapper.insert(manaInfo);
	}

	//设置登录时间
	@Override
	public void setLoginTime(Integer managerId) {
		Managerinfo managerinfo = manaMapper.selectByPrimaryKey(managerId);
		managerinfo.setLoginTime(new Date());
		manaMapper.updateByPrimaryKey(managerinfo);
	}
	
}
