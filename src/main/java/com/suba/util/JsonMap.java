package com.suba.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.vertx.java.core.json.JsonObject;

public class JsonMap extends JsonObject implements Map<String, Object>{

	public JsonMap() {
		
	}
	
	public JsonMap( JsonObject obj ) {
		
		for( String key : obj.getFieldNames() ) {
			this.putValue( key, obj.getValue( key ) );
		}
	}

	@Override
	public void clear() {
		JsonObject j = new JsonObject();
		for( String key : getFieldNames() ) {
			this.removeField( key );
		}
	}

	@Override
	public boolean containsKey( Object key ) {
		return this.containsField( (String)key );
	}

	@Override
	public boolean containsValue( Object value ) {
		for( String key : getFieldNames() ) {
			if( this.getValue( key ).equals( value ) ) return true;
		}
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		
		return null;
	}

	@Override
	public Object get( Object key ) {
		return this.getValue( (String)key );
	}

	@Override
	public boolean isEmpty() {
		return this.getFieldNames().size() == 0;
	}

	@Override
	public Set<String> keySet() {
		return this.getFieldNames();
	}

	@Override
	public Object put( String key, Object value ) {
		return this.putValue( key, value );
	}

	@Override
	public void putAll( Map<? extends String, ? extends Object> m ) {

		return;
	}

	@Override
	public Object remove( Object key ) {
		return this.removeField( (String)key );
	}

	@Override
	public Collection<Object> values() {
		return null;
	}

}
