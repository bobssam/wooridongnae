package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;

public class PartnerAskVO extends PageVO {

	private int 	partnerAskNo;			// 문의 번호
	private String 	name;					// 이름
	private String 	mobile;					// 연착처
	private String 	email;					// 이메일
	private String 	comment;				// 문의 사항
	private Date	regDate;				// 등록일
	private String	memo;					// 메모

	public int getPartnerAskNo() {
		return partnerAskNo;
	}
	public void setPartnerAskNo(int partnerAskNo) {
		this.partnerAskNo = partnerAskNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
