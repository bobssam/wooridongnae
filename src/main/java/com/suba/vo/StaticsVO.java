package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;


public class StaticsVO extends PageVO{
	private Date ymd;
	private int loginCount;
	private int regCount;
	private int paymentPrice;
	private int paymentCount;
	public Date getYmd() {
		return ymd;
	}
	public void setYmd( Date ymd ) {
		this.ymd = ymd;
	}
	public int getLoginCount() {
		return loginCount;
	}
	public void setLoginCount( int loginCount ) {
		this.loginCount = loginCount;
	}
	public int getRegCount() {
		return regCount;
	}
	public void setRegCount( int regCount ) {
		this.regCount = regCount;
	}
	public int getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice( int paymentPrice ) {
		this.paymentPrice = paymentPrice;
	}
	public int getPaymentCount() {
		return paymentCount;
	}
	public void setPaymentCount( int paymentCount ) {
		this.paymentCount = paymentCount;
	}
}
