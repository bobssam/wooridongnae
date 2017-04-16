package com.suba.common.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.vertx.java.core.json.JsonObject;

import com.suba.util.JsonMap;

public class PageVO extends RequestVO{
	private int page = 1;
	private int len = 10;
	private JsonObject order;

	@JsonIgnore
	public int getPage() {
		return page;
	}
	public void setPage( int page ) {
		this.page = page;
	}
	@JsonIgnore
	public int getLen() {
		return len;
	}
	public void setLen( int len ) {
		this.len = len;
	}
    @JsonIgnore
	public int getStart() {
		return (page-1)*len;
	}
    @JsonIgnore
	public int getEnd() {
		return (page)*len;
	}
	public JsonObject getOrder() {
		return order;
	}
	public void setOrder( JsonObject order ) {
		this.order = new JsonMap(order);
	}
}
