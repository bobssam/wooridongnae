package com.suba.vo;

import java.util.Date;
import java.util.List;

import com.suba.common.vo.PageVO;


public class MemberVO extends PageVO{
	
	public final static int TYPE_ADMIN = 3;
	public final static int TYPE_USER = 1;
	public final static int TYPE_DEALER = 2;
	
	public final static int GRADE_BRONZE = 0;
	public final static int GRADE_SILVER = 1;
	public final static int GRADE_GOLD = 2;
	
	public final static String GRADE_NAME_BRONZE = "bronze";
	public final static String GRADE_NAME_SILVER = "silver";
	public final static String GRADE_NAME_GOLD = "gold";

	public final static String GRADE_KR_NAME_BRONZE = "브론즈";
	public final static String GRADE_KR_NAME_SILVER = "실버";
	public final static String GRADE_KR_NAME_GOLD = "골드";
	
	private int memberNo;
	private String id;
	private String pw;
	private String pw2;
	private String name;
	private String phoneNo;
	private String wantArea;
	private int type;
	private String iphoneToken;
	private String androidToken;
	private String pushYn;
	private Date loginDate;
	private Date regDate;
	private int grade;
	private int point;
	private String memo;
	private String corpName;
	private String corpNumber;
	private int categoryNo1;
	private int categoryNo2;
	//private int categoryNo3;
	private int fileNo;
	private String categoryNo1Name;
	private String categoryNo2Name;
	//private String categoryNo3Name;
	private int[] categoryNo3;	// 3차 분류 (여러개 선택이 가능해야해서 고민됨)
	private String[] categoryNo3Name;
	
	
	private String tel;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo( int memberNo ) {
		this.memberNo = memberNo;
	}
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw( String pw ) {
		this.pw = pw;
	}
	public int getType() {
		return type;
	}
	public void setType( int type ) {
		this.type = type;
	}
	public String getIphoneToken() {
		return iphoneToken;
	}
	public void setIphoneToken( String iphoneToken ) {
		this.iphoneToken = iphoneToken;
	}
	public String getAndroidToken() {
		return androidToken;
	}
	public void setAndroidToken( String androidToken ) {
		this.androidToken = androidToken;
	}
	public String getPushYn() {
		return pushYn;
	}
	public void setPushYn( String pushYn ) {
		this.pushYn = pushYn;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate( Date loginDate ) {
		this.loginDate = loginDate;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate( Date regDate ) {
		this.regDate = regDate;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade( int grade ) {
		this.grade = grade;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getCorpNumber() {
		return corpNumber;
	}
	public void setCorpNumber( String corpNumber ) {
		this.corpNumber = corpNumber;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getWantArea() {
		return wantArea;
	}
	public void setWantArea(String wantArea) {
		this.wantArea = wantArea;
	}
	public String getPw2() {
		return pw2;
	}
	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}
	public int getCategoryNo1() {
		return categoryNo1;
	}
	public void setCategoryNo1(int categoryNo1) {
		this.categoryNo1 = categoryNo1;
	}
	public int getCategoryNo2() {
		return categoryNo2;
	}
	public void setCategoryNo2(int categoryNo2) {
		this.categoryNo2 = categoryNo2;
	}
	/*public int getCategoryNo3() {
		return categoryNo3;
	}
	public void setCategoryNo3(int categoryNo3) {
		this.categoryNo3 = categoryNo3;
	}
	*/
	public String getGradeName() {
		if( grade == GRADE_BRONZE ) return GRADE_NAME_BRONZE;
		if( grade == GRADE_SILVER ) return GRADE_NAME_SILVER;
		if( grade == GRADE_GOLD ) return GRADE_NAME_GOLD;
		return null;
	}
	public String getGradeKrName() {
		if( grade == GRADE_BRONZE ) return GRADE_KR_NAME_BRONZE;
		if( grade == GRADE_SILVER ) return GRADE_KR_NAME_SILVER;
		if( grade == GRADE_GOLD ) return GRADE_KR_NAME_GOLD;
		return null;
	}
	public boolean isAdmin() {
		return type == TYPE_ADMIN; 
	}
	public String getLastToken() {
		return androidToken;
	}
	public boolean agreePush() {
		return "Y".equals(pushYn);
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getCategoryNo1Name() {
		return categoryNo1Name;
	}
	public void setCategoryNo1Name(String categoryNo1Name) {
		this.categoryNo1Name = categoryNo1Name;
	}
	public String getCategoryNo2Name() {
		return categoryNo2Name;
	}
	public void setCategoryNo2Name(String categoryNo2Name) {
		this.categoryNo2Name = categoryNo2Name;
	}
	public int[] getCategoryNo3() {
		return categoryNo3;
	}
	public void setCategoryNo3(int[] categoryNo3) {
		this.categoryNo3 = categoryNo3;
	}
	public String[] getCategoryNo3Name() {
		return categoryNo3Name;
	}
	public void setCategoryNo3Name(String[] categoryNo3Name) {
		this.categoryNo3Name = categoryNo3Name;
	}
	
	/*
	public String getCategoryNo3Name() {
		return categoryNo3Name;
	}
	public void setCategoryNo3Name(String categoryNo3Name) {
		this.categoryNo3Name = categoryNo3Name;
	}
	*/
	
}
