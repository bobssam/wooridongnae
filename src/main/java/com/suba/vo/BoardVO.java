package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;

public class BoardVO  extends PageVO{
	private int boardNo;
	private Date regDate;
	private int memberNo;
	private String name;
	private String title;
	private String content;
	private String answer;
	private String isNotice;
	private String isUse;
	private int hits;
	private int category;
	private int replyCount;
	private String id;
	private int isMine;

	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate( Date regDate ) {
		this.regDate = regDate;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo( int memberNo ) {
		this.memberNo = memberNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle( String title ) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent( String content ) {
		this.content = content;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer( String answer ) {
		this.answer = answer;
	}
	public String getIsNotice() {
		return isNotice;
	}
	public void setIsNotice( String isNotice ) {
		this.isNotice = isNotice;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public int getHits() {
		return hits;
	}
	public void setHits( int hits ) {
		this.hits = hits;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory( int category ) {
		this.category = category;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getIsMine() {
		return isMine;
	}
	public void setIsMine(int isMine) {
		this.isMine = isMine;
	}
	
}

