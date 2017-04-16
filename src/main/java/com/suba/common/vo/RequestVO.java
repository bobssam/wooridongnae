package com.suba.common.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.vertx.java.core.json.JsonObject;

import com.suba.util.JsonMap;

public class RequestVO {
	private JsonObject data;
	private JsonObject where = new JsonMap();

	@JsonIgnore
	public JsonObject getData() {
		return data;
	}
	public void setData( JsonObject data ) {
		this.data = new JsonMap(data);
	}
	@JsonIgnore
	public JsonObject getWhere() {
		return where;
	}
	public void setWhere( JsonObject where ) {
		this.where = new JsonMap(where);
	}
}
