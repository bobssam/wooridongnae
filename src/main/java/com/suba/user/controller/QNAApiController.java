package com.suba.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.QNADao;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/api/qna")
public class QNAApiController {

	@Autowired
	private QNADao qnaDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardVO> list( HttpSession session, PageVO pageVO ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO == null ) {
			return null;
		}
		
		pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
		
		List<BoardVO> memberVOList = qnaDao.list(pageVO);
		int total = qnaDao.selectTotal(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( HttpSession session, BoardVO boardVO ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO == null ) {
			return new Result("fail", "로그인 상태가 아님");
		}
		
		boardVO.setMemberNo(memberVO.getMemberNo());
		int row = qnaDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( BoardVO boardVO ) {

		int row = qnaDao.modify(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( BoardVO boardVO ) {

		int row = qnaDao.delete(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}
	
	@RequestMapping(value="/view")
	@ResponseBody
	public BoardVO view( HttpSession session, BoardVO boardVO ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO == null ) {
			return null;
		}
		
		boardVO.setMemberNo(memberVO.getMemberNo());
		
		qnaDao.plusHit(boardVO);
		return qnaDao.view(boardVO);
	}
}
