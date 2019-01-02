package com.management.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyunCode.VerificationCode;
import com.client.entity.Userinfo;
import com.management.entity.Managerinfo;
import com.management.service.ManaValidateService;
import com.management.service.OrderService;
import com.md5Util.MD5Util;

@Controller
public class ManaValidateController {
	@Autowired
	private ManaValidateService manaService;
	@Autowired
	private OrderService orderService;
	
	//登录验证
	@RequestMapping(value="/management/login.action")
	public String manaLogin(Model model,Managerinfo managerinfo,HttpSession httpSession){
		List<Managerinfo> isExistUser=manaService.selectUserByPhoneAndPassword(managerinfo.getManagerPhone(),managerinfo.getManagerPassword());
		if(isExistUser.size()==0) {
			model.addAttribute("mess","您输入的账号密码有误，请重新确人");
			return "management/login";
		}
		else {
			//设置登录时间
			manaService.setLoginTime(isExistUser.get(0).getManagerId());
			httpSession.setAttribute("MANAGER", isExistUser.get(0));
			
			//获取订单的条数
			int orderCount = orderService.getOrderCount();
			httpSession.setAttribute("orderCount", orderCount);
			return "redirect:/management/home.action";
		}
	}
	
	//短信验证
	@RequestMapping(value="/management/verification.action")
	public @ResponseBody
	 String json(@RequestBody String phone) throws Exception{
		//设置随机四位验证码
		int code=(int) ((Math.random()*9+1)*1000);
		String codeString=String.valueOf(code);
		VerificationCode.sendSms(phone, codeString);	
		System.out.println("验证码是"+codeString+"  手机号码是 "+phone);
		return codeString;
	}
	
	//注册
	@RequestMapping(value="/management/registerMana.action")
	public String registerUser(Model model,Managerinfo manaInfo,String code,String checkCode) {
		//查看用户名是否存在
		List<Managerinfo> info1=manaService.selectUserByName(manaInfo.getManagerName());
		//查看手机号是否存在
		List<Managerinfo> info2=manaService.selectUserByPhone(manaInfo.getManagerPhone());
		//如果存在
		if(info1.size()!=0) {
			model.addAttribute("manaInfo", manaInfo);
			model.addAttribute("nameMess", "您输入的用户名已存在，请重新输入");
			return "management/register";
		}
		if(info2.size()!=0) {
			model.addAttribute("manaInfo", manaInfo);
			model.addAttribute("phoneMess", "您输入的手机号已被注册，是否直接登录");
			return "management/register";
		}
		
		if(!code.equals(checkCode)) {
			model.addAttribute("manaInfo", manaInfo);
			model.addAttribute("codeMess", "您输入的验证码有误，请重新获取验证码");
			System.out.println("接收验证码："+code);
			System.out.println("checkCode:"+checkCode);
			return "management/register";
		}else {
			//保存数据
			manaService.saveManaInfo(manaInfo);
			return "management/registerSuccess";
		}
	}
	
	//忘记密码手机验证
	@RequestMapping(value="/management/checkCode.action")
	public String checkCodeInForgetPass1(Model model,String code,String checkCode,String phone) {
		//查看手机号是否存在
		List<Managerinfo> user=manaService.selectUserByPhone(phone);
		if(user.size()==0) {
			model.addAttribute("phone",phone);
			model.addAttribute("phoneMess", "手机号不存在！");
			return "management/forgetPass1";
		}
		if(!code.equals(checkCode)) {
			model.addAttribute("codeMess", "验证码有误，请重新获取验证码");
			model.addAttribute("phone",phone);
			return "management/forgetPass1";
		}else {
			model.addAttribute("phone",phone);
			return "management/forgetPass2";
		}
	}
	
	//设置新密码
	@RequestMapping(value="/management/setNewPassword.action")
	public String setNewPassword(String password,String phone) {
		System.out.println("电话是："+phone);
		manaService.setPassword(password,phone);
		return "management/forgetPass3";
	}
	
	//退出登录
	@RequestMapping(value = "/management/exit.action")
	public String exit(Model model,HttpSession session) {
		session.removeAttribute("MANAGER");
		
		return "management/login";
	}
}

