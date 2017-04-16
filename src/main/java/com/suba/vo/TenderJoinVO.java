package com.suba.vo;

import com.suba.common.vo.PageVO;

public class TenderJoinVO extends PageVO{
	
	// 입찰 / 종료 / 보류 / 성사
	public static final String TYPE_STATUS_TENDER = "T";
	public static final String TYPE_STATUS_END = "E";
	public static final String TYPE_STATUS_DEFER = "D";
	public static final String TYPE_STATUS_SUCCESS = "S";
	
	private int tenderNo;
	private int memberNo;
	private int tenderCount;
	private String tenderStatus;
	private int tenderBid;
	private int tenderMemberNo;
	
	public int getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo(int tenderNo) {
		this.tenderNo = tenderNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getTenderCount() {
		return tenderCount;
	}
	public void setTenderCount(int tenderCount) {
		this.tenderCount = tenderCount;
	}
	public String getTenderStatus() {
		return tenderStatus;
	}
	public void setTenderStatus(String tenderStatus) {
		this.tenderStatus = tenderStatus;
	}
	public int getTenderBid() {
		return tenderBid;
	}
	public void setTenderBid(int tenderBid) {
		this.tenderBid = tenderBid;
	}
	public int getTenderMemberNo() {
		return tenderMemberNo;
	}
	public void setTenderMemberNo(int tenderMemberNo) {
		this.tenderMemberNo = tenderMemberNo;
	}


}
