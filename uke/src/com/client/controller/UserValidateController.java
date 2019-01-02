package com.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyunCode.VerificationCode;
import com.client.entity.Goods;
import com.client.entity.Goodstype;
import com.client.entity.Userinfo;
import com.client.service.UserValidateService;
import com.management.entity.Store;
import com.management.service.SysManagementService;

@Controller
public class UserValidateController {
	@Autowired
	private UserValidateService userService;
	@Autowired
	private SysManagementService sysManagementService;
	
	//登录验证
	@RequestMapping(value="/client/userLogin.action")
	public String userLogin(Model model,Userinfo userinfo,HttpSession httpSession){
		//从数据库获取用户的账号密码
		List<Userinfo> isExistUser=userService.selectUserByPhoneAndPassword(userinfo.getUserPhone(),userinfo.getUserPassword());
		//验证黑名单客户
		List<Userinfo> blackList = sysManagementService.getBlackList();
		//如果不存在此用户，返回登录页面
		if(isExistUser.size()==0) {
			model.addAttribute("mess","您输入的账号密码有误，请重新确认");
			return "client/login";
		}
		
		for(int i=0;i<blackList.size();i++) {
			if(isExistUser.get(0).getUserId()==blackList.get(i).getUserId()) {
				model.addAttribute("mess","您已被拉入黑名单，拒绝访问！");
				return "client/login";
			}
		}
		//用户作为session
		httpSession.setAttribute("USER", isExistUser.get(0));
		//更新登录时间
		userService.updateLoginTime(isExistUser.get(0));
		return "redirect:/client/home.action";
	}
	
	
	//首页
	@RequestMapping(value="/client/home.action")
	public String toHome(Model model){
		//获取热门产品，以销售量高进行筛选
		List<Goods> maxList=userService.MaxSaleVolume();
		model.addAttribute("maxList", maxList);
		
		List<String> type=new ArrayList<>();
		//获取商品类型
		for(int i=0;i<maxList.size();i++) {
			Goodstype typeList=userService.getGoodsType(maxList.get(i).getGoodsType());
			type.add(typeList.getGtName());
		}
		
		//获取店铺设置的内容
		Store store=sysManagementService.getStore();
		
		model.addAttribute("store",store);
		model.addAttribute("type",type);
		return "client/home";
	}
	
	
	//短信验证
	@RequestMapping(value="/client/verification.action")
	public @ResponseBody
	 String json(@RequestBody String phone) throws Exception{
		//设置随机四位验证码
		int code=(int) ((Math.random()*9+1)*1000);
		String codeString=String.valueOf(code);
		VerificationCode.sendSms(phone, codeString);	
		System.out.println("验证码是"+codeString+"  "+phone);
		return codeString;
	}
	
	//注册
	@RequestMapping(value="/client/registerUser.action")
	public String registerUser(Model model,Userinfo userinfo,String code,String checkCode) {

		//查看用户名是否存在
		List<Userinfo> info1=userService.selectUserByName(userinfo.getUserName());
		//查看邮箱是否存在
		List<Userinfo> info2=userService.selectUserByEmail(userinfo.getUserEmail());
		//查看手机号是否存在
		List<Userinfo> info3=userService.selectUserByPhone(userinfo.getUserPhone());
		//如果存在
		if(info1.size()!=0) {
			model.addAttribute("userinfo", userinfo);
			model.addAttribute("nameMess", "您输入的用户名已存在，请重新输入");
			return "client/register";
		}
		if(info2.size()!=0) {
			model.addAttribute("userinfo", userinfo);
			model.addAttribute("emailMess", "您输入的邮箱已被注册，是否直接登录");
			return "client/register";
		}
		if(info3.size()!=0) {
			model.addAttribute("userinfo", userinfo);
			model.addAttribute("phoneMess", "您输入的手机号已被注册，是否直接登录");
			return "client/register";
		}
		
		if(!code.equals(checkCode)) {
			model.addAttribute("userinfo", userinfo);
			model.addAttribute("codeMess", "您输入的验证码有误，请重新获取验证码");
			System.out.println("接收验证码："+code);
			System.out.println("checkCode:"+checkCode);
			return "client/register";
		}else {
			//保存数据
			userService.saveUserInfo(userinfo);
			return "client/registerSuccess";
		}
		
	}

	//忘记密码手机验证
	@RequestMapping(value="/client/checkCode.action")
	public String checkCodeInForgetPass1(Model model,String code,String checkCode,String phone) {
		//查看手机号是否存在
		List<Userinfo> user=userService.selectUserByPhone(phone);
		if(user.size()==0) {
			model.addAttribute("phone",phone);
			model.addAttribute("phoneMess", "手机号不存在！");
			return "client/forgetPass1";
		}
		if(!code.equals(checkCode)) {
			model.addAttribute("codeMess", "验证码有误，请重新获取验证码");
			model.addAttribute("phone",phone);
			return "client/forgetPass1";
		}else {
			model.addAttribute("phone",phone);
			return "client/forgetPass2";
		}
	}
	
	//设置新密码
	@RequestMapping(value="/client/setNewPassword.action")
	public String setNewPassword(String password,String phone) {
		System.out.println("电话是："+phone);
		userService.setPassword(password,phone);
		return "client/forgetPass3";
	}
	
	
}

