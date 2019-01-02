package com.client.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.client.entity.Userinfo;

public class LoginInterceptor implements HandlerInterceptor{
	
	//方法1调用前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//判断是不是/login.action，如果是就放行
		String requestURI=request.getRequestURI();
		//如果去主界面，放行
		if(requestURI.contains("/home")||requestURI.contains("/userLogin")||requestURI.contains("/userLogin")
				||requestURI.contains("/verification")||requestURI.contains("/registerUser")||requestURI.contains("/registerUser")
				||requestURI.contains("/checkCode")||requestURI.contains("/setNewPassword")||requestURI.contains("/forgetPass1")
				||requestURI.contains("/forgetPass2")||requestURI.contains("/forgetPass3")||requestURI.contains("/login")
				||requestURI.contains("/register")||requestURI.contains("/registerSuccess")) {
			return true;
		}else {
			Userinfo user=(Userinfo)request.getSession().getAttribute("USER");
			//如果获取的username为空则不放行，并重定向到登录页面
			if(null==user) {
				response.sendRedirect(request.getContextPath()+"/jsp/client/login.jsp");
				return false;
			}
		}
		return true;
	}
	
	//方法1调用后
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("方法1调用后");
	}
	
	//页面渲染后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("页面1渲染后");
	}
	
}	
