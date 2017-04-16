package com.suba.vo;

import com.suba.common.vo.PageVO;

public class BigAreaVO extends PageVO{

	private int bigAreaNo;
	private String bigAreaName;
	public int getBigAreaNo() {
		return bigAreaNo;
	}
	public void setBigAreaNo( int bigAreaNo ) {
		this.bigAreaNo = bigAreaNo;
	}
	public String getBigAreaName() {
		return bigAreaName;
	}
	public void setBigAreaName( String bigAreaName ) {
		this.bigAreaName = bigAreaName;
	}

}
