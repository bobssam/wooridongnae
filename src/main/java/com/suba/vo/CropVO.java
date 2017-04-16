package com.suba.vo;

import java.util.Date;

public class CropVO extends MemberVO{

	private String corpName;	// 협력업체명
	private String cropCate;	// 업종 (, 로구분)
	private String cropGoods;	// 취급 품목
	private String represent;	// 대표자명
	private String address;		// 주소
	private String mobile;		// 핸드폰 번호
	private String phone;		// 회사 번호
	private String homepage;	// 회사 홈페이지
	private String dc;			// 회원 혜택
	private Date regDate;		// 등록일
	private int memberNo;		// 등록자
	private String title;		// 소개 제목
	private String content;		// 소개 글
	private String openingHour;	// 영업 시간
	private int bigAreaNo;		// 영업장 위치 시도
	private int smallAreaNo;	// 영업장 위치 구군
	private String bigAreaName;		//
	private String smallAreaName;	//
	private int hits;			// 조회수
	private int fileNo;			// 대표 사진 번호
	private int isMine;			// 자신의 글여부 (0:아님, 1:내글)

	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCropCate() {
		return cropCate;
	}
	public void setCropCate(String cropCate) {
		this.cropCate = cropCate;
	}
	public String getCropGoods() {
		return cropGoods;
	}
	public void setCropGoods(String cropGoods) {
		this.cropGoods = cropGoods;
	}
	public String getRepresent() {
		return represent;
	}
	public void setRepresent(String represent) {
		this.represent = represent;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOpeningHour() {
		return openingHour;
	}
	public void setOpeningHour(String openingHour) {
		this.openingHour = openingHour;
	}
	public int getBigAreaNo() {
		return bigAreaNo;
	}
	public void setBigAreaNo(int bigAreaNo) {
		this.bigAreaNo = bigAreaNo;
	}
	public int getSmallAreaNo() {
		return smallAreaNo;
	}
	public void setSmallAreaNo(int smallAreaNo) {
		this.smallAreaNo = smallAreaNo;
	}
	public String getBigAreaName() {
		return bigAreaName;
	}
	public void setBigAreaName(String bigAreaName) {
		this.bigAreaName = bigAreaName;
	}
	public String getSmallAreaName() {
		return smallAreaName;
	}
	public void setSmallAreaName(String smallAreaName) {
		this.smallAreaName = smallAreaName;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getIsMine() {
		return isMine;
	}
	public void setIsMine(int isMine) {
		this.isMine = isMine;
	}
}
