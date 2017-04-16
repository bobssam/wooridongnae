package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminNoticeDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/notice")
public class AdminNoticeApiController {

	@Autowired
	private AdminNoticeDao noticeDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardVO> selectBoardList( PageVO pageVO ) {

		List<BoardVO> memberVOList = noticeDao.list(pageVO);
		int total = noticeDao.total(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoard( BoardVO boardVO ) {

		int row = noticeDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( BoardVO boardVO ) {

		int row = noticeDao.modify(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( BoardVO boardVO ) {

		int row = noticeDao.delete(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
