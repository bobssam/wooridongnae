package com.suba.vo;

import com.suba.common.vo.PageVO;


public class CategoryVO extends PageVO{
	private int categoryNo;
	private String categoryName;
	private String categoryType;
	private int parentCategoryNo;
	
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
	public int getParentCategoryNo() {
		return parentCategoryNo;
	}
	public void setParentCategoryNo(int parentCategoryNo) {
		this.parentCategoryNo = parentCategoryNo;
	}
}
