package com.suba.vo;

import java.util.Date;
import com.suba.common.vo.PageVO;

public class HotdealVO extends PageVO {

	private int hotdealNo;		// 핫딜 번호
	private int fileNo;			// 이미지 번호
	private String model;		// 모델
	private String title;		// 핫딜 제목
	private String comment;		// 핫딜 내용
	private Date regDate;		// 등록일
	private String isUse;		// 사용 여부 (N=사용하지않음 Y=사용함)
	private int	askCount;		// 핫딜 문의수
	private int hotdealAskNo;	// 참여 번호
	private int memberNo;		// 회원 번호

	public int getHotdealNo() {
		return hotdealNo;
	}
	public void setHotdealNo(int hotdealNo) {
		this.hotdealNo = hotdealNo;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public int getAskCount() {
		return askCount;
	}
	public void setAskCount(int askCount) {
		this.askCount = askCount;
	}
	public int getHotdealAskNo() {
		return hotdealAskNo;
	}
	public void setHotdealAskNo(int hotdealAskNo) {
		this.hotdealAskNo = hotdealAskNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

}
