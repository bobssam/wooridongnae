package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;

public class CropWorkPicVO  extends PageVO{
	private int cropNo;				// 업체 번호
	private int cropWorkPicNo;		// 업체 작업 사진 번호
	private Date regDate;			// 등록일
	private int fileNo;				// 사진 번호
	private String content;			// 등록글

	public int getCropNo() {
		return cropNo;
	}
	public void setCropNo(int cropNo) {
		this.cropNo = cropNo;
	}
	public int getCropWorkPicNo() {
		return cropWorkPicNo;
	}
	public void setCropWorkPicNo(int cropWorkPicNo) {
		this.cropWorkPicNo = cropWorkPicNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}