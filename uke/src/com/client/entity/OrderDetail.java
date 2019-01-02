package com.client.entity;

import java.util.List;

public class OrderDetail {
	//用户订单表
	private List<OrderUser> orderUserList;

	public List<OrderUser> getOrderUserList() {
		return orderUserList;
	}

	public void setOrderUserList(List<OrderUser> orderUserList) {
		this.orderUserList = orderUserList;
	}
	
	
}
