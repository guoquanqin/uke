package com.client.entity;

import java.util.Date;
import java.util.List;

public class OrderUser {
	private List<OrderGoods> orderGoodsList;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_user.order_user_id
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    private Integer orderUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_user.user_id
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_user.order_time
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    private Date orderTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_user.order_status
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    private Short orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_user.order_price
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    private Float orderPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_user.is_exit
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    private String isExit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_user.delivery_time
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    private Date deliveryTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_user.address
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_user.confirm_receipt_time
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    private Date confirmReceiptTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_user.order_user_id
     *
     * @return the value of order_user.order_user_id
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public Integer getOrderUserId() {
        return orderUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_user.order_user_id
     *
     * @param orderUserId the value for order_user.order_user_id
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_user.user_id
     *
     * @return the value of order_user.user_id
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_user.user_id
     *
     * @param userId the value for order_user.user_id
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_user.order_time
     *
     * @return the value of order_user.order_time
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_user.order_time
     *
     * @param orderTime the value for order_user.order_time
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_user.order_status
     *
     * @return the value of order_user.order_status
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public Short getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_user.order_status
     *
     * @param orderStatus the value for order_user.order_status
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_user.order_price
     *
     * @return the value of order_user.order_price
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public Float getOrderPrice() {
        return orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_user.order_price
     *
     * @param orderPrice the value for order_user.order_price
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public void setOrderPrice(Float orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_user.is_exit
     *
     * @return the value of order_user.is_exit
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public String getIsExit() {
        return isExit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_user.is_exit
     *
     * @param isExit the value for order_user.is_exit
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public void setIsExit(String isExit) {
        this.isExit = isExit == null ? null : isExit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_user.delivery_time
     *
     * @return the value of order_user.delivery_time
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_user.delivery_time
     *
     * @param deliveryTime the value for order_user.delivery_time
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_user.address
     *
     * @return the value of order_user.address
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_user.address
     *
     * @param address the value for order_user.address
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_user.confirm_receipt_time
     *
     * @return the value of order_user.confirm_receipt_time
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public Date getConfirmReceiptTime() {
        return confirmReceiptTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_user.confirm_receipt_time
     *
     * @param confirmReceiptTime the value for order_user.confirm_receipt_time
     *
     * @mbg.generated Wed Aug 29 12:27:31 CST 2018
     */
    public void setConfirmReceiptTime(Date confirmReceiptTime) {
        this.confirmReceiptTime = confirmReceiptTime;
    }

	public List<OrderGoods> getOrderGoodsList() {
		return orderGoodsList;
	}

	public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}
    
    
}