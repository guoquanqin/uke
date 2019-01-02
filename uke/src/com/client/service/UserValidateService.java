package com.client.service;

import java.util.List;

import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.Userinfo;

public interface UserValidateService {

	//验证手机号和密码
	List<Userinfo> selectUserByPhoneAndPassword(String phone, String password);

	//查看用户名是否存在
	List<Userinfo> selectUserByName(String name);

	//查看email是否存在
	List<Userinfo> selectUserByEmail(String email);

	//查看手机号是否存在
	List<Userinfo> selectUserByPhone(String phone);

	//设置新密码
	void setPassword(String password,String phone);

	//提加用户
	void saveUserInfo(Userinfo userinfo);

	//获取9个销量高的商品
	List<Goods> MaxSaleVolume();

	//获取类型
	Goodstype getGoodsType(Integer goodsType);

	//更新登录时间
	void updateLoginTime(Userinfo userinfo);
}
