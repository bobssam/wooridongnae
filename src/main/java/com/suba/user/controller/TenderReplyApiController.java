package com.suba.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.service.TenderReplyService;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.TenderReplyVO;

@Controller
@RequestMapping(value="/api/tenderReply")
public class TenderReplyApiController {

	@Autowired
	private TenderReplyService tenderReplyService;


	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<TenderReplyVO> list( PageVO pageVO, HttpSession session ) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		
		ListVo<TenderReplyVO> list = tenderReplyService.selectReplyList(pageVO, memberVO);
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( TenderReplyVO tenderReplyVO, HttpSession session ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		Result rs = tenderReplyService.insertTenderReply(tenderReplyVO, memberVO);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( TenderReplyVO tenderReplyVO ) {
		
		/*
		int row = tenderReplyDao.modifyTenderReply(tenderReplyVO);
		Result rs = new Result("ok", row);
		return rs;
		*/
		return null;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( TenderReplyVO tenderReplyVO ) {

		/*
		int row = tenderReplyDao.deleteTenderReply(tenderReplyVO);
		Result rs = new Result("ok", row);
		return rs;
		*/
		return null;		
	}
	
}
