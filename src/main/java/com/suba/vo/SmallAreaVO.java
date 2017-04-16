package com.suba.vo;

import com.suba.common.vo.PageVO;

public class SmallAreaVO extends PageVO{

	private int bigAreaNo;
	private String bigAreaName;
	private int smallAreaNo;
	private String smallAreaName;
	private int areaCode;
	
	public int getBigAreaNo() {
		return bigAreaNo;
	}
	public void setBigAreaNo( int bigAreaNo ) {
		this.bigAreaNo = bigAreaNo;
	}
	public String getBigAreaName() {
		return bigAreaName;
	}
	public void setBigAreaName(String bigAreaName) {
		this.bigAreaName = bigAreaName;
	}
	public int getSmallAreaNo() {
		return smallAreaNo;
	}
	public void setSmallAreaNo( int smallAreaNo ) {
		this.smallAreaNo = smallAreaNo;
	}
	public String getSmallAreaName() {
		return smallAreaName;
	}
	public void setSmallAreaName( String smallAreaName ) {
		this.smallAreaName = smallAreaName;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
}
