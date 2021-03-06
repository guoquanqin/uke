package com.client.entity;

import java.util.Date;
import java.util.List;

public class Comment {
	private List<Goods> goodsList;
	private Userinfo user;
	private String feedbackComment;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_id
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    private Integer commentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.user_id
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_contents
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    private String commentContents;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_level
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    private Short commentLevel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_time
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    private Date commentTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_photo
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    private String commentPhoto;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.order_user_id
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    private Integer orderUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.is_show
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    private String isShow;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_id
     *
     * @return the value of comment.comment_id
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_id
     *
     * @param commentId the value for comment.comment_id
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.user_id
     *
     * @return the value of comment.user_id
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.user_id
     *
     * @param userId the value for comment.user_id
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_contents
     *
     * @return the value of comment.comment_contents
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public String getCommentContents() {
        return commentContents;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_contents
     *
     * @param commentContents the value for comment.comment_contents
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public void setCommentContents(String commentContents) {
        this.commentContents = commentContents == null ? null : commentContents.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_level
     *
     * @return the value of comment.comment_level
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public Short getCommentLevel() {
        return commentLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_level
     *
     * @param commentLevel the value for comment.comment_level
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public void setCommentLevel(Short commentLevel) {
        this.commentLevel = commentLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_time
     *
     * @return the value of comment.comment_time
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_time
     *
     * @param commentTime the value for comment.comment_time
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_photo
     *
     * @return the value of comment.comment_photo
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public String getCommentPhoto() {
        return commentPhoto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_photo
     *
     * @param commentPhoto the value for comment.comment_photo
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public void setCommentPhoto(String commentPhoto) {
        this.commentPhoto = commentPhoto == null ? null : commentPhoto.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.order_user_id
     *
     * @return the value of comment.order_user_id
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public Integer getOrderUserId() {
        return orderUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.order_user_id
     *
     * @param orderUserId the value for comment.order_user_id
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.is_show
     *
     * @return the value of comment.is_show
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.is_show
     *
     * @param isShow the value for comment.is_show
     *
     * @mbg.generated Sun Sep 09 20:30:52 CST 2018
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Userinfo getUser() {
		return user;
	}

	public void setUser(Userinfo user) {
		this.user = user;
	}

	public String getFeedbackComment() {
		return feedbackComment;
	}

	public void setFeedbackComment(String feedbackComment) {
		this.feedbackComment = feedbackComment;
	}
    
    
}