package com.client.entity;

import java.util.Collections;
import java.util.List;

public class CommentAndUser{

	private Comment comment;
    public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private List<String> photoList;
	public List<String> getPhotoList() {
		
		return photoList;
	}

	public void setPhotoList(List<String> photoList) {
		this.photoList = photoList;
	}
	
}