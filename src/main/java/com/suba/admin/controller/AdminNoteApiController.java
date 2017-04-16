package com.suba.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vertx.java.core.json.JsonObject;

import com.suba.admin.dao.AdminNoteDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.NoteVO;

@Controller
@RequestMapping(value="/admin/api/note")
public class AdminNoteApiController {

	@Autowired
	private AdminNoteDao noteDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<NoteVO> selectList( PageVO pageVO, HttpSession session ) {

		// 자신의 노트만 보이게 조정함
		MemberVO memberVO =  (MemberVO) session.getAttribute("memberVO");
		JsonObject json = new JsonObject();
		json.putNumber("memberNo", memberVO.getMemberNo());

		List<NoteVO> noteVoList = noteDao.list(pageVO);
		int total = noteDao.selectTotal(pageVO);
		ListVo<NoteVO> list = new ListVo<NoteVO>( total, noteVoList );
		return list;
	}

	@RequestMapping(value="/send")
	@ResponseBody
	public Result sendNote( NoteVO noteVO ) {

		// TODO 내번호를 넣어줘야함
		noteVO.setSenderNo(99);
		int row = noteDao.sendNote(noteVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/read")
	@ResponseBody
	public Result updateBoard( NoteVO noteVO ) {

		// TODO 포인트 증가
		int row = noteDao.receivePoint(noteVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( NoteVO noteVO ) {

		int row = noteDao.delete(noteVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
