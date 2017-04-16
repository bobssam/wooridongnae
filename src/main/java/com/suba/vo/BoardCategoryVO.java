package com.suba.vo;

import com.suba.common.vo.PageVO;


public class BoardCategoryVO extends PageVO {
	private String boardType;
	private int categoryNo;
	private String categoryName;
	private String categoryType;
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType( String boardType ) {
		this.boardType = boardType;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo( int categoryNo ) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName( String categoryName ) {
		this.categoryName = categoryName;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType( String categoryType ) {
		this.categoryType = categoryType;
	}
}
