package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;

public class CropReplyVO  extends PageVO{
	private int cropNo;
	private int replyNo;
	private Date regDate;
	private int memberNo;
	private String memberId;
	private String name;
	private String content;
	private int isMine;

	public int getCropNo() {
		return cropNo;
	}
	public void setCropNo(int cropNo) {
		this.cropNo = cropNo;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsMine() {
		return isMine;
	}
	public void setIsMine(int isMine) {
		this.isMine = isMine;
	}

}

