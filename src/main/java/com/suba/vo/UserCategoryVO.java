package com.suba.vo;

import com.suba.common.vo.PageVO;


public class UserCategoryVO extends PageVO{
	private int memberNo;
	private int categoryNo;
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
}
