package com.suba.vo;


public class ResultVO<T> {

	private String status;
	private T data;
	
	public ResultVO( T obj ) {
		if( obj == null ) {
			this.status = "no result";
		} else {
			this.status = "ok";
			this.data = obj;
		}
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
