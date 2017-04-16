package com.suba.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vertx.java.core.json.JsonObject;

import com.suba.common.vo.PageVO;
import com.suba.user.dao.NoteDao;
import com.suba.vo.MemberVO;
import com.suba.vo.NoteVO;

@Service
public class MemoService {
	
	@Autowired
	private NoteDao noteDao;

	public List<NoteVO> myMemoList(MemberVO memberVO) {
		
		JsonObject where = new JsonObject();
		where.putNumber("memberNo", memberVO.getMemberNo());
		
		PageVO pageVO = new PageVO();
		pageVO.setWhere(where);
		
		return noteDao.list(pageVO);
	}
	
	public int myUnreadMemoTotal(MemberVO memberVO) {
		JsonObject where = new JsonObject();
		where.putNumber("memberNo", memberVO.getMemberNo());
		where.putNumber("read", 0);
		
		PageVO pageVO = new PageVO();
		pageVO.setWhere(where);
		
		return noteDao.total(pageVO);
	}

	public int myMemoTotal(MemberVO memberVO) {
		JsonObject where = new JsonObject();
		where.putNumber("memberNo", memberVO.getMemberNo());
		
		PageVO pageVO = new PageVO();
		pageVO.setWhere(where);
		
		return noteDao.total(pageVO);
	}


	public int myMemoRead(PageVO pageVO) {
		return noteDao.readNote(pageVO);
	}

	public int myMemoDel(PageVO pageVO) {
		return noteDao.delete(pageVO);

	}

	public int replyMemo(PageVO pageVO) {
		return noteDao.replyNote(pageVO);
	}
	
	

}
