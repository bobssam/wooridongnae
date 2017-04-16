package com.suba.user.service;

import java.util.LinkedList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
	
	private LinkedList<Message> messageQue = new LinkedList<SMSService.Message>();
	
	public void sendMessage( String phoneNumber,  String message ) {
		messageQue.add(new Message(phoneNumber, message));
	}
	
	private class Message {
		String phoneNumber;
		String message;
		
		Message( String phoneNumber,  String message ) {
			this.phoneNumber = phoneNumber;
			this.message = message;
		}
	}
	
	/**
	 * 1초마다 큐를 체크하며
	 * 큐가 있으면 보내고 종료 시킴
	 */
	@Scheduled(fixedDelay=1000)
	public void sendSchedule(  ) {
		try{
			for( int i=0;i<10;i++ ) {
				if( messageQue.isEmpty() ) return;
				Message message = messageQue.poll();
				System.out.println( "send to " +message.phoneNumber +" message "+ message.message);
				// TODO 보내는 로직 추가
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
