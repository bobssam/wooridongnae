package com.suba.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminFAQDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/faq")
public class AdminFAQApiController {

	@Autowired
	private AdminFAQDao faqDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardVO> selectBoardList( PageVO pageVO ) {

		List<BoardVO> memberVOList = faqDao.list(pageVO);
		int total = faqDao.total(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoard( BoardVO boardVO, HttpSession session ) {

		int row = faqDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( BoardVO boardVO ) {

		int row = faqDao.modify(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( BoardVO boardVO ) {

		int row = faqDao.delete(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
