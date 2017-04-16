package com.suba.common.vo;

import java.util.List;

public class Result {

	private String status  = null;
	private String reason  = null;
	private int affectedRow = 0;
	private Object data = null;
	private int total = 0;
	private List<Object> list = null;

	public Result() {
		status = "ok";
	}

	public Result( String status ) {
		this.status = status;
	}

	public Result( String status, String reason ) {
		this.status = status;
		this.reason = reason;
	}

	public Result( String result, String reason, int affectedRow ) {
		this.status = result;
		this.reason = reason;
		this.affectedRow = affectedRow;
	}

	public Result(String status, int affectedRow) {
		this.status = status;
		this.affectedRow = affectedRow;
	}

	public Result(String status, Object data){
		this.status = status;
		this.data = data;
	}

	public Result(String status, int total, List<Object> list){
		this.status = status;
		this.total = total;
		this.list = list;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getAffectedRow() {
		return affectedRow;
	}
	public void setAffectedRow(int affectedRow) {
		this.affectedRow = affectedRow;
	}
	public Object getData() {
		return data;
	}
	public void setData( Object data ) {
		this.data = data;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal( int total ) {
		this.total = total;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList( List<Object> list ) {
		this.list = list;
	}
}
