package com.management.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.management.entity.Managerinfo;

public class LoginInterceptor implements HandlerInterceptor{
	
	//方法1调用前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//判断是不是/login.action，如果是就放行
		String requestURI=request.getRequestURI();
		//如果去以下界面，放行
		if(requestURI.contains("/verification")||requestURI.contains("/registerMana")||requestURI.contains("/checkCode")
				||requestURI.contains("/setNewPassword")||requestURI.contains("/forgetPass1")
				||requestURI.contains("/forgetPass2")||requestURI.contains("/forgetPass3")||requestURI.contains("/login")
				||requestURI.contains("/register")||requestURI.contains("/registerSuccess")) {
			return true;
		}else {
			Managerinfo user=(Managerinfo)request.getSession().getAttribute("MANAGER");
			//如果获取的username为空则不放行，并重定向到登录页面
			if(null==user) {
				response.sendRedirect(request.getContextPath()+"/jsp/management/login.jsp");
				return false;
			}
		}
		return true;
	}
	
	//方法1调用后
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	//页面渲染后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}	
