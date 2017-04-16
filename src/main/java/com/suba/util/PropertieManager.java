package com.suba.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * 프로퍼티를 관리합니다. 중간에 변경된 프로퍼티를 어떻게 감지할지는 아직없습니다.
 * 
 * @author coopmkt1818
 */
public class PropertieManager {
	Properties	properties	= new Properties();
	
	
	public  PropertieManager() {
		InputStreamReader isr = null;
		try {
			URL url = LoggerManager.class.getResource( "/context.properties" );
			isr = new InputStreamReader( new FileInputStream( url.getFile() ) );
			properties.load( isr );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		finally {
			if ( isr != null ) try {
				isr.close();
			}
			catch ( Exception e2 ) {}
		}
	}
	
	public String get( String key ) {
		return ( (String) properties.get( key ) ).trim();
	}
	
	public int getInt( String key ) {
		return Integer.parseInt( (String) properties.get( key ) );
	}
}
