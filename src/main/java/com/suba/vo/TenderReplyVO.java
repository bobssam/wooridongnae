package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;


public class TenderReplyVO extends PageVO{
	private int tenderNo;
	private int tenderSeq;
	private Date regDate;
	private int memberNo;
	private String contents;
	private int fileNo;
	private String id;
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public int getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo( int tenderNo ) {
		this.tenderNo = tenderNo;
	}
	public int getTenderSeq() {
		return tenderSeq;
	}
	public void setTenderSeq( int tenderSeq ) {
		this.tenderSeq = tenderSeq;
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
	public String getContents() {
		return contents;
	}
	public void setContents( String contents ) {
		this.contents = contents;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo( int fileNo ) {
		this.fileNo = fileNo;
	}
}
