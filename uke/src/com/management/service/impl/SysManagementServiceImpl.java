package com.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.dao.CommentMapper;
import com.client.dao.FeedbackCommentMapper;
import com.client.dao.IntegralCommentMapper;
import com.client.dao.IntegralFeedbackCommentMapper;
import com.client.dao.UserinfoMapper;
import com.client.entity.Comment;
import com.client.entity.CommentExample;
import com.client.entity.FeedbackComment;
import com.client.entity.FeedbackCommentExample;
import com.client.entity.IntegralComment;
import com.client.entity.IntegralCommentExample;
import com.client.entity.IntegralFeedbackComment;
import com.client.entity.IntegralFeedbackCommentExample;
import com.client.entity.Userinfo;
import com.client.entity.UserinfoExample;
import com.management.dao.BlackListMapper;
import com.management.dao.ManagerinfoMapper;
import com.management.dao.StoreMapper;
import com.management.entity.BlackList;
import com.management.entity.BlackListExample;
import com.management.entity.Managerinfo;
import com.management.entity.ManagerinfoExample;
import com.management.entity.Store;
import com.management.service.SysManagementService;
import com.md5Util.MD5Util;

@Service
public class SysManagementServiceImpl implements SysManagementService {
	@Autowired
	private ManagerinfoMapper managerinfoMapper;
	@Autowired
	private UserinfoMapper userInfoMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private BlackListMapper blackListMapper;
	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private FeedbackCommentMapper feedbackCommentMapper;
	@Autowired
	private IntegralCommentMapper integralCommentMapper;
	@Autowired
	private IntegralFeedbackCommentMapper integralFeedbackCommentMapper;

	// 保存新加账户
	@Override
	public void saveManager(Managerinfo managerinfo) {
		// 密码加密
		MD5Util md5 = new MD5Util();
		String md5Password = md5.string2MD5(managerinfo.getManagerPassword());
		managerinfo.setManagerPassword(md5Password);
		managerinfoMapper.insert(managerinfo);

	}

	// 获取现有账户
	@Override
	public List<Managerinfo> getAllManager() {
		ManagerinfoExample example = new ManagerinfoExample();
		ManagerinfoExample.Criteria criteria = example.createCriteria();
		criteria.andIsExistEqualTo("1");
		return managerinfoMapper.selectByExample(example);
	}

	// 删除账户
	@Override
	public void deleteManager(Integer id) {
		Managerinfo managerinfo = managerinfoMapper.selectByPrimaryKey(id);
		managerinfo.setIsExist("0");
		managerinfoMapper.updateByPrimaryKey(managerinfo);
	}

	// 保存图片
	@Override
	public void saveManagerPhoto(Integer managerId, String picName) {
		Managerinfo managerinfo = managerinfoMapper.selectByPrimaryKey(managerId);
		managerinfo.setManagerPhoto(picName);
		managerinfoMapper.updateByPrimaryKey(managerinfo);
	}

	// 保存用户名
	@Override
	public void saveManaName(Integer managerId, String newName) {
		Managerinfo managerinfo = managerinfoMapper.selectByPrimaryKey(managerId);
		managerinfo.setManagerName(newName);
		managerinfoMapper.updateByPrimaryKey(managerinfo);

	}

	// 保存手机号
	@Override
	public void saveManaPhone(Integer managerId, String newPhone) {
		Managerinfo managerinfo = managerinfoMapper.selectByPrimaryKey(managerId);
		managerinfo.setManagerPhone(newPhone);
		managerinfoMapper.updateByPrimaryKey(managerinfo);
	}

	@Override
	public Managerinfo getManagerById(Integer managerId) {
		return managerinfoMapper.selectByPrimaryKey(managerId);
	}

	// 获取所有评价表
	@Override
	public List<Comment> getAllComment() {
		CommentExample example = new CommentExample();
		CommentExample.Criteria criteria = example.createCriteria();
		criteria.andCommentIdIsNotNull();
		return commentMapper.selectByExample(example);
	}

	// 获取所有的积分评价表
	@Override
	public List<IntegralComment> getAllIntegralComment() {
		IntegralCommentExample example = new IntegralCommentExample();
		IntegralCommentExample.Criteria criteria = example.createCriteria();
		criteria.andIntegralCommentIdIsNotNull();
		return integralCommentMapper.selectByExample(example);
	}

