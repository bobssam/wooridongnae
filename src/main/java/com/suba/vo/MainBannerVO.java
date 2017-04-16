package com.suba.vo;

import java.util.Date;

public class MainBannerVO extends MemberVO{

	private int mainBannerNo;		// 메인 배너 번호
	private Date regDate;			// 등록일
	private int fileNo;				// 이미지 번호
	private String isUse;			// 사용 여부 {N=안씀, Y=사용}
	private String title;			// 배너 이름
	private String url;				// 링크

	public int getMainBannerNo() {
		return mainBannerNo;
	}
	public void setMainBannerNo(int mainBannerNo) {
		this.mainBannerNo = mainBannerNo;
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
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
