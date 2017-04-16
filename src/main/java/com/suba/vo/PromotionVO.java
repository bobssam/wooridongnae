package com.suba.vo;

import java.util.Date;
import com.suba.common.vo.PageVO;

public class PromotionVO extends PageVO {

	private int promotionNo;	// 프로모션 번호
	private int fileNo;			// 이미지 번호
	private String title;		// 프로모션 제목
	private String model;		// 모델명
	private String comment;		// 프로모션 내용
	private Date regDate;		// 등록일
	private String isUse;		// 사용 여부 (N=사용하지않음 Y=사용함)

	public int getPromotionNo() {
		return promotionNo;
	}
	public void setPromotionNo(int promotionNo) {
		this.promotionNo = promotionNo;
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

}
