package com.suba.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

/**
 * 로그 생성 매니저
 * @Project		: suba
 * @author		: COOPNC1815
 * @Date			: 2015. 5. 14.
 */
public class LoggerManager {
	PropertieManager	manager			= new PropertieManager();
	LoggerContext		loggerContext	= (LoggerContext) LoggerFactory.getILoggerFactory();
	String				rootPath		= null;
	String				rootBackPath	= null;
	SimpleDateFormat	sdf				= new SimpleDateFormat( "yyyyMMdd" );

	public LoggerManager(){
		init();
	}

	public void init() {
		rootPath = manager.get( "logging_root" );
		rootBackPath = manager.get( "logging_backup_root" );
	}

	/**
	 * 로거 설정
	 *
	 * @param file : 로그 파일 이름
	 * @param key : 로그 키
	 * @param pattern : 시간 패턴 (hhmmss 등의 시간 패턴)
	 * @return
	 */
	public Logger getLogger( String file, String key, String pattern ) {
		RollingFileAppender<ILoggingEvent> rollingFileAppender = new RollingFileAppender<ILoggingEvent>();
		rollingFileAppender.setAppend( true );
		rollingFileAppender.setContext( loggerContext );
		rollingFileAppender.setName( file );
		rollingFileAppender.setFile( rootPath + file + ".txt" );
		TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<ILoggingEvent>();
		rollingPolicy.setFileNamePattern( rootBackPath + file + ".%d{yyyyMMdd}.txt.zip" );
		rollingPolicy.setParent( rollingFileAppender ); // parent and context
														// required!
		rollingPolicy.setContext( loggerContext );
		rollingPolicy.start();
		rollingFileAppender.setRollingPolicy( rollingPolicy );
		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		if( key == null )
			encoder.setPattern( "%d{" + pattern + "} %msg%n" );
		else
			encoder.setPattern( "%d{" + pattern + "} " + key + " %msg%n" );
		encoder.setContext( loggerContext );
		encoder.start();
		rollingFileAppender.setEncoder( encoder );
		rollingFileAppender.start();
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger( file );
		root.setLevel( Level.INFO );
		root.detachAppender( file );
		root.addAppender( rollingFileAppender );
		return root;
	}

	/**
	 * 로거 설정
	 *
	 * @param file : 로그 파일 이름
	 * @param key : 로그 키
	 * @param pattern : 시간 패턴 (hhmmss 등의 시간 패턴)
	 * @return
	 */
	public Logger getLogger( String file, String pattern ) {
		return getLogger( file, null, pattern );
	}

	public String[] getRestorePath( String key ) {
		// TODO 현재는 사용 하지 않는 메소드
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		// 그제,어제,오늘 날짜의 로그를 패스를 준다.
		calendar.add( Calendar.DATE, -2 );
		String yesterday2 = rootBackPath + key + "." + sdf.format( calendar.getTime() ) + ".txt";
		calendar.add( Calendar.DATE, 1 );
		String yesterday = rootBackPath + key + "." + sdf.format( calendar.getTime() ) + ".txt";
		String today = rootPath + key + ".txt";
		String paths[] = { today, yesterday, yesterday2 };
		return paths;
	}
}