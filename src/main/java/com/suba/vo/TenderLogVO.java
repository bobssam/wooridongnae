package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;


public class TenderLogVO extends PageVO{
	private int tenderLogNo;
	private int tenderNo;
	private int memberNo;
	private int minusPoint;
	private Date regDate;
	private String id;
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public int getTenderLogNo() {
		return tenderLogNo;
	}
	public void setTenderLogNo( int tenderLogNo ) {
		this.tenderLogNo = tenderLogNo;
	}
	public int getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo( int tenderNo ) {
		this.tenderNo = tenderNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo( int memberNo ) {
		this.memberNo = memberNo;
	}
	public int getMinusPoint() {
		return minusPoint;
	}
	public void setMinusPoint( int minusPoint ) {
		this.minusPoint = minusPoint;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate( Date regDate ) {
		this.regDate = regDate;
	}
}
