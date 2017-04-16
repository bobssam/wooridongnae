package com.suba.vo;

import java.util.List;
import java.util.Map;

public class ListVo<T> {

	private String status;
	private int total = 0;
	private List<T> list;
	private Map<String, Object> data;
	
	public ListVo( int total, List<T> list ) {
		this.status = "ok";
		this.total = total;
		this.list = list;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
