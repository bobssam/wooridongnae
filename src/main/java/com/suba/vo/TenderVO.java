package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;


public class TenderVO extends PageVO{

	// 입찰 / 종료 / 보류 / 성사
	public static final String TYPE_STATUS_TENDER = "T";		// 입찰
	public static final String TYPE_STATUS_END = "E";			// 종료
	public static final String TYPE_STATUS_DEFER = "D";			// 보류
	public static final String TYPE_STATUS_SUCCESS = "S";		// 성사

	private int tenderNo;			// 입찰번호
	private Date regDate;			// 등록일
	private String status;			// 상태 (입찰 / 종료 / 보류 / 성사)
	private int areaCode;			// 지역
	private int categoryNo2;		// 2차 카테고리 선택
	private int categoryNo3;		// 3차 카테고리 선택
	private int categoryNo4;		// 4차 카테고리 선택
	private int buyType;			// 구입방법 {1=할부, 2=리스, 3=현금, 4=장기렌탈}
	private String title;			// 알아본 조건
	private String content;			// 요구조건
	private int replyCount;			// 입찰자 수
	private int memberNo;			// 등록자 번호
	private int bidMemberNo;		// 낙찰자 번호
	private String id;				// 등록자 아이디
	private String bidId;			// 낙찰자 아이디
	private Date wantDate;			// 희망일시

	private int fileNo1;			// 첨부 이미지 1
	private int fileNo2;			// 첨부 이미지 2
	private int fileNo3;			// 첨부 이미지 3
	private int fileNo4;			// 첨부 이미지 4
	private int fileNo5;			// 첨부 이미지 5

	private String categoryNo3Name;		// 3차 카테고리 선택
	private String categoryNo4Name;		// 4차 카테고리 선택

	private String grade;			// 등급

	private String bigAreaName;
	private String smallAreaName;


	private int isTender;

	public String getBidId() {
		return bidId;
	}
	public void setBidId( String bidId ) {
		this.bidId = bidId;
	}
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate( Date regDate ) {
		this.regDate = regDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus( String status ) {
		this.status = status;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public int getCategoryNo2() {
		return categoryNo2;
	}
	public void setCategoryNo2(int categoryNo2) {
		this.categoryNo2 = categoryNo2;
	}
	public int getCategoryNo3() {
		return categoryNo3;
	}
	public void setCategoryNo3(int categoryNo3) {
		this.categoryNo3 = categoryNo3;
	}
	public int getCategoryNo4() {
		return categoryNo4;
	}
	public void setCategoryNo4(int categoryNo4) {
		this.categoryNo4 = categoryNo4;
	}
	public int getBuyType() {
		return buyType;
	}
	public void setBuyType(int buyType) {
		this.buyType = buyType;
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
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getBidMemberNo() {
		return bidMemberNo;
	}
	public void setBidMemberNo(int bidMemberNo) {
		this.bidMemberNo = bidMemberNo;
	}
	public Date getWantDate() {
		return wantDate;
	}
	public void setWantDate(Date wantDate) {
		this.wantDate = wantDate;
	}
	public int getFileNo1() {
		return fileNo1;
	}
	public void setFileNo1(int fileNo1) {
		this.fileNo1 = fileNo1;
	}
	public int getFileNo2() {
		return fileNo2;
	}
	public void setFileNo2(int fileNo2) {
		this.fileNo2 = fileNo2;
	}
	public int getFileNo3() {
		return fileNo3;
	}
	public void setFileNo3(int fileNo3) {
		this.fileNo3 = fileNo3;
	}
	public int getFileNo4() {
		return fileNo4;
	}
	public void setFileNo4(int fileNo4) {
		this.fileNo4 = fileNo4;
	}
	public int getFileNo5() {
		return fileNo5;
	}
	public void setFileNo5(int fileNo5) {
		this.fileNo5 = fileNo5;
	}
	public int getIsTender() {
		return isTender;
	}
	public void setIsTender(int isTender) {
		this.isTender = isTender;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCategoryNo3Name() {
		return categoryNo3Name;
	}
	public void setCategoryNo3Name(String categoryNo3Name) {
		this.categoryNo3Name = categoryNo3Name;
	}
	public String getCategoryNo4Name() {
		return categoryNo4Name;
	}
	public void setCategoryNo4Name(String categoryNo4Name) {
		this.categoryNo4Name = categoryNo4Name;
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
}
