package com.suba.user.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.suba.common.vo.Result;
import com.suba.user.dao.UserDao;
import com.suba.vo.MemberVO;

@Service
public class PushService {

	private LinkedList<Message> messageQue = new LinkedList<PushService.Message>();

	@Autowired
	private UserDao userDao;

	public void sendMessage( MemberVO memberVO,  String message ) {
		messageQue.add(new Message(memberVO.getLastToken(), message));
	}

	private class Message {
		String deviceToken;
		String message;

		Message( String deviceToken,  String message ) {
			this.deviceToken = deviceToken;
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
				sendPush("SUBA 메세지", message.message, message.deviceToken);
				System.out.println( "send to " +message.deviceToken +" message "+ message.message);
				// TODO 푸시 보내는 로직 추가
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Result sendPush(String title, String msg, String regId) throws UnsupportedEncodingException  {
		Sender sender = new Sender("AIzaSyCueaVyL26W_zNWQbrc1gTsBU_sXs493RQ"); // 서버 API Key 입력
//		String regId = "APA91bHgckTbNlixHwB_BJ3L_iyZMaUXhwxtaBO-q5QwfoDEI4N3mbSCSuep1doVjQDmVEC-UnKok2xdQ1pO4rqrSxRfPv3e8SpmL8DG1dbn07B97ZNAvvBHC_EFdu4MQbR3sjD2eybq"; // 단말기 RegID 입력

		com.google.android.gcm.server.Message message = new Builder()
		.addData("message", URLEncoder.encode(msg, "UTF-8"))
		.addData("title",  URLEncoder.encode(title, "UTF-8"))
		.build();
		List<String> list = new ArrayList<String>();
		if (regId != null && regId == "") {
			list.add(regId);
		} else {
			List<MemberVO> regIds = userDao.selectAllRegId();
			for(int i=0, len=regIds.size(); i < len; i++ ) {
				list.add(regIds.get(i).getAndroidToken());
			}
		}
		MulticastResult multiResult;
		try {
			multiResult = sender.send(message, list, 5);
//			if (multiResult != null) {
//				List<com.google.android.gcm.server.Result> resultList = multiResult
//						.getResults();
//				for (com.google.android.gcm.server.Result result : resultList) {
//					System.out.println(result.getMessageId());
//				}
//			}
			return new Result("ok", multiResult.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
