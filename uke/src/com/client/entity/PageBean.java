package com.client.entity;

import java.util.List;


public class PageBean {
	private int pageNum;   //当前页
	private int pageSize;  //每页显示的条数
	private int totalRecord; //总记录数
	private List<CommentAndUser> commentAndUser;

	private int totalPage; //总页数
	private int startIndex; //开始的索引
	private int start;
    private int end;
	
    
	public PageBean() {
		super();
	}

	public PageBean(int pageNum,int pageSize, int totalRecord) {
		this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        
        //获取总页数
        if(totalRecord%pageSize==0) {
        	this.totalPage=totalRecord/pageSize;
        }else {
        	this.totalPage=totalRecord/pageSize+1;
        }
        
        //开始索引
        this.startIndex=(pageNum-1)*pageSize;
        this.start=1;
        this.end=3;
        
        if(totalPage<=3) {
        	this.end=this.totalPage;
        }else {
        	this.start=pageNum-1;
        	this.end=pageNum+1;
        	
        	if(start<=0) {
        		this.start=1;
        		this.end=3;
        	}
        	if(end>this.totalPage) {
        		this.end=totalPage;
        		this.start=end-3;
        	}
        }
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public List<CommentAndUser> getCommentAndUser() {
		return commentAndUser;
	}

	public void setCommentAndUser(List<CommentAndUser> commentAndUser) {
		this.commentAndUser = commentAndUser;
	}
	


	
}
