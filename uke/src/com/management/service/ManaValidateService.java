package com.management.service;

import java.util.List;

import com.client.entity.OrderUser;
import com.client.entity.Userinfo;
import com.client.entity.UserinfoExample;
import com.management.entity.Managerinfo;

public interface ManaValidateService {

	//验证登录账号密码
	List<Managerinfo> selectUserByPhoneAndPassword(String phone, String password);

	//查看用户名是否存在
	List<Managerinfo> selectUserByName(String name);

	//查看手机号码是否存在
	List<Managerinfo> selectUserByPhone(String phone);

	//修改密码
	void setPassword(String password, String phone);

	//保存数据
	void saveManaInfo(Managerinfo manaInfo);

	//设置登录时间
	void setLoginTime(Integer managerId);


	
}
