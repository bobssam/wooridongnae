package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;

public class HotdealAskVO extends PageVO {

	private int hotdealNo;			// 핫딜 번호
	private int hotdealAskNo;		// 견적문의 번호
	private String mobile;		// 연착처
	private String area;		// 지역
	private Date wantDate;		// 구매 희망일
	private int buyType;		// 구입방법, 0=할부
	private String prePrice;	// 선입금
	private String contractDuration;		// 계약기간
	private String ask;			// 요청 사항
	private Date regDate;		// 등록일
	private int memberNo;		// 등록자 번호
	private String isAnswer;	// 답변 여부
	private String memo;		// 메모
	private String id;			// 아이디
	private String title;		// 제목

	public int getHotdealNo() {
		return hotdealNo;
	}
	public void setHotdealNo(int hotdealNo) {
		this.hotdealNo = hotdealNo;
	}
	public int getHotdealAskNo() {
		return hotdealAskNo;
	}
	public void setHotdealAskNo(int hotdealAskNo) {
		this.hotdealAskNo = hotdealAskNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getWantDate() {
		return wantDate;
	}
	public void setWantDate(Date wantDate) {
		this.wantDate = wantDate;
	}
	public int getBuyType() {
		return buyType;
	}
	public void setBuyType(int buyType) {
		this.buyType = buyType;
	}
	public String getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(String prePrice) {
		this.prePrice = prePrice;
	}
	public String getContractDuration() {
		return contractDuration;
	}
	public void setContractDuration(String contractDuration) {
		this.contractDuration = contractDuration;
	}
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
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
	public String getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
