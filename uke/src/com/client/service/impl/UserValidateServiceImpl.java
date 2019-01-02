package com.client.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.dao.GoodsMapper;
import com.client.dao.GoodstypeMapper;
import com.client.dao.UserinfoMapper;
import com.client.entity.Goods;
import com.client.entity.GoodsExample;
import com.client.entity.Goodstype;
import com.client.entity.Userinfo;
import com.client.entity.UserinfoExample;
import com.client.service.UserValidateService;
import com.md5Util.MD5Util;
@Service
public class UserValidateServiceImpl implements UserValidateService{

	@Autowired
	private UserinfoMapper userMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodstypeMapper goodstypeMapper;
	
	
	@Override
	public List<Userinfo> selectUserByPhoneAndPassword(String phone, String password) {
		UserinfoExample userExample=new UserinfoExample();
		UserinfoExample.Criteria criteria =userExample.createCriteria();
		//加密比较
		MD5Util md5=new MD5Util();
		String md5Password=md5.string2MD5(password);
		criteria.andUserPhoneEqualTo(phone).andUserPasswordEqualTo(md5Password);
		return userMapper.selectByExample(userExample);
	}
	
	@Override
	public List<Userinfo> selectUserByName(String name) {
		UserinfoExample userExample=new UserinfoExample();
		UserinfoExample.Criteria criteria=userExample.createCriteria();
		criteria.andUserNameEqualTo(name);
		return userMapper.selectByExample(userExample);
	}
	
	@Override
	public List<Userinfo> selectUserByEmail(String email) {
		UserinfoExample userExample=new UserinfoExample();
		UserinfoExample.Criteria criteria=userExample.createCriteria();
		criteria.andUserEmailEqualTo(email);
		return userMapper.selectByExample(userExample);
	}
	
	@Override
	public List<Userinfo> selectUserByPhone(String phone) {
		UserinfoExample userExample=new UserinfoExample();
		UserinfoExample.Criteria criteria=userExample.createCriteria();
		criteria.andUserPhoneEqualTo(phone);
		return userMapper.selectByExample(userExample);
	}

	@Override
	public void setPassword(String password,String phone) {
		UserinfoExample userExample=new UserinfoExample();
		UserinfoExample.Criteria criteria=userExample.createCriteria();
		criteria.andUserPhoneEqualTo(phone);
		List<Userinfo> userList=userMapper.selectByExample(userExample);
		//加密
		MD5Util md5=new MD5Util();
		String md5Password=md5.string2MD5(password);
		userList.get(0).setUserPassword(md5Password);
		userMapper.updateByPrimaryKey(userList.get(0));
	}

	@Override
	public void saveUserInfo(Userinfo userinfo) {
		String password=userinfo.getUserPassword();
		//加密
		MD5Util md5=new MD5Util();
		String md5Password=md5.string2MD5(password);
		userinfo.setUserPassword(md5Password);
		
		userinfo.setUserPhoto("头像.jpg");
		//获取注册时间
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    //date dateNowStr = sdf.format(date); 
	    //初始化积分
	    userinfo.setUserIntegral(0);

	    userinfo.setRegisterTime(date);
	    userinfo.setLastLoginTime(date);
		userMapper.insert(userinfo);
	}

	//获取高销量商品
	@Override
	public List<Goods> MaxSaleVolume() {
		GoodsExample goodsExample=new GoodsExample();
		GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andGoodsIsSaleEqualTo("1").andGoodsInventoryNotEqualTo(0);
		//获取全部的商品
		List<Goods> list=goodsMapper.selectByExample(goodsExample);
		//商品数量
		long l = goodsMapper.countByExample(goodsExample);
		//定义一个list用来存储数据
		List<Goods> maxList=new ArrayList<>();
		
		Collections.sort(list,new Comparator<Goods>(){
            public int compare(Goods arg0, Goods arg1) {
                return arg0.getGoodsSalesVolume().compareTo(arg1.getGoodsSalesVolume());
            }
        });
		
		//取前面九个对象
		for(int i=(int) l-9;i<l;i++) {
			maxList.add(list.get(i));
		}
		
		return maxList;
}

	//获取商品类型
	@Override
	public Goodstype getGoodsType(Integer goodsTypeId) {
		Goodstype goodstype2 = goodstypeMapper.selectByPrimaryKey(goodsTypeId);
		return goodstype2;
	}

	//更新登录时间
	@Override
	public void updateLoginTime(Userinfo userinfo) {
		// TODO Auto-generated method stub
		userinfo.setLastLoginTime(new Date());
		userMapper.updateByPrimaryKey(userinfo);
	}
	
	
	
}
