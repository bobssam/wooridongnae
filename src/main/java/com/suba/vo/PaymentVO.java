package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;


public class PaymentVO extends PageVO{
	
	public static final String TYPE_CARD = "C";			//카드 결제
	public static final String TYPE_PHONE = "P";		//핸드폰 결제
	
	private int paymentNo;
	private int memberNo;
	private Date paymentDate;
	private int price;
	private String orderType;
	private String orderNo;
	private String id;
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public int getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo( int paymentNo ) {
		this.paymentNo = paymentNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo( int memberNo ) {
		this.memberNo = memberNo;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate( Date paymentDate ) {
		this.paymentDate = paymentDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice( int price ) {
		this.price = price;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType( String orderType ) {
		this.orderType = orderType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo( String orderNo ) {
		this.orderNo = orderNo;
	}
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo( int parentNo ) {
		this.parentNo = parentNo;
	}
	private int parentNo;
}
