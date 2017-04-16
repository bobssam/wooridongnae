package com.suba.util;

import org.vertx.java.core.json.JsonArray;

public class Mybatis {

	public static boolean isArray(Object obj) {
		if( obj instanceof String[] ) return true;
		if( obj instanceof JsonArray ) return true;
		return false;
	}
}
