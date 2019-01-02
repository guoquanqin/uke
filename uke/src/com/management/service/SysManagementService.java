package com.management.service;

import java.util.List;

import com.client.entity.Comment;
import com.client.entity.FeedbackComment;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralFeedbackComment;
import com.client.entity.Userinfo;
import com.management.entity.Managerinfo;
import com.management.entity.Store;

public interface SysManagementService {

	//保存新加账户
	void saveManager(Managerinfo managerinfo);

	//获取现有账户
	List<Managerinfo> getAllManager();

	//删除账户
	void deleteManager(Integer id);

	//保存图片
	void saveManagerPhoto(Integer managerId, String picName);

	//保存用户名
	void saveManaName(Integer managerId, String newName);

	//保存手机号
	void saveManaPhone(Integer managerId, String newPhone);

	//获取用户
	Managerinfo getManagerById(Integer managerId);

	//获取所有评价表
	List<Comment> getAllComment();

	//获取所有的积分评价表
	List<IntegralComment> getAllIntegralComment();

	//通过dateSetToList找comment
	List<Comment> getCommentByDate(String string);
	
	//通过dateSetToList找integralComment
	IntegralComment getIntegralCommentByDate(String string);

	//查询积分评价反馈信息
	List<IntegralFeedbackComment> getFeedbackCommentByIntegralOrderId(Integer integralOrderId);

	//获取反馈信息
	List<FeedbackComment> getFeedbackCommentByOrderId(Integer orderUserId);

	//保存商品反馈
	void saveFeedbackComment(FeedbackComment  feedbackComment);

	//保存积分订单反馈
	void saveIntegralFeedbackComment(IntegralFeedbackComment feedbackComment);

	//删除商品订单反馈
	void deleteFeedbackComment(Integer id);

	//删除积分订单反馈
	void deleteIntegralFeedbackComment(Integer id);

	//设置评价订单状态
	void setComment(Integer id, String flag);

	//设置积分评价订单状态
	void setIntegralComment(Integer id, String flag);

	//保存密码
	void saveManaPassword(Integer managerId, String newPass);

	//获取店铺设置的内容
	Store getStore();

	//修改店铺设置的内容
	void alterStore(Store store);

	//查询加入黑名单客户
	List<Userinfo> getBlackList();

	//查询未加入黑名单客户
	List<Userinfo> getNoBlackList();

	//删除黑名单
	void deleteBlackList(Integer id);

	//添加黑名单
	void addBlackList(Integer id);

}
