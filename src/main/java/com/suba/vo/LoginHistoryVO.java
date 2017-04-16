package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;


public class LoginHistoryVO extends PageVO{
	private int seq;
	private Date loginDate;
	private int memberNo;
	private String id;
	private String ip;
	public String getIp() {
		return ip;
	}
	public void setIp( String ip ) {
		this.ip = ip;
	}
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq( int seq ) {
		this.seq = seq;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate( Date loginDate ) {
		this.loginDate = loginDate;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo( int memberNo ) {
		this.memberNo = memberNo;
	}
}