	// 通过dateSetToList找comment
	@Override
	public List<Comment> getCommentByDate(String string) {
		CommentExample example = new CommentExample();
		CommentExample.Criteria criteria = example.createCriteria();
		criteria.andCommentIdIsNotNull();
		List<Comment> selectByExample = commentMapper.selectByExample(example);

		List<Comment> comment = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < selectByExample.size(); i++) {
			String time = formatter.format(selectByExample.get(i).getCommentTime());
			if (string.equals(time)) {
				comment.add(selectByExample.get(i));
			}
		}
		return comment;
	}

	// 通过dateSetToList找integralComment
	@Override
	public IntegralComment getIntegralCommentByDate(String string) {
		IntegralCommentExample example = new IntegralCommentExample();
		IntegralCommentExample.Criteria criteria = example.createCriteria();
		criteria.andIntegralCommentIdIsNotNull();
		List<IntegralComment> selectByExample = integralCommentMapper.selectByExample(example);

		List<IntegralComment> IntegralComment = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < selectByExample.size(); i++) {
			String time = formatter.format(selectByExample.get(i).getCommentTime());
			if (string.equals(time)) {
				IntegralComment.add(selectByExample.get(i));
			}
		}
		return IntegralComment.get(0);
	}

	// 获取积分订单反馈信息
	@Override
	public List<IntegralFeedbackComment> getFeedbackCommentByIntegralOrderId(Integer integralOrderId) {
		IntegralFeedbackCommentExample example = new IntegralFeedbackCommentExample();
		IntegralFeedbackCommentExample.Criteria criteria = example.createCriteria();
		criteria.andIntegralOrderIdEqualTo(integralOrderId);
		return integralFeedbackCommentMapper.selectByExample(example);
	}

	// 获取商品订单反馈信息
	@Override
	public List<FeedbackComment> getFeedbackCommentByOrderId(Integer orderUserId) {
		FeedbackCommentExample example = new FeedbackCommentExample();
		FeedbackCommentExample.Criteria criteria = example.createCriteria();
		criteria.andOrderUserIdEqualTo(orderUserId);
		return feedbackCommentMapper.selectByExample(example);
	}

	// 保存商品反馈
	@Override
	public void saveFeedbackComment(FeedbackComment feedbackComment) {

		feedbackCommentMapper.insert(feedbackComment);
	}

	// 保存积分订单反馈
	@Override
	public void saveIntegralFeedbackComment(IntegralFeedbackComment feedbackComment) {
		// TODO Auto-generated method stub
		integralFeedbackCommentMapper.insert(feedbackComment);
	}

	// 删除商品订单反馈
	@Override
	public void deleteFeedbackComment(Integer id) {
		FeedbackCommentExample example = new FeedbackCommentExample();
		FeedbackCommentExample.Criteria criteria = example.createCriteria();
		criteria.andOrderUserIdEqualTo(id);
		List<FeedbackComment> list = feedbackCommentMapper.selectByExample(example);
		feedbackCommentMapper.deleteByPrimaryKey(list.get(0).getFcId());
	}

	// 删除积分订单反馈
	@Override
	public void deleteIntegralFeedbackComment(Integer id) {
		IntegralFeedbackCommentExample example = new IntegralFeedbackCommentExample();
		IntegralFeedbackCommentExample.Criteria criteria = example.createCriteria();
		criteria.andIntegralOrderIdEqualTo(id);
		List<IntegralFeedbackComment> list = integralFeedbackCommentMapper.selectByExample(example);
		integralFeedbackCommentMapper.deleteByPrimaryKey(list.get(0).getIfcId());
	}

	// 设置评价订单状态
	@Override
	public void setComment(Integer id, String flag) {
		Comment comment = commentMapper.selectByPrimaryKey(id);
		comment.setIsShow(flag);
		commentMapper.updateByPrimaryKey(comment);
	}

	// 设置积分评价订单状态
	@Override
	public void setIntegralComment(Integer id, String flag) {
		IntegralComment comment = integralCommentMapper.selectByPrimaryKey(id);
		comment.setIsShow(flag);
		integralCommentMapper.updateByPrimaryKey(comment);
	}

	// 保存密码
	@Override
	public void saveManaPassword(Integer managerId, String newPass) {
		Managerinfo managerinfo = managerinfoMapper.selectByPrimaryKey(managerId);
		managerinfo.setManagerPassword(newPass);
		managerinfoMapper.updateByPrimaryKey(managerinfo);
	}

	// 获取店铺设置的内容
	@Override
	public Store getStore() {
		return storeMapper.selectByPrimaryKey(1);
	}

	// 修改店铺设置的内容
	@Override
	public void alterStore(Store store) {
		Store store2 = storeMapper.selectByPrimaryKey(1);
		store2.setBusinessHours(store.getBusinessHours());
		store2.setStoreAddress(store.getStoreAddress());
		store2.setStoreIntroduction(store.getStoreIntroduction());
		store.setStorePhone(store.getStorePhone());
		storeMapper.updateByPrimaryKey(store2);
	}

	// 查询加入黑名单客户
	@Override
	public List<Userinfo> getBlackList() {
		BlackListExample example = new BlackListExample();
		BlackListExample.Criteria criteria = example.createCriteria();
		criteria.andBlIdIsNotNull();
		List<BlackList> selectByExample = blackListMapper.selectByExample(example);

		List<Userinfo> list = new ArrayList<>();
		for (int i = 0; i < selectByExample.size(); i++) {
			Userinfo userinfo = userInfoMapper.selectByPrimaryKey(selectByExample.get(i).getUserId());
			list.add(userinfo);
		}
		return list;
	}

	// 查询未加入黑名单客户
	@Override
	public List<Userinfo> getNoBlackList() {
		BlackListExample example = new BlackListExample();
		BlackListExample.Criteria criteria = example.createCriteria();
		criteria.andBlIdIsNotNull();
		List<BlackList> selectByExample = blackListMapper.selectByExample(example);

		UserinfoExample userExample = new UserinfoExample();
		UserinfoExample.Criteria userCriteria = userExample.createCriteria();
		for (int i = 0; i < selectByExample.size(); i++) {
			userCriteria.andUserIdNotEqualTo(selectByExample.get(i).getUserId());
		}
		List<Userinfo> selectByExample2 = userInfoMapper.selectByExample(userExample);

		return selectByExample2;
	}

	// 删除黑名单
	@Override
	public void deleteBlackList(Integer id) {
		BlackListExample example = new BlackListExample();
		BlackListExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		List<BlackList> selectByExample = blackListMapper.selectByExample(example);

		blackListMapper.deleteByPrimaryKey(selectByExample.get(0).getBlId());
	}

	// 添加黑名单
	@Override
	public void addBlackList(Integer id) {
		BlackList black = new BlackList();
		black.setUserId(id);
		blackListMapper.insert(black);
	}

}
